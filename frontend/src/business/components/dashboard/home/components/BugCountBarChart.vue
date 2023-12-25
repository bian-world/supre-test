<template>
  <el-card class="table-card" v-loading="result.loading" body-style="padding:10px;">
    <div slot="header">
      <span class="title">
        {{ $t('dashboard.bug_list') }}
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
  name: "BugCountBarChart",
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
          name: '（开发）',
          type: 'category',
          axisLabel: { interval: 0, rotate: 40 },
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

            data: this.caseOption.yAxis,
            type: 'bar',
            label: {
              show: true,
              position: 'top',
            },
            itemStyle: {
              color: "#FFE4B5"
            }
          }
        ]
      }
    }
  },
  watch: {
    caseOption(val, val2) {
      console.log("val %o ", val2)
      this.option.xAxis.data = val.xAxis;
      this.option.series[0].data = val.yAxis;
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
