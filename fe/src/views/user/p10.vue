<template>
  <div>
    <!--查询条件部分  searchmap -->
    <el-form ref="form" :inline="true" label-width="80px">
      <el-form-item label="线索内容:">
        <el-input v-model="query.xname" placeholder="线索内容"></el-input>
      </el-form-item>
      <el-form-item label="省市区:">
        <el-select v-model="cjList[0]" @change="getSsqSheng" placeholder="请选择省" clearable size="10">
          <el-option
              v-for="item in per"
              :key="item.id"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
        <el-select v-model="cjList[1]" @change="getSsqShi" placeholder="请选择市" clearable size="10">
          <el-option
              v-for="item in shi"
              :key="item.id"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
        <el-select v-model="cjList[2]" placeholder="请选择区" clearable size="10">
          <el-option
              v-for="item in qv"
              :key="item.id"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="getTableData">查询</el-button>
        <el-button type="primary" @click="add()">新增</el-button>
      </el-form-item>
    </el-form>

    <!--table 展示区-->
    <template>
      <el-table
          :data="searchMap.tableData">
        <!--        border style="width: 100%"-->
        <el-table-column prop="id" label="id" width="50"></el-table-column>
        <el-table-column prop="xno" label="线索编号" width="100"></el-table-column>
        <el-table-column prop="xname" label="线索内容" width="100"></el-table-column>
        <el-table-column prop="xfrom" label="线索来源" width="180"></el-table-column>
        <el-table-column prop="ssq" label="省市区" width="100"></el-table-column>
        <el-table-column prop="person" label="反映人" width="180"></el-table-column>
        <el-table-column
            prop="status"
            label="审核状态"
            width="180">
          <!--          :formatter="statusFormat"-->
          <template slot-scope="scope">{{
              scope.row.status === 0 ? '待审核' : scope.row.status === 1 ? '已审核' : '已办结'
            }}
          </template>
        </el-table-column>

        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button @click="dele(scope.row.id)" type="text" size="small">删除</el-button>
            <el-button @click="shenhe(scope.row)" type="text" size="small">审核</el-button>
            <el-button @click="banli(scope.row)" type="text" size="small">办结</el-button>
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
          :current-page.sync="searchMap.page"
          :page-sizes="[2, 4, 6, 8]"
          :page-size="searchMap.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="searchMap.total">
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
                                    <el-input v-model="pojo.xno" placeholder="设备名称"></el-input>
                                </el-form-item>
                                <el-form-item label="线索内容">
                                    <el-input v-model="pojo.xname" placeholder="项目编码"></el-input>
                                </el-form-item>
                              <el-form-item label="反映人">
                                    <el-input v-model="pojo.person" placeholder="反映人"></el-input>
                                </el-form-item>
                                <el-form-item label="线索来源">
                                     <el-select v-model="pojo.xfrom" placeholder="请选择来源" clearable size="10">
                                        <el-option
                                            v-for="item in forms"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="省市区">
                                    <el-select v-model="temp.sheng" @change="tempGetSheng" placeholder="请选择省"
                                               clearable size="10">
                                        <el-option
                                            v-for="item in per1"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.name">
                                        </el-option>
                                    </el-select>
                                    <el-select v-model="temp.shi" @change="tempGetShi" placeholder="请选择市" clearable
                                               size="10">
                                        <el-option
                                            v-for="item in shi1"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.name">
                                        </el-option>
                                    </el-select>
                                    <el-select v-model="temp.qv" placeholder="请选择区" clearable size="10">
                                        <el-option
                                            v-for="item in qv1"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                              <!-- <el-form-item label="时间">
                                   <el-date-picker
                                           v-model="pojo.createtime"
                                           type="date" @input="onInput()"
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
                                    <el-input v-model="shenhepojo.notify" placeholder="拒绝理由"
                                    ><!--@input="onInput()"--></el-input>
                                </el-form-item>
                            </el-form>
                        </span>
      <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="shenheMethod(1)">审核通过</el-button>
                            <el-button @click="shenheMethod(0)">审核拒绝</el-button>
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
                                    <el-input v-model="banlipojo.xName" placeholder="线索名称"
                                    ><!--@input="onInput()"--></el-input>
                                </el-form-item>
                                <el-form-item label="核处结论:">
                                    <el-radio v-model="banlipojo.notify"
                                              label="经核实存在欠薪，已支付完毕">经核实存在欠薪，已支付完毕</el-radio>
                                    <el-radio v-model="banlipojo.notify"
                                              label="尚未支付，准备或已申请法院强制执行">尚未支付，准备或已申请法院强制执行</el-radio>
                                    <el-radio v-model="banlipojo.notify"
                                              label="尚未支付，涉嫌犯罪，已移送公安机关">尚未支付，涉嫌犯罪，已移送公安机关</el-radio>
                                    <el-radio v-model="banlipojo.notify"
                                              label="已支付欠款或达成还款协议">已支付欠款或达成还款协议</el-radio>
                                    <el-radio v-model="banlipojo.notify"
                                              label="应当或者已经通过劳动争议仲裁解决，已告知反映人">应当或者已经通过劳动争议仲裁解决，已告知反映人</el-radio>
                                    <el-radio v-model="banlipojo.notify"
                                              label="经核实不存在欠薪情况">经核实不存在欠薪情况</el-radio>
                                </el-form-item>
                                <el-form-item label=" 是否办结:">
                                    <el-radio v-model="banlipojo.status" label="1">未办结</el-radio>
                                    <el-radio v-model="banlipojo.status" label="2">已办结</el-radio>
                                </el-form-item>
                                 <el-form-item label="办结时间:">
                                    <el-date-picker
                                        v-model="pojo.createtime"
                                        type="date"
                                        placeholder="选择日期"><!--@input="onInput()"-->
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
import axios from "axios";

