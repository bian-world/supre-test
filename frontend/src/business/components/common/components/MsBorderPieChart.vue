<template>
  <ms-chart v-if="visible && pieData.length > 0" :options="options" :autoresize ="true"  style="width: 100%"/>
</template>

<script>

import MsChart from "@/business/components/common/chart/MsChart";
export default {
  name: "MsBorderPieChart",
  components: {MsChart},
  data() {
    return {
      visible: true,
      autoresize:true,
      options: {
        color:['#fac858','#73c0de','#ee6666', '#91cc75',   '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc','#5470c6'],
        title: {
          text: this.text,
          subtext: this.subtext,
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          right: "1%"
        },
        series: [
          {
            type: 'pie',
            radius: this.radius,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: true,
              position:'outside',
              formatter: '{c}, {d}%',
            },
            labelLine: {
              show: true
            },
            data: this.pieData,
            colorBy: "data"
          }
        ]
      }
    }
  },
  props: {
    text: {
      type: String,
      default: ''
    },
    name: {
      type: String,
      default: ''
    },
    subtext: {
      type: String,
      default: ''
    },
    radius:{
      type: Array,
      default() {
        return ['40%', '70%']
      }
    },
    pieData: {
      type: Array,
      default() {
        return [
          {
            value:235, name:'示例1',
            itemStyle: {
              color: '#67C23A'
            }
          },
          {
            value:274, name:'示例2',
            itemStyle: {
              color: '#E6A23C'
            }
          },
          {
            value:274, name:'示例3',
            itemStyle: {
              color: '#F56C6C'
            }}
        ]

      }
    }
  }
}
</script>

<style scoped>
</style>
