<script>
import {defineComponent} from 'vue'
import bao from "@/components/bao.vue";

export default defineComponent({
  name: "Home",
  computed: {
    bao() {
      return bao
    }
  },
  methods: {
    adminLogin() {
      this.$router.push("/login")
    },
    baoGo() {
      this.$router.push("/bao")
    }

  }
})
</script>

<template>
  <div id="home">
    <el-button type="primary" @click="adminLogin">管理员登录</el-button>
    <el-button type="primary" @click="baoGo">学生报考</el-button>
    <el-button type="primary" @click="">学生登录</el-button>


  </div>
</template>

<style scoped>

</style>
