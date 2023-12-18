<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
          </div>
        </el-col>
        <el-col :span="16">
          <div class="grid-content bg-purple-light">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
              <el-form-item label="文件题名">
                <el-input v-model="form.user" placeholder="文件题名"></el-input>
              </el-form-item>
              <el-form-item label="成文日期">
                <el-date-picker v-model="form.start" type="date" placeholder="选择日期"></el-date-picker>
                到
                <el-date-picker v-model="form.end" type="date" placeholder="选择日期"></el-date-picker>
              </el-form-item>
              <!--              <el-form-item label="档案分类">
                              <el-cascader
                                  v-model="formInline.value"
                                  :options="options">
                              </el-cascader>
                            </el-form-item>-->
              <el-form-item label="档案状态">
                <el-select v-model="form.status" placeholder="请选择">
                  <el-option
                      v-for="item in optionss"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="toAdd">添加文件</el-button>
              </el-form-item>
            </el-form>
            <el-table
                :data="total.rows"
                border
                style="width: 100%">
              <el-table-column
                  fixed
                  prop="id"
                  label="序号"
                  width="100">
              </el-table-column>
              <el-table-column
                  prop="status"
                  label="档案状态"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="province"
                  label="档案来源"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="city"
                  label="文件题名"
                  width="220">
              </el-table-column>
              <el-table-column
                  prop="address"
                  label="全宗号"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="year"
                  label="年度"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="dh"
                  label="档号"
                  width="220">
              </el-table-column>
              <el-table-column
                  prop="wh"
                  label="文号"
                  width="220">
              </el-table-column>
              <el-table-column
                  prop="zrz"
                  label="责任者"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="zdrg"
                  label="归档日期"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="mj"
                  label="密级"
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="bgqx"
                  label="保管期限"
                  width="120">
              </el-table-column>
            </el-table>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.page"
                :page-sizes="[3, 6, 9, 400]"
                :page-size="page.size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total.total">
            </el-pagination>
          </div>
        </el-col>

      </el-row>

    </el-card>
  </div>
</template>

<script>
export default {
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.page.size = val;
      this.getTableAll()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.page.page = val
      this.getTableAll()
    },
    handleClick(row) {
      console.log(row);
    },
    onSubmit() {
      console.log(this.formInline)
      console.log('submit!');
      this.getTableAll()
    },
    toAdd() {
      this.$router.push({path: '/GoodsAdd'});
    },
    handleNodeClick(data) {
      console.log(data);
    }
    ,
    getTableAll() {
      this.$axios.post(`api/goods/getAll?page=${this.page.page}&size=${this.page.size}`, this.form).then(res => {
        this.total.total = res.data.total
        this.total.rows = res.data.rows
        console.log(this.total)

      })
    }
  },

  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      data: [
        {
          label: 'JD公司',
          children: [{
            label: '文书档案',
            children: [{
              label: '2023年'
            }]
          }, {
            label: '会计档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }, {
            label: '基建档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }, {
            label: '设备档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }, {
            label: '照片档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }]
        }, {
          label: '淘宝公司',
          children: [{
            label: '文书档案',
            children: [{
              label: '2023年'
            }]
          }, {
            label: '会计档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }, {
            label: '基建档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }, {
            label: '设备档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }, {
            label: '照片档案',
            children: [{
              label: '2023年'
            }, {
              label: '2022年'
            }, {
              label: '2021年'
            }]
          }]
        }],
      total: {
        total: 0,
        rows: [
          {
            id: '1',
            name: '收集中',
            province: 'OA归档文件',
            city: '关于成立成都市就业工作领导小组的通知',
            address: '0110',
            zt: '上架',
            year: '2021',
            dh: '0110-WS·2021-Y-BGS-0001',
            wh: '省办公厅[2021]001号',
            zrz: '张三',
            zdrq: '2021-1-1',
            mj: '机密',
            bgqx: '永久'
          }, {
            id: '2',
            name: '收集中',
            province: '电子文件登记',
            city: '关于成立成都市就业工作领导小组的通知',
            address: '0110',
            zt: '2021',
            year: '2021',
            dh: '0110-WS·2022-Y-BGS-0001',
            wh: '省办公厅[2021]001号',
            zrz: '张三',
            zdrq: '2021-1-1',
            mj: '秘密',
            bgqx: '30年'
          }]
      }, rows: [],
      page: {
        page: 1, size: 3
      },
      optionss: [
        {
          value: '1',
          label: '收集中'
        }, {
          value: '2',
          label: '归档'
        }],
      value: [],
      options: [
        {
          value: 'zhinan',
          label: 'JD',
          children: [{
            value: 'shejiyuanze',
            label: '文书档案',
            children: [{
              value: 'yizhi',
              label: '2023年'
            }, {
              value: 'fankui',
              label: '2022年'
            }, {
              value: 'xiaolv',
              label: '2021年'
            }]
          }]
        }, {
          value: 'ziyuan',
          label: '淘宝',
          children: [{
            value: 'shejiyuanze',
            label: '文书档案',
            children: [{
              value: 'yizhi',
              label: '2023年'
            }, {
              value: 'fankui',
              label: '2022年'
            }, {
              value: 'xiaolv',
              label: '2021年'
            }]
          }]
        }],
      formInline: {
        // user: '',
        // region: '',
        // start:'',
        // end:''
      },
      tableData: [], form: {}


    }
  }, created() {
    this.getTableAll()
  }
}
</script>

<style>

</style>
