<template>
    <el-drawer
      :visible.sync="showDialog"
      :with-header="false"
      :modal-append-to-body="false"
      size="100%"
      ref="drawer"
      v-loading="result.loading">
      <template v-slot:default="scope">
        <el-scrollbar>
          <el-row type="flex" class="head-bar">
            <el-col :span="12">
              <div class="name-edit">
                <el-button plain size="mini" icon="el-icon-arrow-left" @click="handleClose" >{{$t('test_track.return')}}
                </el-button>&nbsp;
                <span class="title">{{plan.name}}</span>


              </div>
            </el-col>
            <el-col :span="12" class="head-right">
              <!--            <el-button v-permission="['PROJECT_TRACK_REPORT:READ+EXPORT']" :disabled="!isTestManagerOrTestUser" plain size="mini" @click="handleExport(report.name)">-->
              <!--              {{$t('test_track.plan_view.export_report')}}-->
              <!--            </el-button>-->
              <test-plan-report-buttons :is-db="false" :plan-id="plan.id" :is-share="false" :report="plan"/>
            </el-col>
          </el-row>
          <div class="container">
            <test-plan-report-content v-if="showReport" :plan-id="plan.id" :is-pdf="false" />
          </div>
        </el-scrollbar>
      </template>
    </el-drawer>
</template>

<script>

import TestPlanReportContent from "@/business/components/track/plan/view/comonents/report/detail/TestPlanReportContent";
import TestPlanReportButtons from "@/business/components/track/plan/view/comonents/report/detail/TestPlanReportButtons";
export default {
  name: "TestPlanReportReview",
  components: {
    TestPlanReportButtons,
    TestPlanReportContent,

  },
  data() {
    return {
      result: {},
      showDialog: false,
      plan: {},
      isTestManagerOrTestUser: false,
      showReport: false,
      originUlr: ''
    }
  },
  mounted() {
    this.isTestManagerOrTestUser = true;
  },
  methods: {
    listenGoBack() {
      //监听浏览器返回操作，关闭该对话框
      if (window.history && window.history.pushState) {
        this.originUlr = document.URL;
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', this.goBack, false);
      }
    },
    goBack() {
      let newUlr = document.URL;
      // 将上一次url设置成列表的url，回退时关闭
      history.pushState(null, null, this.originUlr);
      history.pushState(null, null, newUlr);
      if (document.URL.split("#").indexOf('/plan/all') > -1) {
        history.pushState(null, null, this.originUlr);
        this.handleClose();
      }
    },
    open(plan) {
      this.showReport = false;
      // 每次都重新获取
      this.$nextTick(() => {
        this.showReport = true;
        this.plan = plan;
      });
      this.showDialog = true;
      this.listenGoBack();
    },
    handleClose() {
      window.removeEventListener('popstate', this.goBack, false);
      this.$emit('refresh');
      this.showDialog = false;
    },
  }
}
</script>

<style scoped>

.head-bar {
  background: white;
  /*height: 45px;*/
  /*line-height: 45px;*/
  padding: 0 10px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 0 2px 0 rgba(31, 31, 31, 0.15), 0 1px 2px 0 rgba(31, 31, 31, 0.15);
}

.container {
  height: 100vh;
  background: #F5F5F5;
}

.head-right {
  text-align: right;
  border-width: 0px;
  box-shadow: none;
}

.el-button  {
  margin: 5px 0px 5px 0px;
  border-width: 0px;
}

</style>
