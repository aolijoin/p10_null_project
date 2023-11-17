<script>

export default {
  name: 'home',
  data() {
    return {}
  }, methods: {
    clck() {
      this.$axios.post(`/api/hello`).then(res => {
        this.$message.success(res.data)
      })
    }
  }
}
</script>

<template>
  <div id="home">
    <el-button type="primary" @click="clck">点击</el-button>
  </div>
</template>

<style>

</style>