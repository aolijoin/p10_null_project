<script>

export default {
  name: 'ssq',
  data() {
    return {
      sheng: [], shi: [], qv: [],
      // a1: {}, a2: {}, a3: {},
      b1: '', b2: '', b3: '',
      c1: '', c2: '', c3: ''
    }
  }, methods: {
    getSsq() {
      this.axios.post(`/com/getSsq?pid=`).then(res => {
        this.sheng = res.data.data
      })
      console.log(this.b1)
    }, a1Change() {
      this.shi = []
      this.qv = []
      this.b2 = ''
      this.b3 = ''
      this.axios.post(`/com/getSsq?pid=${this.b1}`).then(res => {
        this.shi = res.data.data
      })
      this.axios.post(`/com/getSsqName/${this.b1}`).then(res => {
        this.c1 = res.data
        this.$emit('getSsqName', this.c1)
      })

    }, a2Change() {
      this.qv = []
      this.b3 = ''
      this.axios.post(`/com/getSsq?pid=${this.b2}`).then(res => {
        this.qv = res.data.data
      })
      this.axios.post(`/com/getSsqName/${this.b2}`).then(res => {
        this.c2 = res.data
        console.log(this.c1 + '-' + this.c2)
        this.$emit('getSsqName', this.c1 + '-' + this.c2)
      })
    }, a3Change() {

      this.axios.post(`/com/getSsqName/${this.b3}`).then(res => {
        this.c3 = res.data
        console.log(this.c1 + '-' + this.c2 + '-' + this.c3)
        this.$emit('getSsqName', this.c1 + '-' + this.c2 + '-' + this.c3)
      })
    }
  }, created() {
    this.getSsq()
  }
}
</script>

<template>
  <div>
<!--    <h1>HHHHH</h1>-->
    <el-select v-model="b1" @change="a1Change" placeholder="请选择">
      <el-option
          v-for="item in sheng"
          :key="item.id"
          :label="item.name"
          :value="item.id">
      </el-option>
    </el-select>
    <el-select v-model="b2" @change="a2Change" placeholder="请选择">
      <el-option
          v-for="item in shi"
          :key="item.id"
          :label="item.name"
          :value="item.id">
      </el-option>
    </el-select>
    <el-select v-model="b3" @change="a3Change" placeholder="请选择">
      <el-option
          v-for="item in qv"
          :key="item.id"
          :label="item.name"
          :value="item.id">
      </el-option>
    </el-select>
  </div>
</template>

<style>

</style>