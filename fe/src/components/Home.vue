<script>

export default {
  name: 'home',
  data() {
    return {}
  }, methods: {
    test() {
      this.$axios.post(`/api/hello`).then(res => {
        this.$message.success(res.data)
      })
    }
  }
}
</script>

<template>
  <div id="home">
    <el-button type="primary" @click="test">点击测试</el-button>
  </div>
</template>

<style>

</style>