<template>
  <div>
    <el-main>
      <test-plan-report-content :is-template="false" :plan-id="planId" :isPdf="true"/>
    </el-main>
  </div>
</template>

<script>
import TestPlanReportContent from "@/business/components/track/plan/view/comonents/report/detail/TestPlanReportContent";
import {getPlanId} from "@/common/js/utils";
export default {
  name: "PdfPlanReportTemplate",
  components: {TestPlanReportContent},
  data() {
    return {
      planId: ''
    }
  },
  created() {
    this.planId = getPlanId();
  }
}
</script>

<style scoped>
</style>
