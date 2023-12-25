<template>
  <el-card class="table-card" v-loading="result.loading" body-style="padding:10px;">
    <div slot="header">
      <span class="title">
        {{ $t('dashboard.case_increased') }}
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
  name: "CaseIncreasedLineChart",
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
          name: '（日期）',
          type: 'category',
          data: this.caseOption.xAxisData,
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
        legend: {
          data: ['新增用例'],
          orient: 'horizontal',
          right: '80',
          icon: 'pin'
        },

        series: [
          {
            name: '新增用例',
            data: this.caseOption.yAxisData,
            type: 'line',
            smooth: true,
            showSymbol: false,
            lineStyle: {      // 阴影部分
              shadowOffsetX: 0, // 折线的X偏移
              shadowOffsetY: 9,// 折线的Y偏移
              shadowBlur: 8,  // 折线模糊
              shadowColor: "#FFE4B5", //折线颜色

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
    caseOption(val) {
      console.log(val)
      this.option.xAxis.data = val.xAxisData;
      this.option.series[0].data = val.yAxisData;
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
