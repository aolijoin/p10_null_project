<script>

import axios from "axios";

export default {
  name: 'home',
  data() {
    return {
      form: {},
      categoryList: []
      ,
      page: {
        page: 1,
        size: 3,
        total: 0,
        tableData: [{id: '1', name: '答辩', price: 1145.14, num: 51, category: '家电'}]
      },

    }
  }, methods: {
    getTableData() {
      this.$axios.post(`/goods/getTableData/${this.page.page}/${this.page.size}`, this.form).then(res => {
        let r = res.data
        this.page.tableData = r.rows
        this.page.total = r.total
      })
    },
    handleCurrentChange(val) {
      this.page.page = val
      this.getTableData()
    }, handleClick(row) {
      this.$axios.post(`/cart/addCart/${row.id}`).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.$message.success('已加入购物车。')
          this.getTableData()
        } else {
          this.$message.error(r.msg)
        }
      })
    }, goShopCar() {
      this.$router.push('/cart')
    }, getCategory() {
      this.$axios.post(`/cate/getCateAll`).then(res => {
        this.categoryList = res.data.data
      })
    }
  }, created() {
    this.getTableData()
    this.getCategory()
  }
}
</script>

<template>
  <div id="home">
    <el-form :model="form" label-width="80px">

      <el-form-item label="商品分类">
        <el-select v-model="form.cid" placeholder="请选择商品分类" :clearable="true">
          <el-option v-for="c in categoryList" :label="c.name" :value="c.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="商品编号">
        <el-input v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getTableData">搜索</el-button>
        <el-button type="primary" @click="goShopCar">购物车</el-button>
      </el-form-item>
    </el-form>

    <el-table
        :data="page.tableData"
        border
        style="width: 100%">
      <el-table-column
          prop="id"
          label="编号">
      </el-table-column>
      <el-table-column
          prop="name"
          label="商品名称">
      </el-table-column>
      <el-table-column
          prop="price"
          label="商品价格">
      </el-table-column>
      <el-table-column
          prop="num"
          label="商品库存">
      </el-table-column>
      <el-table-column
          label="商品分类" prop="category">
        <!--        <template slot-scope="scope">-->
        <!--          {{ scope.row.category == null ? '' : scope.row.category.name }}-->
        <!--        </template>-->
      </el-table-column>
      <el-table-column
          label="操作">
        <template slot-scope="scope">
          <el-button
              @click="handleClick(scope.row)" :disabled="scope.row.num<1" type="text" size="small">
            {{ scope.row.num < 1 ? '售馨' : '选购'}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @current-change="handleCurrentChange"
        :current-page="page.page"
        :page-size="page.size"
        layout="total, prev, pager, next, jumper"
        :total="page.total">
    </el-pagination>
  </div>
</template>

<style>

</style>