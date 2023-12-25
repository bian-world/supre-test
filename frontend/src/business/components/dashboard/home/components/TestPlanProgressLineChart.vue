<template>
  <el-card class="table-card" v-loading="result.loading" body-style="padding:10px;">
    <div slot="header">
      <span class="title">
        {{ $t('dashboard.test_plan_progress') }}
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
  name: "TestPlanProgressLineChart",
  components: {MsChart},
  props: {
    caseOption: {}
  },
  data() {
    return {
      result: {},
      chartData: {},
      option: {
        xAxis: {
          name: '（日期）',
          type: 'category',
          data: this.caseOption.xAxis,
          nameTextStyle: {
            color: '#DCDCDC'
          }
        },
        yAxis: {
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
          },
          axisLabel: {
            formatter: '{value}%'
          }

        },
        tooltip: { // 鼠标悬浮提示框显示 X和Y 轴数据
          trigger: 'axis',
          valueFormatter: (value) => value + '%'
        },
        legend: {
          data: this.caseOption.legend,
          orient: 'horizontal',
          right: '80',
          icon: 'pin'
        },

        series: this.caseOption.series,
      }
    }
  },
  watch: {
    caseOption(val) {
      console.log(val)
      this.option.xAxis.data = val.xAxis;
      this.option.legend.data = val.legend
      this.option.series = val.series;
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
