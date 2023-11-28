<script>
export default {
  data() {
    return {
      form: {},
    }
  }, methods: {
    login() {
      this.$axios.post(`/user/login`, this.form).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          let data = result.data.user
          let msg = result.data.borrow
          console.log(msg)
          this.$router.push({path: '/home', query: {name: data.username, id: data.id}})
          if (msg != null) {
            alert(data.name + '您于' + msg.createTime + '借阅一本书,请及时归还 ！！！')
          }
          this.$message.success('欢迎回来: ' + data.name)
        } else {
          this.$message.error(result.msg)
        }
      })
    }
  }, created() {

  }

}
</script>

<template>
  <div id="login">
    用户名
    <el-input v-model="form.username" size="mini" style="width: 200px"></el-input>
    <br>
    密 码
    <el-input v-model="form.password" size="mini" style="width: 200px"></el-input>
    <br>
    <el-button type="primary" @click="login">登 录</el-button>

  </div>
</template>

<style>

</style>