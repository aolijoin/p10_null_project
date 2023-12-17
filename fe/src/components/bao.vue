<script>

export default {
  name: 'bao',
  data() {
    return {
      form: {}, major: [{id: 1, name: '大数据'}, {id: 2, name: '人工智能'}],
    }
  }, methods: {
    test() {
      this.$axios.post(`/api/major/getAll`).then(res => {
        // this.$message.success(res.data)
        this.major = res.data.data
      })
    }, onSubmit() {
      console.log(this.form)
      this.$axios.post(`/api/student/addStudent`, this.form).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.$message.success(r.date)
        } else {
          this.$message.success(r.mag)
        }
      })
    }
  }, created() {
    this.test()
  }
}
</script>

<template>
  <div id="home">
<!--    <el-button type="primary" @click="test">点击测试</el-button>-->
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model.trim="form.name"></el-input>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model.trim="form.personId"></el-input>
      </el-form-item>
      <el-form-item label="报考专业">
        <el-select v-model="form.mid" placeholder="请选择你要报考的专业">
          <el-option v-for="n in major" :label="n.name" :value="n.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="练习电话">
        <el-input v-model.number="form.phone" type="number"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">报 考</el-button>
        <el-button>返 回</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<style>

</style>
