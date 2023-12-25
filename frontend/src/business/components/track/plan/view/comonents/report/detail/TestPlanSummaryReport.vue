<template>
  <test-plan-report-container id='summary' :title="$t('test_track.report.report_summary')" style="margin-bottom: 40px">
    <template v-slot:title v-if="!isTemplate && !isShare && !isDb && !isPdf">
      <img src="../../../../../../../../assets/bulletpoint.png" style="margin-left: 10px;" class="icon" alt="" >
      <el-link class="edit-link" v-if="!isEdit" @click="isEdit = true">
        {{ $t('commons.edit') }}
      </el-link>
      <el-link class="edit-link" v-if="isEdit" @click="saveSummary">
        {{ $t('commons.save')}}
      </el-link>
    </template>
    <el-form class="form-info" v-loading="result.loading" >
      <div v-if="!isEdit">
        <div class="rich-text-content" v-html="report.summary"></div>
      </div>
      <div v-else>
        <ms-rich-text :content.sync="report.summary"/>
      </div>
    </el-form>
  </test-plan-report-container>

</template>

<script>
import MsFormDivider from "@/business/components/common/components/MsFormDivider";
import {editPlanReport} from "@/network/test-plan";
import TestPlanReportContainer
  from "@/business/components/track/plan/view/comonents/report/detail/TestPlanReportContainer";
import MsRichText from "@/business/components/track/case/components/MsRichText";
export default {
  name: "TestPlanSummaryReport",
  components: {MsRichText, TestPlanReportContainer, MsFormDivider},
  props: {
    planId: String,
    report: Object,
    isTemplate: Boolean,
    isShare: Boolean,
    isDb: Boolean,
    isPdf: Boolean,
  },
  data() {
    return {
      result: {},
      isEdit: false
    }
  },
  methods: {
    saveSummary() {
      editPlanReport({
        id: this.planId,
        reportSummary: this.report.summary ? this.report.summary : ''
      });
      this.isEdit = false;
    }
  }
}
</script>

<style scoped>
.el-link >>> .el-link--inner {
  line-height: 25px;
  font-size: 12px;
  height: 29px;
  color: #0099ff;
}

.form-info {
  padding: 25px;
  background-color: #FAFAFA;
  font-size: 10px;
}

.edit-link {
  /*margin-left: 5px;*/

}

.rich-text-content >>> .table td {
  border: solid 1px #e6e6e6;
  min-width: 2em;
  padding: .4em;
  font-size: 10px
}

.icon {
  width: 14px;
  margin-left: 10px;
  margin-right: 2px;
}
</style>
