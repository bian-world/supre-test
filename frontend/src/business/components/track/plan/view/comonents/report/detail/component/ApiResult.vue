<template>
<!--  <div>-->
<!--    <el-row >-->
<!--      <el-col :span="12" v-if="caseCharData && caseCharData.length > 0">-->
<!--        <st-gauge-chart></st-gauge-chart>-->
<!--&lt;!&ndash;        <ms-doughnut-pie-chart style="margin-right: 200px" :name="$t('api_test.home_page.detail_card.single_case')" :data="caseCharData" ref="functionChar"/>&ndash;&gt;-->
<!--      </el-col>-->
<!--      <el-col :span="12" v-if="scenarioCharData && scenarioCharData.length > 0">-->
<!--        <api-scenario-char-result :name="$t('test_track.plan.test_plan_api_scenario_count')" :data="scenarioCharData"/>-->
<!--        <api-scenario-char-result style="margin-top: -50px;" :name="$t('test_track.plan.test_plan_component_case_count')" :data="stepCharData"/>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--  </div>-->

  <ms-container>
    <ms-main-container>
      <el-row :gutter="10">
        <el-col :span="12" v-if="caseCharData && caseCharData.length > 0">
          <div class="square">
            <pass-rate-card :is-pdf="isPdf" :data="caseCharData" :name="$t('api_test.home_page.detail_card.single_case')"></pass-rate-card>
          </div>
        </el-col>

        <el-col :span="12" v-if="scenarioCharData && scenarioCharData.length > 0">
          <div class="square">
            <pass-rate-card :is-pdf="isPdf" :data="scenarioCharData" :name="$t('test_track.plan.test_plan_api_scenario_count')"></pass-rate-card>
          </div>
        </el-col>
      </el-row>

      <el-row :gutter="10" style="margin-top: 10px">
        <el-col :span="12" v-if="stepCharData && stepCharData.length > 0">
          <div class="square" >
            <pass-rate-card :is-pdf="isPdf" :data="stepCharData" :name="$t('test_track.plan.test_plan_component_case_count')"></pass-rate-card>

          </div>
        </el-col>

        <el-col :span="12">
          <div class="square">
<!--            <case-increased-line-chart :case-option="caseIncreasedOption" class="track-card"/>-->
          </div>
        </el-col>
      </el-row>

    </ms-main-container>
  </ms-container>
</template>

<script>

import MsPieChart from "@/business/components/common/components/MsPieChart";
import MsDoughnutPieChart from "@/business/components/common/components/MsDoughnutPieChart";
import ApiScenarioCharResult
  from "@/business/components/track/plan/view/comonents/report/detail/component/ApiScenarioCharResult";
import StGaugeChart from "@/business/components/common/components/StGaugeChart";
import PassRateCard from "@/business/components/track/plan/view/comonents/report/detail/component/PassRateCard";
import MsContainer from "@/business/components/common/components/MsContainer";
import MsMainContainer from "@/business/components/common/components/MsMainContainer";
export default {
  name: "ApiResult",
  components: {
    MsMainContainer,
    MsContainer, PassRateCard, StGaugeChart, ApiScenarioCharResult, MsDoughnutPieChart, MsPieChart},
  data() {
    return {
      caseDataMap: new Map([
        ["success", {name: this.$t('test_track.plan_view.pass'), itemStyle: {color: '#67C23A'}}],
        ["Success", {name: this.$t('test_track.plan_view.pass'), itemStyle: {color: '#67C23A'}}],
        ["Pass", {name: this.$t('test_track.plan_view.pass'), itemStyle: {color: '#67C23A'}}],
        ["error", {name: this.$t('test_track.plan_view.failure'), itemStyle: {color: '#F56C6C'}}],
        ["Error", {name: this.$t('test_track.plan_view.failure'), itemStyle: {color: '#F56C6C'}}],
        ["Fail", {name: this.$t('test_track.plan_view.failure'), itemStyle: {color: '#F56C6C'}}],
        ["Failure", {name: this.$t('test_track.plan_view.failure'), itemStyle: {color: '#F56C6C'}}],
        ["Prepare", {name: this.$t('api_test.home_page.detail_card.unexecute'), itemStyle: {color: '#909399'}}],
        ["Underway", {name: this.$t('api_test.home_page.detail_card.unexecute'), itemStyle: {color: '#909399'}}],
        ["errorReportResult", {name: this.$t('error_report_library.option.name'), itemStyle: {color: '#F6972A'}}],
      ]),
      caseCharData: [],
      scenarioCharData: [],
      stepCharData: [],
      isShow: true
    }
  },
  props: {
    isPdf: Boolean,
    apiResult: {
      type: Object,
      default() {
        return {
          caseData: [],
          issueData: []
        }
      }
    }
  },
  watch: {
    apiResult() {
      this.getCaseCharData();
    }
  },
  created() {
    this.getCaseCharData();
  },
  methods: {
    getCaseCharData() {
      let caseCharData = [];
      if (this.apiResult.apiCaseData) {
        this.apiResult.apiCaseData.forEach(item => {
          let data = this.getDataByStatus(item.status);
          data.value = item.count;
          caseCharData.push(data);
        });
      }
      this.caseCharData = caseCharData;

      let apiScenarioData = [];
      if (this.apiResult.apiScenarioData) {
        this.apiResult.apiScenarioData.forEach(item => {
          let data = this.getDataByStatus(item.status);
          data.value = item.count;
          apiScenarioData.push(data);
        });
      }

      let stepCharData = [];
      for (let i = 0; i < this.apiResult.apiScenarioStepData.length; i++) {
        let stepItem = this.apiResult.apiScenarioStepData[i];
        let data = this.getDataByStatus(stepItem.status);
        data.value = stepItem.count;
        stepCharData.push(data);
      }

      this.scenarioCharData = apiScenarioData;
      this.stepCharData = stepCharData;
    },
    getDataByStatus(status) {
      let tmp = this.caseDataMap.get(status);
      if (!tmp) {
        tmp = this.caseDataMap.get('Prepare');
      }
      let data = {};
      Object.assign(data, tmp);
      return data;
    }
  }
}
</script>

<style scoped>
.square {
  width: 100%;
  height: 200px;
}

</style>
