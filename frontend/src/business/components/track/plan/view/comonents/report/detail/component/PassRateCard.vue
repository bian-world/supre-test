<template>
  <el-card body-style="padding:10px;" v-loading="result.loading" style="width:100%;height:200px">
    <el-container>
      <el-aside width="45%">
        <st-gauge-chart :data="series" :autoresize="!isPdf"/>
      </el-aside>
      <el-main style="padding-left: 0px;padding-right: 0px;padding-top: 8%">
        <el-row align="middle" type="flex" justify="space-around"
                style="background-color: #FAFAFA;height: 70px;width: 80%;margin-left: 0px">
          <el-col :span="11"
                  style="padding: 5px;border-right-style: solid;border-right-width: 1px;border-right-color: #CFCFCF;">
            <span style="font-size: 12px;margin-left: 15%">{{ caseNumText }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:#0099ff">{{caseNum }}</span>
          </el-col>
          <el-col :span="13"
                  style="padding: 5px;border-right-style: solid;border-right-width: 0px;border-right-color: #ECEEF4;">

            <span style="font-size: 12px;margin-left: 10%">{{ $t('test_track.report.pass_rate') }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:#3fbf51">{{passRate}}%</span><br/>
          </el-col>

        </el-row>
      </el-main>
    </el-container>
  </el-card>
</template>

<script>
import StGaugeChart from "@/business/components/common/components/StGaugeChart";

export default {
  name: "PassRateCard",
  components: {StGaugeChart},
  data() {
    return {
      result: {},
      passRate: 0,
      caseNum: 0,
      caseNumText: this.name,
      series: [
        {
        center: ['50%', '70%'],
        z: 1,
        radius: '120%',
        type: 'gauge',
        startAngle: 180,
        endAngle: 0,
        splitNumber: 0,
        axisLine: {
          lineStyle: {
            opacity: 0.5,
            width: 40,
          }
        },

        splitLine: {
          lineStyle: {
            color: '#FFFFFF',
            width: 0
          }
        },
        axisLabel: {
          formatter: function (value) {
            return ''
          }
        },
      },
        {
          center: ['50%', '70%'],
          z: 2,
          radius: '109%',
          min: 0,
          max: 1,
          type: 'gauge',
          startAngle: 180,
          endAngle: 0,
          splitNumber: 11,
          axisLine: {
            lineStyle: {
              opacity: 0.1,
              width: 15,
              color: [
                [1, '#1C86EE']
              ]
            }
          },
          axisTick: {
            show: false
          }
          ,
          splitLine: {
            distance: -14,
            length: 8,
            lineStyle: {
              color: '#FFFFFF',
              width: 2
            }
          },
          axisLabel: {
            formatter: function (value) {
              return ''
            }
          },
        },
        {
          center: ['50%', '70%'],
          z: 3,
          radius: '120%',
          type: 'gauge',
          startAngle: 180,
          endAngle: 0,
          splitNumber: 0,
          pointer: {
            show: false
          },
          axisLine: {
            lineStyle: {
              opacity: 0.3,
              width: 60,
            }
          },
          splitLine: {
            lineStyle: {
              color: '#FFFFFF',
            }
          },
          axisLabel: {
            show: false
          },
        },
        {
          center: ['50%', '70%'],
          z: 4,
          radius: '109%',
          type: 'gauge',
          startAngle: 180,
          endAngle: 0,
          splitNumber: 0,
          pointer: {
            show: false
          },
          axisLine: {
            lineStyle: {
              opacity: 0,
              width: 30,
            }
          },
          splitLine: {
            lineStyle: {
              color: '#FFFFFF',
              width: 1
            }
          },
          progress: {
            show: true,
            width: 15,
            itemStyle: {
              color: '#1C86EE',
              opacity: 1
            }
          },
          axisLabel: {
            formatter: function (value) {
              return ''
            }
          },
          title: {
            offsetCenter: [0, '-10%'],
            fontSize: 12,
            color: '#B5B5B5',
          },
          detail: {
            fontSize: 25,
            offsetCenter: [0, '-30%'],
            valueAnimation: true,
            formatter: function (value) {
              return value + '%';
            },
            color: 'black'
          },
          data: [{
            value: this.passRate,
            name: '通过率',
          }]
        },
        {
          center: ['50%', '70%'],
          z: 5,
          radius: '109%',
          min: 0,
          max: 1,
          type: 'gauge',
          startAngle: 180,
          endAngle: 0,
          splitNumber: 11,
          axisLine: {
            lineStyle: {
              opacity: 0,
              width: 20
            }
          },
          axisTick: {
            show: false
          }
          ,
          splitLine: {
            distance: -17,
            length: 9,
            lineStyle: {
              color: '#FFFFFF',
              width: 2
            }
          },
          axisLabel: {
            formatter: function (value) {
              return ''
            }
          },
        }]
    }
  },
  props: {
    isPdf: Boolean,
    data:{
      type: Array,
      default() {
        return []
      },
    },
    name: {
      type: String,
      default() {
        return '用例数'
      },
    }
  },
  watch: {
    data() {
      this.reload();
    }
  },
  created() {
    this.reload();
  },
  mounted() {
    this.reload();
  },
  methods: {
    reload() {
      this.setFormatterFunc();
      this.visible = false;
      this.$nextTick(() => {
        this.visible = true;
      });
    },
    setFormatterFunc() {

      let count = 0;

      this.data.forEach(item => {
        count += item.value;
      });

      this.caseNum = count;

      let pass = this.data.filter(item => item.name === '通过')
      let target = pass.length > 0? pass[0].value: 0

      this.passRate =  ((target / count) * 100).toFixed(0);
      this.series[3].data[0].value = this.passRate
      // if(this.isPdf){
      //   this.series.forEach(serie => serie.center[0] = '80%')
      // }
    },
  }
}
</script>

<style scoped>


.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
