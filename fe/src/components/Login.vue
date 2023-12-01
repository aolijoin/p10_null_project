<script>

export default {
  name: 'home',
  data() {
    return {form: {}}
  }, methods: {
    login() {
      this.$axios.post(`/user/login`, this.form).then(res => {
        let r = res.data
        let user = r.data
        if (r.code === 200) {
          this.$message.success('登录OK')
          if (user.type === 1) {
            this.$router.push('/goods')
          }else {
            this.$router.push('/apply')
          }
        } else {
          this.$message.error(r.msg)
        }
      })
    }
  }
}
</script>

<template>
  <div id="home">
    <el-form label-position="left" label-width="80px" :model="form">
      <el-form-item label="账号">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style>

</style>