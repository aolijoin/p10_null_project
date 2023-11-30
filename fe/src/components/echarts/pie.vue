<template>
  <div id="myChart" :style="{width: '95%', height: '800px'}"></div>
</template>
<script>
import axios from "axios";
export default {
  name: 'echarts',
  data() {
    return {
      title: '饼状图',
      // name: 'Access From',
      name: '员工管理图',
      pieData: [
        {value: 1048, name: 'Search Engine'},
        {value: 735, name: 'Direct'},
        {value: 580, name: 'Email'},
        {value: 484, name: 'Union Ads'},
        {value: 300, name: 'Video Ads'}
      ],
    }
  }, mounted() {
    this.drawLine();
  }, created() {
  }, methods: {
    drawLine() { // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart')) // 绘制图表
      /**
       * 请求返回字段为 value 和 name 的 List的Map集合
       */
      axios.post(`/api/test`).then(res => {
        let result = res.data.data
        this.pieData = result
        myChart.setOption(
            {
              title: {
                text: this.title,
                subtext: 'Fake Data',
                left: 'center'
              },
              tooltip: {
                trigger: 'item'
              },
              legend: {
                orient: 'vertical',
                left: 'left'
              },
              series: [
                {
                  name: this.name,
                  type: 'pie',
                  radius: '50%',
                  data: this.pieData,
                  emphasis: {
                    itemStyle: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            });
      })

    }
  }
}
</script>
