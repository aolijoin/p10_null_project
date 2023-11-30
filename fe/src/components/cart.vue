<script>
import {defineComponent} from 'vue'

export default defineComponent({
  name: "cart",
  data() {
    return {
      tableData: [],
      totalNum: 0,
      totalPrice: 0,
    }
  }, methods: {
    goHome() {
      this.$router.push('/')
    }, changeNum(row) { //商品数量改变
      if (row.num < 1) {
        row.num = 1
        this.$message.info('至少选择一件商品!')
      } else {
        this.$axios.post(`/cart/changeNum/${row.id}/${row.num}`).then(res => {
          let r = res.data
          if (r.code === 200) {
            this.getCartTable()
            this.$message.success('修改成功')
          } else {
            this.getCartTable()
            this.$message.info(r.msg)
          }
        })
      }
    }, handleClick(row) {
      this.$axios.post(`/cart/handleClick/${row.id}`).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.$message.success('已移除')
          this.getCartTable()
        } else {
          this.$message.error(r.msg)
        }
      })
    }, saveOrder() {
      this.$router.push('/order')
    }, getCartTable() {
      this.$axios.post(`/cart/getTableData`).then(res => {
        let r = res.data.data
        this.tableData = r.rows
        if (r.numPrice) {
          this.totalPrice = r.numPrice.price
          this.totalNum = r.numPrice.num
        } else {
          this.totalNum = '0'
        }
      })
    }
  }, created() {
    this.getCartTable()
  }
})
</script>

<template>
  <div id="cart">
    <el-button @click="goHome">继续购物</el-button>

    <el-table
        empty-text="购物车空空如也，快去选购吧"
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          prop="id"
          label="商品编号">
        <!--        <template slot-scope="scope">-->
        <!--          {{ scope.row.goods == null ? '' : scope.row.goods.id }}-->
        <!--        </template>-->
      </el-table-column>
      <el-table-column
          prop="goods"
          label="商品名称">
        <!--        <template slot-scope="scope">-->
        <!--          {{ scope.row.goods == null ? '' : scope.row.goods.name }}-->
        <!--        </template>-->
      </el-table-column>
      <el-table-column
          label="购买数量">
        <!--          prop="num"-->
        <template slot-scope="scope">
          <el-input v-model="scope.row.num" type="number" @blur="changeNum(scope.row)"></el-input>
        </template>
      </el-table-column>
      <el-table-column
          prop="price"
          label="单价">
        <!--        <template slot-scope="scope">-->
        <!--          {{ scope.row.goods == null ? '' : scope.row.goods.price }}-->
        <!--        </template>-->
      </el-table-column>
      <el-table-column
          label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">移除</el-button>
        </template>
      </el-table-column>
    </el-table>
    总数量：{{ totalNum }} | 总价格： {{ totalPrice }}

    <el-button @click="saveOrder()" size="small">结算</el-button>
  </div>
</template>

<style scoped>

</style>