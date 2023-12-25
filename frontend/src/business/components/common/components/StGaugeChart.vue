<template>
  <ms-chart v-if="visible && data.length > 0" :options="options" :autoresize="autoresize" style="width: 100%;height:200px"/>
</template>

<script>

import MsChart from "@/business/components/common/chart/MsChart";

export default {
  name: "StGaugeChart",
  components: {MsChart},
  data() {
    return {
      visible: true,
      options: {
        series: this.data
      }
    }
  },
  props: {
    autoresize:{
      type: Boolean,
      default(){
        return true
      }
    },
    data: {
      type: Array,
      default() {
        return [
          {
            radius: '50%',
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            min: 0,
            max: 1,
            splitNumber: 11,
            pointer: {
              show: false
            },
            axisLine: {
              lineStyle: {
                opacity: 1,
                width: 15,
                color: [
                  [1, '#1E90FF']
                ]
              }
            },
            progress: {
              show: true,
              width: 20,
              z: 1,
              itemStyle: {
                color: '#0000CD',
                opacity: 0.2
              }
            },

            axisTick: {
              distance: 1,
              z: 10,
              length: 8,
              lineStyle: {
                color: '#FFFFFF',
              }
            }
            ,
            splitLine: {
              distance: -30,
              length: 50,
              lineStyle: {
                color: '#FFFFFF',
                width: 4
              }
            },
            axisLabel: {
              color: '	#FFFFFF',
              fontSize: 20,
              distance: 20,
              formatter: function (value) {

                return ''
              }

            },
            title: {
              offsetCenter: [0, '-20%'],
              fontSize: 20
            },
            detail: {
              fontSize: 30,
              offsetCenter: [0, '0%'],
              valueAnimation: true,
              formatter: function (value) {
                return Math.round(value * 100) + '分';
              },
              color: 'auto'
            },
            data: [
              {
                value: 0.7,
                name: 'Grade Rating'
              }
            ]
          }]
      }
    }
  }
}
</script>

<style scoped>
</style>
