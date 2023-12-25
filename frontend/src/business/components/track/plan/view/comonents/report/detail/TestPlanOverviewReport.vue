<template>
  <test-plan-report-container id="overview" :title="$t('test_track.report.overview')" >
    <el-form class="form-info" v-loading="result.loading">

      <el-row type="flex" justify="space-around" align="middle">
        <el-col :span="12" style="background-color: ghostwhite ; margin-right: 10px; height:100px" class="center">
          <el-col :span="4">
            <img src="../../../../../../../../assets/icon_rili.png" style="margin-left: 70%;" class="icon" alt="">
          </el-col>
          <el-col>
            <el-row>
              <el-col :span="11" class="center">
                <span style="font-size: 20px;">{{ startTime }}</span><br/>
              </el-col>
              <el-col :span="2">
                <span style="font-size: 20px;">~</span>
              </el-col>
              <el-col :span="11" v-if="endTime" style="font-size: 20px;">
                <span>{{ endTime }}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="11" class="center font-char" >
                <span>{{ $t('test_track.report.testing_start_time') }}</span>
              </el-col>
              <el-col :span="2" class="center">

              </el-col>
              <el-col :span="11" v-if="endTime" class="center font-char" >
                <span>{{ $t('test_track.report.testing_end_time') }}</span>
              </el-col>

            </el-row>
          </el-col>
        </el-col>
        <el-col :span="4" style="background-color: ghostwhite ; margin-right: 10px; height:100px" class="center">

            <el-col :span="8" class="center">
              <img src="../../../../../../../../assets/total.png" style="margin-left: 50%;" class="icon" alt="">
            </el-col>
            <el-col :span="8" >
              <span class="font-num">{{ report.caseCount }}</span><br/>
              <span
                  style="margin-left: 30%" class="font-char">{{ $t('test_track.report.total_number_tests') }}</span>
        </el-col>
            <el-col :span="8"></el-col>
        </el-col>
        <el-col :span="4" style="background-color: ghostwhite ; margin-right: 10px; height:100px" class="center">
          <el-col :span="8" class="center">
            <img src="../../../../../../../../assets/implemetion.png" style="margin-left: 30%;" class="icon" alt="">
          </el-col>
          <el-col :span="8" >
          <span class="font-num" style="margin-left: 15%">{{ (report.executeRate ? (report.executeRate * 100).toFixed(0) : 0)}}</span>
          <span style="font-size: 12px;color:#0099ff">%</span><br/>
          <span
              style="margin-left: 15%" class="font-char">{{ $t('test_track.report.exacutive_rate') }}
            <ms-instructions-icon v-if="!isPdf" :content="$t('test_track.report.passing_rate_tip')"/>
            </span>
        </el-col>
          <el-col :span="8"></el-col>
        </el-col>


        <el-col :span="4" style="background-color: ghostwhite ; margin-right: 10px; height:100px" class="center">
          <el-col :span="8" class="center">
            <img src="../../../../../../../../assets/tongguo.png" style="margin-left: 30%;" class="icon" alt="">
          </el-col>
          <el-col :span="8" >
            <span class="font-num" style="margin-left: 15%">{{ (report.passRate ? (report.passRate  * 100 ).toFixed(0) : 0)}}</span>
            <span style="font-size: 12px;color:#0099ff">%</span><br/>
            <span
                style="margin-left: 15%" class="font-char">{{ $t('test_track.report.passing_rate') }}
            <ms-instructions-icon v-if="!isPdf"  :content="$t('test_track.report.passing_rate_tip')"/>
            </span>
          </el-col>
          <el-col :span="8"></el-col>
        </el-col>
      </el-row>
    </el-form>
  </test-plan-report-container>
</template>

<script>
import MsFormDivider from "@/business/components/common/components/MsFormDivider";
import TestPlanReportContainer
  from "@/business/components/track/plan/view/comonents/report/detail/TestPlanReportContainer";
import {timestampFormatDate} from "@/common/js/filter";
import MsInstructionsIcon from "@/business/components/common/components/MsInstructionsIcon";

export default {
  name: "TestPlanOverviewReport",
  components: {MsInstructionsIcon, TestPlanReportContainer, MsFormDivider},
  props: {
    report: Object,
    isPdf: Boolean,
  },
  data() {
    return {
      result: {},
      isEdit: false
    }
  },
  computed: {
    startTime() {
      let startTime = '';
      if (this.report.startTime) {
        startTime = timestampFormatDate(this.report.startTime, false);
      }
      return startTime
    },
    endTime() {
      let endTime = '';
      if (this.report.endTime) {
        endTime = timestampFormatDate(this.report.endTime, false);
      }
      return endTime;
    }
  },
}
</script>

<style scoped>
.form-info {
  padding: 20px;
}

.el-form-item:first-child {
  margin-bottom: 0;
  height: 60px;
}

.center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.font-num{
  margin-left: 50%;
  font-size: 20px;
  color:#0099ff;
}

.font-char{
  font-size: 8px;
  color: darkgray;
}
</style>
