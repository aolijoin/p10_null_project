<template>
  <div>
    <el-row>
      <el-col :span="8">
        <div class="grid-content bg-purple">&nbsp;</div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple-light">
          <el-card>
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="所属公司">
                <el-input v-model="form.province"></el-input>
              </el-form-item>
              <el-form-item label="全宗号">
                <el-input v-model="form.address"></el-input>
              </el-form-item>
              <el-form-item label="分类">
                <el-select v-model="form.region" placeholder="请选择分类">
                  <el-option label="党群工作" value="党群工作"></el-option>
                  <el-option label="行政管理" value="行政管理"></el-option>
                  <el-option label="经营管理" value="经营管理"></el-option>
                  <el-option label="财务审计" value="财务审计"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="年度">
                <el-select v-model="form.year" placeholder="请选择年度">
                  <el-option label="2023" value="2023"></el-option>
                  <el-option label="2022" value="2022"></el-option>
                  <el-option label="2021" value="2021"></el-option>
                  <el-option label="2020" value="2020"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="盒号">
                <el-input v-model="form.hehao"></el-input>
              </el-form-item>
              <el-form-item label="件号">
                <el-input v-model="form.jihao"></el-input>
              </el-form-item>
              <el-form-item label="档号">
                <el-input v-model="form.dh"></el-input>
              </el-form-item>
              <el-form-item label="题名">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item label="文号">
                <el-input v-model="form.wh"></el-input>
              </el-form-item>
              <el-form-item label="责任者">
                <el-input v-model="form.zrz"></el-input>
              </el-form-item>
              <el-form-item label="成文日期">
                <el-date-picker
                    v-model="form.zdrg"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="保管期限">
                <el-select v-model="form.bgqx" placeholder="请选择分类">
                  <el-option label="永久" value="永久"></el-option>
                  <el-option label="30年" value="30年"></el-option>
                  <el-option label="10年" value="10年"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="密级">
                <el-select v-model="form.mj" placeholder="请选择分类">
                  <el-option label="秘密" value="秘密"></el-option>
                  <el-option label="机密" value="机密"></el-option>
                  <el-option label="绝密" value="绝密"></el-option>
                </el-select>
                <el-upload
                    class="upload-demo"
                    action="http://localhost:8080/api/goods/upload"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    multiple :limit="3">
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>
              </el-form-item>
              <el-form-item>
                <el-table
                    :data="tableData"
                    border
                    style="width: 100%">
                  <el-table-column
                      fixed
                      prop="date"
                      label="序号"
                      width="50">
                  </el-table-column>
                  <el-table-column
                      prop="name"
                      label="文件名称"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      prop="province"
                      label="文件类型"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      prop="city"
                      label="文件大小"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      fixed="right"
                      label="操作"
                      width="100">
                    <template slot-scope="scope">
                      <el-button type="text" size="small">下载</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">&nbsp;</div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      value: '',
      form: {
        name: '',
        region: '',
        // date1: '',
        // date2: '',
        // delivery: false,
        // type: [],
        // resource: '',
        // desc: ''
      },
      tableData: [{
        date: '1',
        name: '关于成立成都市就业工作领导小组的通知',
        province: '.docx',
        city: '132KB'
      }, {
        date: '2',
        name: '关于成立成都市就业工作领导小组的通知',
        province: '.docx',
        city: '132KB'
      }, {
        date: '3',
        name: '关于成立成都市就业工作领导小组的通知',
        province: '.excel',
        city: '1.2MB'
      }, {
        date: '4',
        name: '关于成立成都市就业工作领导小组的通知',
        province: '.excel',
        city: '1.2MB'
      }]
    }
  },
  methods: {

    handlePreview(file) {
      console.log(file);
      this.getFileList()
    },
    handleClick(row) {
      console.log(row);
    },
    onSubmit() {
      // console.log('submit!');
      console.log(this.form)
      this.$axios.post('/api/goods/addGoods', this.form).then(res => {

      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    getFileList() {
      this.$axios.post(`/api/file/getAll`).then(res => {
        let r = res.data.data
        this.tableData = r
      })
    }
  },
  created() {
    this.getFileList()
  }
}
</script>

<style>

</style>
