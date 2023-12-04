<script>
import {defineComponent} from 'vue'

export default defineComponent({
  name: "Goods",
  data() {
    return {
      page: {rows: []}, form: {id: '', examineReason: ''}, dialogFormVisible: false
      , goodsList: []
    }
  }, methods: {
    getGoodsList() {
      this.$axios.post(`/goods/getAll`).then(res => {
        this.goodsList = res.data.data
      })
      console.log(this.goodsList)
    }, getTable() {
      this.$axios.post(`/apply/getTable`).then(res => {
        let r = res.data
        this.page.rows = r.rows
      })
    }, addGoods() {
      this.dialogFormVisible = true
    }, addApply() {
      this.$axios.post(`/apply/addApply`, this.form).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.getTable()
          this.$message.success('添加成功')
          this.dialogFormVisible=false
        }
      })
    }
  },
  created() {
    this.getTable()
    this.getGoodsList()
  }
})
</script>

<template>
  <div>
    <h1>G00DS</h1>
    <el-button @click="addGoods">添加</el-button>
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
    </el-table>

    <el-dialog title="添加物资" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="物资名称">
          <el-select v-model="form.goodsName" placeholder="请选择">
            <el-option
                v-for="item in goodsList"
                :key="item.id"
                :label="item.goodsName"
                :value="item.goodsName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物资数量">
          <el-input v-model="form.goodsAmount" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addApply">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>