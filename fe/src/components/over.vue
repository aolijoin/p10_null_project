<script>

import goods from "@/components/goods.vue";

export default {
  data() {
    return {}
  }, methods: {
    go() {
      this.$router.push('/')
    }
  }
}
</script>

<template>
  <div id="over">

    <h1>结束购物</h1>
    <a href="" @click.prevent="go">返回首页</a>
  </div>
</template>

<style>

</style>