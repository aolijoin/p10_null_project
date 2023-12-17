<script>
import {defineComponent} from 'vue'

export default defineComponent({
  name: "Login", data() {
    return {form: {}}
  }, methods: {
    reSet() {
      this.form = {}
    },
    login() {
      this.$axios.post(`/api/user/login`, this.form).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.$message.success("登录成功")
          this.$router.push("/table")
        } else {
          this.$message.error(r.msg)
        }
      })
    }
  }, created() {
  }

})
</script>

<template>
  <div id="login">
    <el-input placeholder="请输入账号" v-model="form.username"></el-input>
    <el-input placeholder="请输入密码" type="password" v-model="form.password"></el-input>
    <el-button @click="login" type="primary">登录</el-button>
    <el-button @click="reSet" type="primary">重置</el-button>
  </div>
</template>

<style scoped>

</style>
