<template>
  <div>
    <!--查询条件部分  searchmap -->
    <el-form ref="form" :inline="true" label-width="80px">
      <el-form-item label="线索内容:">
        <el-input v-model="query.name" placeholder="线索内容"></el-input>
      </el-form-item>
      <el-form-item label="省市区:">
        <el-select v-model="query.sheng" @change="getShi" placeholder="请选择省" clearable size="10">
          <el-option
              v-for="item in shengList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
        <el-select v-model="query.shi" @change="getQv" placeholder="请选择市" clearable size="10">
          <el-option
              v-for="item in shiList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
        <el-select v-model="query.qv" placeholder="请选择区" clearable size="10">
          <el-option
              v-for="item in qvList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="getTable">查询</el-button>
        <el-button type="primary" @click="add()">新增</el-button>
      </el-form-item>
    </el-form>

    <!--table 展示区-->
    <template>
      <el-table
          :data="page.tableData"
          border style="width: 100%">
        <el-table-column
            prop="id"
            label="id"
            width="50">
        </el-table-column>
        <el-table-column
            prop="code"
            label="线索编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="name"
            label="线索内容"
            width="100">
        </el-table-column>
        <el-table-column
            prop="formname"
            label="线索来源"
            width="180">
        </el-table-column>
        <el-table-column
            prop="ssq"
            label="省市区"
            width="100">
        </el-table-column>
        <el-table-column
            prop="person"
            label="反映人"
            width="180">
        </el-table-column>
        <el-table-column
            prop="status"
            label="审核状态"
            width="180">
          <template slot-scope="scope">
            {{ scope.row.status == 0 ? '待审核' : scope.row.status == 1 ? '已审核' : '已办结' }}
          </template>
        </el-table-column>

        <el-table-column
            label="操作">
          <template slot-scope="scope">    
            <el-button @click="dele(scope.row.id)" :disabled="scope.row.status!==0"  type="text"  size="small">删除
            </el-button>
             
            <el-button @click="shenhe(scope.row)"  type="text"  size="small">审核</el-button>
            <el-button @click="banli(scope.row)" :disabled="scope.row.status!==1"  type="text"  size="small">办结
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <div class="pagination-container">
      <!--分页插件 -->
      <el-pagination
          class="pagiantion"
          @size-change="sizeChange"
          @current-change="fetchData"
          :current-page.sync="page.page"
          :page-sizes="[2, 4, 6, 8]"
          :page-size="page.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.total">
      </el-pagination>
    </div>

    <!--新增、修改编辑框-->
    <el-dialog
        title="编辑"
        :visible.sync="dialogVisible"
        width="50%">
                        <span>
                            <el-form ref="form" label-width="100px">
                                <el-input v-model="pojo.id" type="hidden"></el-input>
                                <el-form-item label="线索编号">
                                    <el-input v-model="pojo.code" placeholder="设备编号"></el-input>
                                </el-form-item>
                                <el-form-item label="线索内容">
                                    <el-input v-model="pojo.name" placeholder="项目名称"></el-input>
                                </el-form-item>
                                <el-form-item label="线索提供人">
                                    <el-input v-model="pojo.person" placeholder="线索提供人"
                                    ></el-input>
                                </el-form-item>
                                <el-form-item label="线索来源">
                                     <el-select v-model="pojo.form" placeholder="请选择来源" clearable size="10">
                                        <el-option
                                            v-for="item in formList"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="省市区">
                                    <el-select v-model="pojo.sheng" @change="getShi1" placeholder="请选择省" clearable
                                               size="10">
                                        <el-option
                                            v-for="item in shengList1"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                        </el-option>
                                    </el-select>
                                    <el-select v-model="pojo.shi" @change="getQv1" placeholder="请选择市" clearable
                                               size="10">
                                        <el-option
                                            v-for="item in shiList1"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                        </el-option>
                                    </el-select>
                                    <el-select v-model="pojo.qv" placeholder="请选择区" clearable size="10">
                                        <el-option
                                            v-for="item in qvList1"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                              <!-- <el-form-item label="时间">
                                   <el-date-picker
                                           v-model="pojo.createtime"
                                           type="date"
                                           placeholder="选择日期">
                                   </el-date-picker>
                               </el-form-item>-->
                            </el-form>
                        </span>
      <span slot="footer" class="dialog-footer">
                            <el-button @click="dialogVisible = false">取 消</el-button>
                            <el-button type="primary" @click="save()">保 存</el-button>
                        </span>
    </el-dialog>

    <!--审核编辑框-->
    <el-dialog
        title="审核"
        :visible.sync="shenhe_dialogVisible"
        width="50%">
                        <span>
                            <el-form ref="form" label-width="100px">
                                <el-input v-model="shenhepojo.id" type="hidden"></el-input>
                                <el-form-item label="拒绝理由">
                                    <el-input v-model.trim="shenhepojo.notify" placeholder="拒绝理由"
                                    ></el-input>
                                </el-form-item>
                            </el-form>
                        </span>
      <span slot="footer" class="dialog-footer">
                            <el-button type="primary"
                                       @click="shenheMethod(1)"
                                       :disabled="shenhepojo.notify || shenhepojo.notify==null">审核通过</el-button>
                            <el-button :disabled="!shenhepojo.notify"
                                       @click="shenheMethod(0)">审核拒绝</el-button>
                        </span>
    </el-dialog>

    <!--办结编辑框-->
    <el-dialog
        title="办理"
        :visible.sync="banli_dialogVisible"
        width="50%">
                        <span>
                            <el-form ref="form" label-width="100px">
                                <el-input v-model="banlipojo.id" type="hidden"></el-input>
                                <el-form-item label="基本案情:">
                                    <el-input v-model="banlipojo.name" placeholder="线索名称"></el-input>
                                </el-form-item>
                                <el-form-item label="核处结论:">
                                    <el-radio v-model="banlipojo.jl" label="1">经核实存在欠薪，已支付完毕</el-radio>
                                    <el-radio v-model="banlipojo.jl"
                                              label="2">尚未支付，准备或已申请法院强制执行</el-radio>
                                    <el-radio v-model="banlipojo.jl"
                                              label="3">尚未支付，涉嫌犯罪，已移送公安机关</el-radio>
                                    <el-radio v-model="banlipojo.jl" label="4">已支付欠款或达成还款协议</el-radio>
                                    <el-radio v-model="banlipojo.jl"
                                              label="5">应当或者已经通过劳动争议仲裁解决，已告知反映人</el-radio>
                                    <el-radio v-model="banlipojo.jl" label="6">经核实不存在欠薪情况</el-radio>
                                </el-form-item>
                                <el-form-item label=" 是否办结:">
                                    <el-radio v-model="banlipojo.status" label="1">未办结</el-radio>
                                    <el-radio v-model="banlipojo.status" label="2">已办结</el-radio>
                                </el-form-item>
                                 <el-form-item label="办结时间:">
                                    <el-date-picker
                                        v-model="banlipojo.createtime"
                                        type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-form>
                        </span>
      <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="banlisave()">保存</el-button>
                            <el-button @click="banli_dialogVisible = false">取消</el-button>
                        </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "list",
  data() {
    return {//NP C data
      page: {
        page: 1, size: 4, total: 10,
        tableData: [
          {
            "id": 1,
            "code": '0001',
            "name": '线索1',
            "formname": '人民网',
            "ssq": '河北省-石家庄市-桥西区',
            "person": '张三',
            "status": '1'
          },
          {
            "id": 2,
            "code": '0002',
            "name": '线索2',
            "formname": '区政府',
            "ssq": '河南省-郑州市-金水区',
            "person": '李四',
            "status": '2'
          }
        ],
      },
      dialogVisible: false,
      pojo: {}, query: {sheng: '', shi: '', qv: ''},
      shenhe_dialogVisible: false,
      shenhepojo: {notify: ''},
      banli_dialogVisible: false,
      banlipojo: {
        jl: '1'
      }, formList: [], cjList: [], searchMap: {},
      shengList: [],
      shengList1: [],
      shiList: [],
      shiList1: [],
      qvList: [],
      qvList1: [],
    }
  },
  created() {
    this.getTable()
    this.getForm()
    this.getSheng()
  },
  methods: {

    sizeChange(val) {
      this.page.size = val
      this.getTable()
    }, fetchData(val) {
      this.page.page = val
      this.getTable()
    }, getTable() {
      this.$axios.post(`/clue/getTable/${this.page.page}/${this.page.size}`, this.query).then(res => {
        this.page.total = res.data.total
        this.page.tableData = res.data.rows
      })
    }, getForm() {
      this.$axios.post(`/commons/getFormList`).then(res => {
        this.formList = res.data.data
      })
    }, getSheng() {
      this.$axios.post(`/commons/getSsq?id=`).then(res => {
        this.shengList = res.data.data
        this.shengList1 = res.data.data
      })
    },
    getShi() {
      this.$axios.post(`/commons/getSsq?id=${this.query.sheng}`).then(res => {
        this.shiList = res.data.data
      })
    }, getShi1() {
      this.$axios.post(`/commons/getSsq?id=${this.pojo.sheng}`).then(res => {
        this.shiList1 = res.data.data
      })
    },
    getQv() {
      this.$axios.post(`/commons/getSsq?id=${this.query.shi}`).then(res => {
        this.qvList = res.data.data
      })
    },
    getQv1() {
      this.$axios.post(`/commons/getSsq?id=${this.pojo.shi}`).then(res => {
        this.qvList1 = res.data.data
      })
    },
    add() {
      this.dialogVisible = true
      this.pojo={}
    }, save() {
      this.$axios.post(`/clue/saveClue`, this.pojo).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.$message.success('添加成功');
          this.dialogVisible = false
          this.getTable()
        } else {
          this.$message.error(r.msg);
        }
      })
    },
    shenhe(row) {
      this.shenhepojo.id = row.id
      this.shenhe_dialogVisible = true
      this.shenhepojo.notify = ''
    },
    shenheMethod(status) {
      // this.$message.error(status);
      console.log(this.shenhepojo)
      this.shenhepojo.status = status
      this.$axios.post(`/clue/shenhepojo`, this.shenhepojo).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.shenhe_dialogVisible = false
          this.getTable()
          this.$message.success('修改成功');
        } else {
          this.$message.error(r.msg)
        }
      })

    },
    dele(id) {
      this.$axios.post(`/clue/dele?id=${id}`).then(res => {
        let r = res.data
        if (r.code === 200) {
          this.page.page = 1
          this.$message.success('删除成功')
          this.getTable()
        } else {
          this.$message.error(r.msg)
        }
      })
    },    //NP C methods
    banli(row) {
      this.banli_dialogVisible = true
      this.banlipojo.id = row.id
      this.banlipojo.jl = '1'
      this.banlipojo.name = row.name
      this.banlipojo.person = row.person
    },
    banlisave() {
      console.log(this.banlipojo)
      this.$axios.post(`/notify/save`, this.banlipojo).then(res => {
        let r = res.data
        if (r.code === 200) {
          console.log(r)
          this.banli_dialogVisible = false
          this.banlipojo = {}
          this.$router.push('p10notify')
        } else {
          this.$message.error(r.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
