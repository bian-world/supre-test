<template>
  <el-card class="table-card" body-style="padding:10px;" v-loading="result.loading">
    <div slot="header">
      <span class="title">
        {{ $t('dashboard.api_case_count') }}
      </span>
    </div>
    <el-container>
      <el-aside width="45%">
        <st-rose-pie-chart :data="apiCaseCountData.data" style="width:100%; height:340px"/>
      </el-aside>

      <el-main style="padding-left: 0px;padding-right: 0px;padding-top: 10%">
        <span style="font-size: 12px"
              v-html="$t('api_test.home_page.test_case_details_card.this_week_add_tab',[apiCaseCountData.thisWeekAddedCount])"> </span>
        <span style="font-size: 12px"
              v-html="$t('api_test.home_page.test_case_details_card.this_week_execute_sm_tab',[apiCaseCountData.thisWeekExecutedCount])">  </span>
        <span style="font-size: 12px"
              v-html="$t('api_test.home_page.test_case_details_card.executed_sm_tab',[apiCaseCountData.executedCount])">  </span>
        <br/><br/>
        <el-row align="middle" type="flex" justify="space-around"
                style="background-color: #FAFAFA;height: 150px;width: 90%;margin-left: 0px">
          <el-col :span="9"
                  style="padding: 5px;border-right-style: solid;border-right-width: 2px;border-right-color: #CFCFCF;">
            <span style="margin-left: 20%;font-size: 30px;color:#0099ff">{{ apiCaseCountData.coverageRage }}</span>
            <span style="font-size: 12px;color:#0099ff">%</span><br/>
            <span
              style="font-size: 12px;margin-left: 20%">{{ $t('api_test.home_page.detail_card.rate.coverage') }}</span>

          </el-col>
          <el-col :span="5"
                  style="padding: 5px;border-right-style: solid;border-right-width: 0px;border-right-color: #ECEEF4;">
            <span style="margin-left: 20%;font-size: 20px;color:#bcc0c3">{{apiCaseCountData.uncoverageCount}}</span><br/>
            <span style="font-size: 12px;margin-left: 20%">{{ $t('api_test.home_page.detail_card.uncoverage') }}</span>
          </el-col>
          <el-col :span="5"
                  style="padding: 5px;border-right-style: solid;border-right-width: 0px;border-right-color: #ECEEF4;">
            <span style="margin-left: 20%;font-size: 20px;color:#db3a52"> {{
                apiCaseCountData.coverageCount
              }}</span><br/>
            <span style="font-size: 12px;margin-left: 20%">{{ $t('api_test.home_page.detail_card.coverage') }}</span>
          </el-col>
          <el-col :span="5" style="padding: 5px;">
          </el-col>
        </el-row>
      </el-main>
    </el-container>

  </el-card>
</template>

<script>
import MsInstructionsIcon from "@/business/components/common/components/MsInstructionsIcon";
import MsCountRingChart from "@/business/components/common/chart/MsCountRingChart";
import MsDoughnutPieChart from "@/business/components/common/components/MsDoughnutPieChart";
import StRosePieChart from "@/business/components/common/components/StRosePieChart";

export default {
  name: "ApiCaseCountPieChart",
  components: {StRosePieChart, MsDoughnutPieChart, MsInstructionsIcon},
  props: {
    apiCaseCountData: {},
  },
  data() {
    return {
      result: {}
    }
  },
  methods: {
    redirectPage(clickType) {
      this.$emit("redirectPage", "case", "case", clickType);
    }
  }
}
</script>

<style scoped>
.el-aside {
  line-height: 100px;
  text-align: center;
}

.count-number {
  font-family: 'ArialMT', 'Arial', sans-serif;
  font-size: 33px;
  color: var(--count_number);
  position: relative;
}

.count-number-show {
  margin: 20px auto;
}

.detail-container {
  margin-top: 30px
}

.no-shadow-card {
  -webkit-box-shadow: 0 0px 0px 0 rgba(0, 0, 0, .1);
  box-shadow: 0 0px 0px 0 rgba(0, 0, 0, .1);
}

.default-property {
  font-size: 12px
}

.main-property {
  color: #F39021;
  font-size: 12px
}

.el-card /deep/ .el-card__header {
  border-bottom: 0px solid #EBEEF5;
}

.count-info-div {
  margin: 3px;
}

.count-info-div >>> p {
  font-size: 10px;
}

.info-tool-tip {
  position: absolute;
  top: 0;
}
</style>

