<script>
import {defineComponent} from 'vue'

export default defineComponent({
  name: "Table",
  data() {
    return {
      page: {
        total: 0,
        rows: []
      },
      queryPage: {
        page: 1,
        size: 3
      },
      query: {}
    }
  }, methods: {
    getTableAll() {
      this.$axios.post(`/api/student/getStudent/${this.queryPage.page}/${this.queryPage.size}`, this.query).then(res => {
        let r = res.data
        this.page = r
        console.log(this.page)
      })
    }
  }, created() {
    this.getTableAll()
  }
})
</script>

<template>
  <div id="table">
    <template>
      <el-table
          :data="page.rows"
          style="width: 100%">
        <el-table-column prop="id" label="编号" width="70"></el-table-column>
        <el-table-column prop="name" label="名字"></el-table-column>
        <el-table-column prop="personId" label="身份证号码"></el-table-column>
        <el-table-column prop="major" label="报考专业"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template slot-scope="scope">
            {{
              scope.row.status == 0 ? '待通过' : scope.row.status == 1 ? '拒绝通过' : scope.row.status == 2 ? '待完善信息' : '已通过'
            }}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">

          </template>
        </el-table-column>


      </el-table>
    </template>
  </div>
</template>

<style scoped>

</style>
