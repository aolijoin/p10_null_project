<script>

import axios from "axios";

export default {
  name: 'home',
  data() {
    return {
      page: {
        tableData: [],
        page: 1,
        size: 3,
        total: 0,
      },
      majors: [], hobbys: [], multipleSelection: [],
      query: {}, form: {}, dialogFormVisible: false,
      model: {
        title: '',
        url: '',
      }
    }
  }, methods: {
    handleSizeChange(val) {
      this.page.size = val
      this.getTableData()
    }, handleCurrentChange(val) {
      this.page.page = val
      this.getTableData()
    }, getTableData() {
      axios.post(`/student/getTableData/${this.page.page}/${this.page.size}`, this.query).then(res => {
        let result = res.data
        this.page.total = result.total
        this.page.tableData = result.rows
      })
    }, getMajor() {
      axios.post(`major/getMajor`).then(res => {
        this.majors = res.data.data
      })
    }, addPage() {
      this.model.title = '添加学生'
      this.model.url = '/student/addStudent'
      this.form = {}
      this.dialogFormVisible = true
      this.hobbys = []
    }, updatePage(row) {
      this.model.title = '修改学生信息'
      this.model.url = '/student/updateStudent'
      this.form = row
      this.hobbys = row.hobby.split(',')
      this.dialogFormVisible = true
    }, saveStudent() {
      this.form.hobby = this.hobbys.join(',')
      console.log(this.form)
      axios.post(this.model.url, this.form).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success('执行成功')
          this.dialogFormVisible = false
          this.getTableData()
        } else {
          this.$message.error(result.msg)
        }
      })
    }, handleSelectionChange(val) {
      this.multipleSelection = val
    }, deleteList() {
      if (this.multipleSelection.length < 1) {
        this.$message.info('请选择您要删除的数据')
      } else {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = []
          for (let i = 0; i < this.multipleSelection.length; i++) {
            ids.push(this.multipleSelection[i].id)
          }
          axios.post(`/student/deleteList?ids=${ids}`).then(res => {
            let result = res.data
            if (result.code === 200) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.getTableData()
            } else {
              this.$message.error(result.msg)
            }
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    }

  }, created() {
    this.getTableData()
    this.getMajor()
  }
}
</script>

<template>
  <div id="home">
    <el-input v-model="query.name" style="width: 200px"></el-input>
    <el-button @click="getTableData" type="primary">查询</el-button>
    <el-button @click="addPage" type="primary">添加</el-button>
    <el-button @click="deleteList" type="primary">批量删除</el-button>

    <el-table :data="page.tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="编码"></el-table-column>
      <el-table-column prop="name" label="名字"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别">
        <template slot-scope="scope">{{ scope.row.sex == 0 ? '男' : '女' }}</template>
      </el-table-column>
      <el-table-column prop="hobby" label="爱好"></el-table-column>
      <el-table-column prop="birthday" label="生日"></el-table-column>
      <el-table-column prop="major" label="专业"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="updatePage(scope.row)"> 修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page.page"
        :page-sizes="[3, 6, 9, 12]"
        :page-size="page.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.total">
    </el-pagination>
    <el-dialog :title="model.title" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="名字">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" type="number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.sex" placeholder="请选择活动区域">
            <el-option label="男" :value="0"></el-option>
            <el-option label="女" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="爱好">
          <el-checkbox-group v-model="hobbys">
            <el-checkbox label="篮球"></el-checkbox>
            <el-checkbox label="足球"></el-checkbox>
            <el-checkbox label="乒乓球"></el-checkbox>
            <el-checkbox label="唱歌"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker type="date" v-model="form.birthday"></el-date-picker>

        </el-form-item>
        <el-form-item label="专业">
          <el-select v-model="form.mid" placeholder="请选择活动区域">
            <el-option :key="m.id" :label="m.name" :value="m.id" v-for="m in majors"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveStudent">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<style>

</style>