export default {
  name: "list",
  data() {
    return {
      forms: [], per: [], per1: [], shi: [], qv: [], qv1: [], shi1: [],
      temp: {},
      query: {},
      searchMap: {
        page: 1,
        size: 2, total: 10,
        tableData: [
          {
            "id": 1,
            "xNo": '0001',
            "xName": '线索1',
            "xFrom": '人民网',
            "ssq": '河北省-石家庄市-桥西区',
            "person": '张三',
            "status": '1'
          },
          {
            "id": 2,
            "xNo": '0002',
            "xName": '线索2',
            "xFrom": '区政府',
            "ssq": '河南省-郑州市-金水区',
            "person": '李四',
            "status": '2'
          }
        ],
      },
      dialogVisible: false,
      pojo: {},
      shenhe_dialogVisible: false,
      shenhepojo: {},
      banli_dialogVisible: false,
      banlipojo: {},
      cjList: []
    }
  },
  methods: {
    shenheMethod(status) {
      console.log(this.shenhepojo)

      axios.post(`/notify/byIdStatus/${status}`, this.shenhepojo).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success('操作成功')
          this.shenhe_dialogVisible = false
          this.getTableData()
        } else {
          this.$message.error("操作失败")
        }
      })
    },
    dele(id) {
      axios.post(`/clue/del/${id}`).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success('操作成功')
          this.getTableData()
        } else {
          this.$message.error(result.msg)
        }
      })
    },
    banlisave(status) {
      console.log(this.banlipojo)
      axios.post(`/notify/byIdStatus/${this.banlipojo.status}`, this.banlipojo).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.banli_dialogVisible = false
          this.$router.push('p10notify')
        } else {
          this.$message.error(result.msg)
        }
      })

    },
    banli(row) {
      console.log(row)
      console.log('row:' + row)
      this.banlipojo.userName = row.person
      this.banlipojo.id = row.id
      this.banli_dialogVisible = true
    },
    shenhe(row) {
      console.log(row)
      this.shenhe_dialogVisible = true
      this.shenhepojo.id = row.id
      this.shenhepojo.userName = row.person
    },
    save() {
      this.pojo.ssq = this.temp.sheng + '-' + this.temp.shi + '-' + this.temp.qv
      axios.post(`/clue/addPojo`, this.pojo).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success('添加成功')
          this.dialogVisible = false
          this.getTableData()
        } else {
          this.$message.error(result.msg)
        }

      })
    },
    tempGetShi() {
      axios.post(`/com/getSsqShi/${this.temp.shi}`).then(res => {
        this.qv1 = res.data.data
      })

    }, tempGetSheng() {
      this.shi1 = []
      this.temp.shi = ''
      axios.post(`/com/getSsqShi/${this.temp.sheng}`).then(res => {
        this.shi1 = res.data.data
      })

    },
    add() {
      this.dialogVisible = true
      this.pojo = {}
    },
    getTableData() {
      this.query.ssq = this.cjList.join('-')
      axios.post(`clue/findAllTable/${this.searchMap.page}/${this.searchMap.size}`, this.query).then(res => {
        let result = res.data
        this.searchMap.tableData = result.rows
        this.searchMap.total = result.total
      })
    }, getSsqAll() {
      axios.post(`com/getSsqPer`).then(res => {
        this.per = res.data.data
        this.per1 = res.data.data
      })
    }, getFormsAll() {
      axios.post(`com/getFormsAll`).then(res => {
        this.forms = res.data.data
      })
    }, getSsqSheng() {
      this.cjList[1] = ''
      this.cjList[2] = ''
      this.shi = []
      this.qv = []
      axios.post(`com/getSsqShi/${this.cjList[0]}`).then(res => {
        this.shi = res.data.data
      })
    }, getSsqShi() {
      this.cjList[2] = ''
      this.qv = []
      axios.post(`com/getSsqShi/${this.cjList[1]}`).then(res => {
        this.qv = res.data.data
      })
    }, sizeChange(val) {
      this.searchMap.size = val
      this.getTableData()
    }, fetchData(val) {
      this.searchMap.page = val
      this.getTableData()
    }

  },
  created() {
    this.getSsqAll()
    this.getFormsAll()
    this.getTableData()
  },
}
</script>

<style scoped>
/*
         .__.
        /´¯/)
       /—-/
      /—-/
     /´¯/'--'/´¯`·_
    /'/--/—-/—--/¨¯\
   ('(———- ¯~/'--')
    \————-'—--/
    '\'————_-·´
      \———--(Gh\
       \———--(Gh\

*/
</style>
