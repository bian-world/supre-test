<template>
  <div v-loading="result ? result.loading : false" class="head-bar head-right">
<el-row  type="flex" justify="space-between" align="middle">
      <el-col  >
      <el-popover
        placement="right"
        width="300">
        <p>{{shareUrl}}</p>
        <span style="color: red;float: left;margin-left: 10px;" v-if="application.typeValue">{{ $t('commons.validity_period')+application.typeValue}}</span>
        <div style="text-align: right; margin: 0">
          <el-button type="primary" size="mini" :disabled="!shareUrl"
                     v-clipboard:copy="shareUrl">{{ $t("commons.copy") }}</el-button>
        </div>
        <el-button  icon="el-icon-share" slot="reference" :disabled="!isTestManagerOrTestUser"
                   plain size="mini" @click="handleShare()">
          {{ $t('test_track.report.share') }}
        </el-button>
      </el-popover>
      </el-col>
    <el-col >
      <el-button  icon="el-icon-receiving" v-if="!isDb" :disabled="!isTestManagerOrTestUser" plain size="mini" @click="handleSave()">
        {{ $t('commons.save')}}
      </el-button>
    </el-col>
    <el-col>

      <el-dropdown @command="handleDownLoad" plain size="mini" >
        <el-button icon="el-icon-download" :disabled="!isTestManagerOrTestUser" plain size="mini"  >
          {{ $t('commons.export')}}
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="pdf">PDF</el-dropdown-item>
          <el-dropdown-item command="html">HTML</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    </el-col>
<!--    <el-col>-->
<!--      <el-button  icon="el-icon-setting" v-if="!isDb"  :disabled="!isTestManagerOrTestUser" plain size="mini" @click="handleEditTemplate()">-->
<!--        {{ $t('test_track.report.configuration') }}-->
<!--      </el-button>-->
<!--    </el-col>-->
</el-row>
    <test-plan-report-edit :plan-id="planId" :config.sync="report.config" ref="reportEdit"/>
  </div>
</template>

<script>

import TestPlanApiReport from "@/business/components/track/plan/view/comonents/report/detail/TestPlanApiReport";
import {generateShareInfoWithExpired} from "@/network/share";
import TestPlanReportEdit
  from "@/business/components/track/plan/view/comonents/report/detail/component/TestPlanReportEdit";
import {editPlanReport, saveTestPlanReport} from "@/network/test-plan";
import {getCurrentProjectID} from "@/common/js/utils";
import {CURRENT_LANGUAGE} from "@/i18n/i18n";
export default {
  name: "TestPlanReportButtons",
  components: {
    TestPlanReportEdit,
    TestPlanApiReport,
    },
  props: {
    planId:String,
    isShare: Boolean,
    report: Object,
    isDb: Boolean
  },
  data() {
    return {
      result: {},
      isTestManagerOrTestUser: true,
      shareUrl: '',
      application:{},
    };
  },
  methods: {
    handleEditTemplate() {
      this.$refs.reportEdit.open();
    },
    handleShare(){
      let pram = {};
      pram.customData = this.planId;
      pram.shareType = 'PLAN_REPORT';
      if (this.isDb) {
        pram.customData = this.report.id;
        pram.shareType = 'PLAN_DB_REPORT';
      }
      pram.lang = localStorage.getItem(CURRENT_LANGUAGE);
      generateShareInfoWithExpired(pram, (data) => {
        let thisHost = window.location.host;
        this.shareUrl = thisHost + "/sharePlanReport" + data.shareUrl;
      });
      this.getProjectApplication();
    },
    getProjectApplication(){
      this.$get('/project_application/get/' + getCurrentProjectID()+"/TRACK_SHARE_REPORT_TIME", res => {
        if(res.data){
          let quantity = res.data.typeValue.substring(0, res.data.typeValue.length - 1);
          let unit = res.data.typeValue.substring(res.data.typeValue.length - 1);
          if(unit==='H'){
            res.data.typeValue = quantity+this.$t('commons.date_unit.hour');
          }else
          if(unit==='D'){
            res.data.typeValue = quantity+this.$t('commons.date_unit.day');
          }else
          if(unit==='M'){
            res.data.typeValue = quantity+this.$t('commons.date_unit.month');
          }else
          if(unit==='Y'){
            res.data.typeValue = quantity+this.$t('commons.date_unit.year');
          }
          this.application = res.data;
        }
      });
    },
    handleSave() {
      let param = {};
      this.buildParam(param);
      editPlanReport({id: this.planId, reportSummary: this.report.summary ? this.report.summary : ''}, () => {
        saveTestPlanReport(this.planId, () => {
          this.$success(this.$t('commons.save_success'));
        });
      });
    },
    buildParam(param) {
      param.name = this.report.name;
      param.id = this.report.id;
      param.isNew = true;
    },
    handleDownLoad(command){
      if(command && command === 'pdf'){this.handleExportPdf()}
      else if(command &&  command === 'html'){this.handleExportHtml()}
      else{console.log('暂不支持此格式的导出')}
    },
    handleExportHtml() {
      let config = {
        url: '/test/plan/report/export/' + this.planId,
        method: 'get',
        responseType: 'blob'
      };
      if (this.isDb) {
        config.url = '/test/plan/report/db/export/' + this.report.id;
      }
      if (this.isShare) {
        config.url = '/share' + config.url;
      }
      config.url = config.url + '/' + localStorage.getItem(CURRENT_LANGUAGE);
      this.result = this.$download(config, this.report.name + '.html',()=>{
        this.$success(this.$t("organization.integration.successful_operation"));
      });
    },
    handleExportPdf() {
      let config = {
        url: '/test/plan/report/exportPdf/' + this.planId,
        method: 'get',
        responseType: 'blob'
      };
      config.url = config.url + '/' + localStorage.getItem(CURRENT_LANGUAGE);
      this.result = this.$download(config, this.report.name + '.pdf',()=>{
        this.$success(this.$t("organization.integration.successful_operation"));
      });
    },
  }
}
</script>

<style scoped>

#header-top {
  width: 100%;
  border-width: 0px;
  padding: 0 10px;
  background-color: var(--color);
  color: rgb(245, 245, 245);
  font-size: 14px;
  height: 40px;
}

.head-right {
  text-align: right;
  float: right;
}

/*.head-bar .el-button {*/
/*  position: relative;*/
/*  z-index: 99;*/
/*  margin-bottom: 10px;*/
/*  width: 80px;*/
/*  margin-right: 10px;*/
/*  display: block;*/
/*}*/

.el-button  {
  margin: 5px 0px 5px 0px;
  border-width: 0px;
}

/*.head-bar {*/
/*  position: fixed;*/
/*  right: 10px;*/
/*  padding: 20px;*/
/*}*/

</style>
