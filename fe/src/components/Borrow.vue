<script>
import axios from "axios";

export default {
  data() {
    return {
      page: {page: 1, size: 5, total: 0, date: []}, loginId: '',
      // , currentUser: {name: ''}, name: ''
    }
  }, methods: {
    getBorrowTable() {
      let id = this.$route.query.id
      this.$axios.post(`/borrow/getTableBorrow/${this.page.page}/${this.page.size}/${id}`).then(res => {
        let result = res.data
        console.log(result)
        this.page.total = result.total
        this.page.date = result.rows
      })
    }, handleSizeChange(val) {
      this.page.size = val
      this.getBorrowTable()
    }, handleCurrentChange(val) {
      this.page.page = val
      this.getBorrowTable()
    }, returnBook(id, bookId) {//还书
      this.$axios.post(`/borrow/returnBook/${id}/${bookId}`).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success("归还成功")
          this.getBorrowTable()
        } else {
          this.$message.error(result.msg)
        }
      })
    }, returnBookInfo(id) {//催还图书
      this.$axios.post(`/borrow/returnBookInfo/${id}`).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success("已发送催还信息")
        } else {
          this.$message.error("催还失败")
        }
      })
    },
    // getCurrentUser() {
    //   let id = this.$route.query.id
    //   this.$axios.post(`user/getCurrentUser/${id}`).then(res => {
    //     let result = res.data
    //     console.log(result)
    //     this.currentUser = result.date
    //   })
    // }
  }, created() {
    this.getBorrowTable();
    this.loginId = this.$route.query.id
    // this.getCurrentUser()
  }
}
</script>

<template>
  <div id="borrow">
<!--    <h1> 当前登陆人 : {{ currentUser.name }}</h1>-->
    <el-table :data="page.date">
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="book" label="书名"></el-table-column>
      <el-table-column prop="user" label="借阅人"></el-table-column>
      <el-table-column prop="createTime" label="借阅时间"></el-table-column>
      <el-table-column prop="endTime" label="归还时间"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" v-if="scope.row.endTime===null &&loginId!=2"
                     @click="returnBook(scope.row.id,scope.row.bid)">归还
          </el-button>
          <el-button type="text" v-if="loginId==2 && scope.row.endTime===null" @click="returnBookInfo(scope.row.id)">
            催还图书
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page.page"
        :page-sizes="[5, 7, 10, 15]"
        :page-size="page.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.total">
    </el-pagination>
  </div>
</template>

<style>

</style>