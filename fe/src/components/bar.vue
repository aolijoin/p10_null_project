<template>
  <div>
    <el-button type="primary" @click="qie">切换</el-button>
    <div id="myChart" :style="{width: '95%', height: '800px'}">
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'echarts',
  data() {
    return {
      type: 'line',
      style: 0,
      // msg: 'Welcome to Your Vue.js App',
      title: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
      // ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
      number: [5, 20, 36, 10, 10, 20]
      // [5, 20, 36, 10, 10, 20]
    }
  }, mounted() {
    this.drawLine();
  }, created() {
  }, methods: {
    qie() {
      if (this.style % 2 === 0) {
        this.type = 'bar'
      } else {
        this.type = 'line'
      }
      this.style++
      this.drawLine()
    },
    drawLine() { // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart')) // 绘制图表
      /**
       * 请求返回字段为 value 和 name 的 List的Map集合
       */
      axios.post(`/student/groupMajor`).then(res => {
        let result = res.data.data
        console.log(result)
        this.title = []
        this.number = []
        for (let i = 0; i < result.length; i++) {
          this.title[i] = result[i].name
          this.number[i] = result[i].value
        }
        myChart.setOption({
          title: {
            text: '在Vue中使用echarts'
          },
          tooltip: {},
          xAxis: {
            data: this.title
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: this.type,// bar 柱子图 ,line 线状图
            data: this.number
          }]
        });
      })
    }
  }
}
</script>