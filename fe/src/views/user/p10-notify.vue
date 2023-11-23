<template>
  <div>
    <!--查询条件部分  searchmap -->
    <el-form ref="form" :inline="true" label-width="80px">
      <el-form-item label="名称">
        <el-input placeholder="名称" v-model="pojo.userName"></el-input>
      </el-form-item>

      <!--<el-form-item label="品牌的首字母" label-width="100px">
          <el-input  placeholder="品牌" ></el-input>
      </el-form-item>-->

      <el-form-item>
        <el-button type="primary" @click="getTabledata">查询</el-button>
      </el-form-item>
    </el-form>

    <!--table 展示区-->
    <template>
      <el-table
          :data="searchMap.tableData">
        <!--        border style="width: 100%"-->
        <el-table-column
            prop="id"
            label="线索id"
            width="180">
        </el-table-column>
        <el-table-column
            prop="userName"
            label="通知用户"
            width="180">
        </el-table-column>
        <el-table-column
            prop="notify"
            label="通知内容"
            width="180">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="通知时间">
          <!--                  :formatter="timeFormat"-->
        </el-table-column>

      </el-table>
    </template>

    <div class="pagination-container">
      <!--分页插件 -->
      <el-pagination
          class="pagiantion"
          @size-change="sizeChange"
          @current-change="fetchData"
          :current-page.sync="searchMap.page"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="searchMap.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="searchMap.total">
      </el-pagination>
    </div>


  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "list",
  data() {
    return {
      searchMap: {
        total: 10,
        page: 1,
        size: 5, tableData: [
          {"id": 1, "userName": '张三', "notify": "张三您好，您提交的线索1已办结", "createTime": '2023-11-07 12:12:12'},
          {"id": 1, "userName": '李四', "notify": "李四您好，您提交的线索2办结失败", "createTime": '2023-11-07 12:12:12'}
        ],
      },
      dialogVisible: false,
      pojo: {}
    }
  },
  created() {
    this.getTabledata()
  },
  methods: {
    fetchData(val) {
      this.searchMap.page = val
      this.getTabledata()
    },
    sizeChange(val) {
      this.searchMap.size = val
      this.getTabledata()
    },
    getTabledata() {
      axios.post(`/notify/getTableData/${this.searchMap.page}/${this.searchMap.size}`, this.pojo).then(res => {
        let result = res.data
        this.searchMap.total = result.total
        this.searchMap.tableData = result.rows
      })
    }
  }
}
</script>

<style scoped>

</style>
