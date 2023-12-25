<template>
  <el-card class="table-card" v-loading="result.loading" body-style="padding:10px;">
    <div slot="header">
      <span class="title">
        {{ $t('dashboard.bug_duration') }}
      </span>
    </div>
    <el-container>
      <ms-chart :options="option" :autoresize="true" style="width: 100%;height: 340px"></ms-chart>
    </el-container>
  </el-card>
</template>

<script>
import MsChart from "@/business/components/common/chart/MsChart";

export default {
  name: "BugDurationBarChart",
  components: {MsChart},
  props: {
    caseOption: {},
  },
  data() {
    return {
      result: {},
      chartData: this.caseOption,
      option: {
        xAxis: {
          name: '（天）',
          type: 'category',
          data: this.caseOption.xAxis,
          nameTextStyle: {
            color: '#DCDCDC'
          }
        },
        yAxis: {
          name: '（单位：个）',
          nameTextStyle: {
            color: '#DCDCDC'
          },
          type: 'value',
          splitNumber: 5,
          splitLine: {
            show: true,
            lineStyle: {
              type: 'dashed'
            }
          }

        },
        series: [
          {
            label: {
             show: true,
             color: "#fff",
            },
            data: this.caseOption.yAxis,
            type: 'bar',
            itemStyle: {
              // color: "#FFE4B5"

                normal: {
                  color: (val) => {
                    console.log("val %o",val)
                    switch(val.name){
                      case "90":
                        return '#F30824';
                      case "30":
                        return "#fac858";
                      case "7":
                        return "#5470c6";
                      case "3":
                        return "#91cc75";
                      default:
                        return "#91cc75";
                    }
                  },

                },

            }
          }
        ]
      }
    }
  },
  watch: {
    caseOption(val) {
      this.option.xAxis.data = val.xAxis;
      this.option.series[0].data = val.yAxis;
    }
  },
  methods:{
    formatSeries(){
      this.option.series.forEach()
    }
  }
}
</script>

<style scoped>
.no-shadow-card {
  -webkit-box-shadow: 0 0px 0px 0 rgba(0, 0, 0, .1);
  box-shadow: 0 0px 0px 0 rgba(0, 0, 0, .1);
}

.el-card /deep/ .el-card__header {
  border-bottom: 0px solid #EBEEF5;
  padding-bottom: 0px;
}
</style>
