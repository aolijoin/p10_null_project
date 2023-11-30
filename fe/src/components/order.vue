<template>
  <div>
    <h1>确认订单</h1>
    <el-form :model="form" label-width="80px">
      <el-form-item label="购买商品">
        <el-input type="textarea" v-model="form.content" disabled></el-input>
      </el-form-item>
      <el-form-item label="总金额">
        <el-input v-model="form.totalPrice" disabled></el-input>
      </el-form-item>
      <el-form-item label="下单时间">
        <el-input v-model="form.createTime" disabled></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="zhifu">立即支付</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {form: {}}
  }, methods: {
    zhifu() {
      this.$axios.post(`/order/zhi`, this.form).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.$message.success('支付成功')
          this.$router.push('/over')
        } else {
          this.$message.success('支付失败')
        }
      })
    }, getGoodsInfo() {
      this.$axios.post(`/order/getInfo`).then(res => {
        this.form = res.data.data
      })
    }
  }, created() {
    this.getGoodsInfo()
  }
}
</script>