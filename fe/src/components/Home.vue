<script>

export default {
  comments: {},
  name: 'home',
  data() {
    return {
      page: {page: 1, size: 3, total: 0, data: []}, user: '', form: {},
      query: {name: '', author: '', publishing: ''}, pub: [], dialogFormVisible: false,
    }
  }, methods: {
    del(id) {
      this.$axios.post(`book/del/${id}`).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getTableData()
          this.$message.success('删除成功')
        } else {
          this.$message.success(result.msg)
        }
      })
    },
    handleSizeChange(val) {
      this.page.size = val
      this.getTableData()
    }, handleCurrentChange(val) {
      this.page.page = val
      this.getTableData()
    },
    getTableData() {
      this.$axios.post(`/book/getTableDate/${this.page.page}/${this.page.size}`, this.query).then(res => {
        let result = res.data;
        console.log(this.user)
        this.page.total = result.total;
        this.page.data = result.rows;
      })
    }, getAll() {
      this.$axios.post(`/publishing/getAll`).then(res => {
        this.pub = res.data.data
      })
    }, update(row) {
      console.log(row)
      this.dialogFormVisible = true
      this.form = row
    }, borrow(id) {
      this.$axios.post(`/book/borrow/${id}`).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success('借阅成功')
          this.getTableData()
        } else {
          this.$message.error(result.msg)
        }
      })

    }, goBookInfo() {
      let id = this.$route.query.id
      this.$router.push({path: '/borrow', query: {id: id}})
    }, updateBook() {
      this.$axios.post(`/book/updateBook`, this.form).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.dialogFormVisible = false
          this.getTableData()
          this.$message.success("修改成功")
        } else {
          this.$message.success(result.msg)
        }
      })
    }
  }, created() {
    this.user = this.$route.query.name
    this.getTableData()
    this.getAll()
  }
}
</script>

<template>
  <div id="home">
    <div id="query">
      书名：
      <el-input v-model="query.name"></el-input>
      作者：
      <el-input v-model="query.author"></el-input>
      出版社：
      <el-input v-model="query.publishing"></el-input>
      <el-button plain @click="getTableData">查询</el-button>
      <el-button plain @click="goBookInfo">借阅信息表</el-button>
    </div>
    <div id="table">
      <el-table :data="page.data">
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="name" label="书名"></el-table-column>
        <el-table-column prop="publishing" label="出版社"></el-table-column>
        <el-table-column prop="author" label="作者"></el-table-column>
        <el-table-column prop="num" label="借阅次数"></el-table-column>
        <el-table-column prop="status" label="当前状态">
          <template slot-scope="scope">{{ scope.row.status == 0 ? "借阅中" : '未借阅' }}</template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" v-if="user==='admin'" @click="update(scope.row)">修改</el-button>
            <el-button type="text" v-if="user!='admin' && scope.row.status===1" @click="borrow(scope.row.id)">借阅
            </el-button>
            <el-button type="text" v-if="user==='admin'" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="page.page"
          :page-sizes="[3, 6, 10, 15]"
          :page-size="page.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.total">
      </el-pagination>
    </div>
    <div id="update">
      <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="书名">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="出版社">
            <el-select v-model="form.pid" placeholder="请选择">
              <el-option
                  v-for="item in pub"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="form.author" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateBook">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<style>

</style>