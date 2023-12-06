<template>
  <div>
    <!--查询条件部分  searchmap -->
    <el-form ref="form" :inline="true" label-width="80px">
      <el-form-item label="名称">
        <el-input v-model="query.name" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getTableData">查询</el-button>
      </el-form-item>
    </el-form>

    <!--table 展示区-->
    <el-table
        :data="page.tableData">
      <el-table-column
          prop="id"
          label="线索id"
          width="180">
      </el-table-column>
      <el-table-column
          prop="person"
          label="通知用户"
          width="180">
      </el-table-column>
      <el-table-column
          prop="notify"
          label="通知内容"
          width="180">
      </el-table-column>
      <el-table-column
          prop="createtime"
          label="通知时间">
        <template slot-scope="scope" :formatter="timeFormat">
          {{ scope.row.createtime }}
        </template>
      </el-table-column>

    </el-table>

    <div class="pagination-container">
      <!--分页插件 -->
      <el-pagination
          class="pagiantion"
          @size-change="sizeChange"
          @current-change="fetchData"
          :current-page.sync="page.page"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="page.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "p10notify",
  data() {
    return {
      query: {name: '',},
      page: {
        page: 1,
        size: 5,
        total: 0,
        tableData: [
          {"id": 1, "person": '张三', "notify": "张三您好，您提交的线索1已办结", "createTime": '2023-11-07 12:12:12'},
          {"id": 1, "person": '李四', "notify": "李四您好，您提交的线索2办结失败", "createTime": '2023-11-07 12:12:12'}
        ],
      }
    }
  },
  created() {
    this.getTableData()
  },
  methods: {
    timeFormat() {
      return '123'
    },
    sizeChange(val) {
      this.page.size = val
      this.getTableData()
    },
    fetchData(val) {
      this.page.page = val
      this.getTableData()
    },
    getTableData() {
      this.$axios.post(`/notify/getTable/${this.page.page}/${this.page.size}`, this.query).then(res => {
        let r = res.data
        this.page.total = r.total
        this.page.tableData = r.rows
        console.log(r)
      })
    }
  }
}
</script>

<style scoped>

</style>
