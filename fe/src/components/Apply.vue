<script>
export default {
  data() {
    return {page: {rows: []}, form: {id: '', examineReason: ''}, dialogFormVisible: false}
  }, methods: {
    getTable() {
      this.$axios.post(`/apply/getTable`).then(res => {
        let r = res.data
        this.page.rows = r.rows
        console.log(this.page.rows)
      })
    }, tongGo(id) {
      this.$axios.post(`apply/tongGo/${id}`).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.getTable()
          this.$message.success(r.date)
        } else {
          this.$message.error(r.msg)
        }
      })
    }, noTongGo(id) {
      this.form.id = id
      this.dialogFormVisible = true
    }, noGo() {
      this.$axios.post(`/apply/noGo`, this.form).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.dialogFormVisible = false
          this.form = {}
          this.$message.success(r.date)
          this.getTable()
        } else {
          this.$message.error(r.msg)
        }
      })
    }
  }, created() {
    this.getTable()
  }
}
</script>

<template>
  <div>
    <h1>apply</h1>
    <el-table :data="page.rows">
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="goodsName" label="物资名称"></el-table-column>
      <el-table-column prop="goodsAmount" label="物资申请数量"></el-table-column>
      <el-table-column prop="goodsId" label="物资编号"></el-table-column>
      <el-table-column prop="goodsApplicantId" label="物资申请人ID"></el-table-column>
      <el-table-column prop="goodsApplicantName" label="物资申请人名字"></el-table-column>
      <el-table-column prop="goodsApplicantStatus" label="申请状态">
        <template slot-scope="scope">
          {{
            scope.row.goodsApplicantStatus === 1 ? '等待审批' : scope.row.goodsApplicantStatus === 2 ? '审批通过' : '审批拒绝'
          }}
        </template>
      </el-table-column>
      <el-table-column prop="examineReason" label="审批结果"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <span v-if="scope.row.goodsApplicantStatus===1">
          <el-button type="primary" @click="tongGo(scope.row.id)">审批通过</el-button>
          <el-button type="danger" @click="noTongGo(scope.row.id)">拒绝通过</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="提示" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="活动名称">
          <el-input v-model="form.examineReason" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="noGo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>