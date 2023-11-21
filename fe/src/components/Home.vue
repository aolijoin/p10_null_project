<script>

import axios from "axios";

export default {
  name: 'home',
  data() {
    return {
      page: {
        page: 1, size: 3, total: 0, data: []
      }, dialogFormVisible: false, multipleSelection: [],
      query: {name: ''}, form: {}, model: {}, hobbys: [], dormitory: []
    }
  }, methods: {
    getDormitory() {
      axios.post(`/dormitory/getAll`).then(res => {
        this.dormitory = res.data.data
      })
    },
    getTableData() {
      axios.post(`/user/getTableData/${this.page.page}/${this.page.size}`, this.query).then(res => {
        let result = res.data
        this.page.total = result.total
        this.page.data = result.rows
      })
    }, goAddPage() {
      this.dialogFormVisible = true
      this.model.title = '添加用户'
      this.model.url = `/user/addUser`
    }, updatePage(row) {
      this.form = row
      this.hobbys = row.hobby.split(',')
      this.dialogFormVisible = true
      this.model.title = '修改'
      this.model.url = `/user/update`

    }, addUser() {
      console.log(this.form)
      console.log(this.hobbys)
      this.form.hobby = this.hobbys.join(',')
      axios.post(this.model.url, this.form).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.dialogFormVisible = false
          this.getTableData()
        } else {
          this.$message.error(result.msg)
        }
      })
    }, handleSizeChange(val) {
      this.page.size = val
      this.getTableData()
    }, handleCurrentChange(val) {
      this.page.page = val
      this.getTableData()
    }, delList() {
      if (this.multipleSelection.length < 1) {
        this.$message.info('请选择要删除的数据')
      } else {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = [];
          for (let i = 0; i < this.multipleSelection.length; i++) {
            ids.push(this.multipleSelection[i].id)
          }
          console.log(ids)
          axios.post(`/user/delList?ids=${ids}`).then(res => {
            let result = res.data
            if (result.code === 200) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getTableData()
            } else {
              this.$message.error(result.msg)
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    }

  }, created() {
    this.getTableData()
    this.getDormitory()
  }
}
</script>

<template>
  <div id="home">
    <el-input v-model="query.name" placeholder="输入名字"></el-input>
    <el-button @click="getTableData">查询</el-button>
    <el-button @click="goAddPage">添加</el-button>
    <el-button @click="delList">批量删除</el-button>
    <el-table :data="page.data" @selection-change="handleSelectionChange">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别">
        <template slot-scope="scope">{{ scope.row.sex == 1 ? '女' : '男' }}</template>
      </el-table-column>
      <el-table-column prop="hobby" label="爱好"></el-table-column>
      <el-table-column prop="birthday" label="生日"></el-table-column>
      <el-table-column prop="dormitory" label="宿舍"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="updatePage(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page.page"
        :page-sizes="[3, 5, 6, 10]"
        :page-size="3"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.total">
    </el-pagination>
    <el-dialog :title="model.title" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" type="number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option label="男" :value="0"></el-option>
            <el-option label="女" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="爱好">
          <el-checkbox-group v-model="hobbys">
            <el-checkbox label="吃" value="吃"></el-checkbox>
            <el-checkbox label="喝" value="喝"></el-checkbox>
            <el-checkbox label="玩" value="玩"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker
              v-model="form.birthday"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="宿舍">
          <el-select v-model="form.bid" placeholder="请选择">
            <el-option
                v-for="item in dormitory"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>

</style>