<template>
  <div>
    <el-row>
      <el-col :span="6">
        <st-rose-pie-chart id="func" style="width:100%; height:150px" :data="caseCharData" :supreOptions="options"
                           :pieSize="size" :canvasPosition="position" name="" :autoresize="!isPdf"/>
      </el-col>
      <el-col :span="18">
        <el-row align="middle" type="flex" justify="space-around"
                style="background-color: #FAFAFA;height: 70px;width: 90%;margin-top:4%;margin-left: 0px">
          <el-col :span="4"
                  style="border-right-style: solid;border-right-width: 1px;border-right-color: #CFCFCF;">
            <span style="font-size: 12px;margin-left: 15%">{{ $t('test_track.plan.test_plan_test_case_count') }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:#0099ff">{{ totalData.caseNum }}</span>
          </el-col>
          <el-col :span="5"
                  style="border-right-style: solid;border-right-width: 0px;border-right-color: #ECEEF4;">

            <span style="font-size: 12px;margin-left: 15%">{{ $t('test_track.report.pass_num') }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:#29e342">{{ totalData.passNum }}</span><br/>
          </el-col>
          <el-col :span="5"
                  style="border-right-style: solid;border-right-width: 1px;border-right-color: #CFCFCF;">

            <span style="font-size: 12px;margin-left: 10%">{{ $t('test_track.report.pass_rate') }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:#29e342">{{ totalData.passRate }}</span><br/>
          </el-col>
          <el-col :span="4"
                  style="border-right-style: solid;border-right-width: 0px;border-right-color: #ECEEF4;">

            <span style="font-size: 12px;margin-left: 15%">{{ $t('test_track.report.unpass_num') }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:red">{{ totalData.unPassNum }}</span><br/>
          </el-col>
          <el-col :span="6"
                  style="border-right-style: solid;border-right-width: 0px;border-right-color: #ECEEF4;">

            <span style="font-size: 12px;margin-left: 10%">{{ $t('test_track.report.unpass_rate') }}</span>
            <span style="margin-left: 5%;font-size: 18px;color:red">{{ totalData.unPassRate }}</span><br/>
          </el-col>

        </el-row>

      </el-col>
      <!--     <el-col :span="12">-->
      <!--       <ms-doughnut-pie-chart :name="$t('test_track.plan.test_plan_test_case_count')" :data="caseCharData" ref="functionChar"/>-->
      <!--     </el-col>-->
      <!--     <el-col :span="12">-->
      <!--       <ms-doughnut-pie-chart :name="$t('test_track.plan_view.issues_count')" :data="issueCharData"/>-->
      <!--     </el-col>-->
    </el-row>
    <el-row style="margin-bottom: 50px">
      <el-table
          :header-cell-style="{'text-align':'center','font-size':'10px','font-weight':3,'background-color':'#FAFAFA'}"
          :cell-style="{'text-align':'center','font-size':'10px'}"
          :data="tableData"
          border
          style="width: 100%;margin-top: 20px; ">
        <el-table-column
            prop="moduleName"
            label="所属模块"
            width="300px">
        </el-table-column>
        <el-table-column
            prop="caseNum"
            label="用例总数"
            width="300px">
        </el-table-column>
        <el-table-column
            prop="passNum"
            label="已执行通过"
            width="300px">
        </el-table-column>
        <el-table-column
            prop="unPassNum"
            label="未执行通过"
            width="300px">
        </el-table-column>
        <el-table-column
            prop="passRate"
            label="通过率">
        </el-table-column>
        <el-table-column
            prop="isPublish"
            label="是否上线">
          <template slot-scope="scope">
            <el-checkbox :disabled="isShare || isTemplate || isDb" v-model="scope.row.isPublish"
                         @change="handleModulePushlish(scope.row)"></el-checkbox>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>

import MsDoughnutPieChart from "@/business/components/common/components/MsDoughnutPieChart";
import StRosePieChart from "@/business/components/common/components/StRosePieChart";
import {
  getPlanFunctionAllCase,
  getSharePlanFunctionAllCase, getSharePlanFunctionFailureCase,
  publishPlanModule,
  unPublishPlanModule
} from "@/network/test-plan";

export default {
  name: "FunctionalResult",
  components: {StRosePieChart, MsDoughnutPieChart},
  data() {
    return {
      caseDataMap: new Map([
        ["Pass", {name: this.$t('test_track.plan_view.pass'), itemStyle: {color: '#0099ff'}}],
        ["Failure", {name: this.$t('test_track.plan_view.failure'), itemStyle: {color: '#6666ff'}}],
        ["Blocking", {name: this.$t('test_track.plan_view.blocking'), itemStyle: {color: '#00ccff'}}],
        ["Skip", {name: this.$t('test_track.plan_view.skip'), itemStyle: {color: '#33cccc'}}],
        ["Underway", {name: this.$t('test_track.plan.plan_status_running'), itemStyle: {color: 'lightskyblue'}}],
        ["Prepare", {name: this.$t('test_track.plan.plan_status_prepare'), itemStyle: {color: '#a0a7e6'}}]
      ]),
      issueDataMap: new Map([
        ["resolved", {name: this.$t('test_track.issue.status_resolved'), itemStyle: {color: '#67C23A'}}],
        ["new", {name: this.$t('test_track.issue.status_new'), itemStyle: {color: '#F56C6C'}}],
        ["closed", {name: this.$t('test_track.issue.status_closed'), itemStyle: {color: '#909399'}}],
      ]),
      caseCharData: [],
      issueCharData: [],
      isShow: true,
      size: ['40%', '70%'],
      position: ['0%', '50%'],
      options: {
        legend: {
          left: '50%',
          bottom: '30%',
          icon: 'pin',
          itemHeight: 1,  //调整icon大小
          itemWidth: 15,
          textStyle: {
            lineHeight: 10,
            rich: {
              a: {
                width: 50,
                fontSize: 11,
                color: '#303133'
              }
            },
          },
          formatter: (name) => {
            return `{a|${name}}`;
          },
        },
      },
      tableData: [],
      totalData: {
        caseNum: 0,
        passNum: 0,
        passRate: '',
        unPassNum: 0,
        unPassRate: ''
      },
      checked: false,
      testCases: []
    }
  },
  props: {
    shareId: {
  type: String,
},
    report: {},
    isDb: {
      type: Boolean,
      default() {
        return false
      }
    },
    isTemplate: {
      type: Boolean,
      default() {
        return false
      }
    },
    isShare: {
      type: Boolean,
      default() {
        return false
      }
    },
    isPdf: {
      type: Boolean,
      default() {
        return false
      }
    },
    planId: {
      type: String,
    }
  },
  watch: {
    report() {
      this.getCaseCharData();
    }
  },
  mounted() {
    this.getCaseCharData();
  },
  methods: {

    getCaseCharData() {

      let caseCharData = [];
      this.report.functionResult.caseData.forEach(item => {
        let data = this.caseDataMap.get(item.status);
        data.value = item.count;
        caseCharData.push(data);
      });
      this.caseCharData = caseCharData;

      // let issueCharData = [];
      // let colors = [
      //   '#1bb2d8', '#99d2dd', '#88b0bb', '#1c7099', '#038cc4', '#75abd0', '#afd6dd'];
      // let usedSet = new Set();
      //
      // this.report.functionResult.issueData.forEach(item => {
      //   let status = item.status;
      //   let data = this.issueDataMap.get(status);
      //   if (!data) {
      //     data = {name: status, itemStyle: {color: null}};
      //     if (status === 'new' || status === '新' || status === '待办' || status === 'active' || status === 'created') {
      //       data.itemStyle.color = '#1c7099';
      //       usedSet.add(data.itemStyle.color);
      //     }
      //     if (status === '已拒绝' || status === 'reject') {
      //       data.itemStyle.color = '#1bb2d8';
      //       usedSet.add(data.itemStyle.color);
      //     }
      //     if (status === '已关闭' || status === 'close') {
      //       data.itemStyle.color = '#75abd0';
      //       usedSet.add(data.itemStyle.color);
      //     }
      //     if (!data.itemStyle.color) {
      //       for (let i = 0; i < colors.length; i++) {
      //         let color = colors[i];
      //         if (!usedSet.has(color)) {
      //           data.itemStyle.color = color;
      //           usedSet.add(data.itemStyle.color);
      //           break;
      //         }
      //       }
      //     }
      //   }
      //   data.value = item.count;
      //   issueCharData.push(data);
      // });
      // this.issueCharData = issueCharData;
      // // getPlanFunctionAllCases(this.planId, (data) => {
      // //   console.log('data  %o',data)
      // //   this.testCases = data;
      // // });

      //后端渲染模版方式的，需要后端组装好report中的数据
      if (this.isTemplate || this.isDb) {
        this.testCases = this.report.functionAllCases
        this.formatTableData();
      } else if (this.isShare) {

        getSharePlanFunctionAllCase(this.shareId, this.planId, (data) => {
          this.testCases = data;
          this.formatTableData();
        });
      } else {
        getPlanFunctionAllCase(this.planId, (data) => {
          this.testCases = data;
          //异步调用，页面渲染时用到的数据须包裹在callback中
           this.formatTableData();
        });
      }

    },

    formatTableData() {

      if (this.testCases) {
        let caseMap = new Map();

        this.testCases.forEach(caseData => {
          if (caseMap.get(caseData.nodeId)) {
            caseMap.get(caseData.nodeId).push(caseData)
          } else {
            let caseList = [];
            caseList.push(caseData)
            caseMap.set(caseData.nodeId, caseList)
          }
        })
        caseMap.forEach((v, k) => {
          let moduleCase = {
            moduleName: '',
            moduleId: '',
            caseNum: 0,
            passNum: 0,
            unPassNum: 0,
            passRate: '',
            isPublish: false
          };
          moduleCase.moduleId = k
          v.forEach(caseData => {
            moduleCase.caseNum += 1;
            if (caseData.status === 'Pass') {
              moduleCase.passNum += 1;
            }
          })
          moduleCase.isPublish = this.report.publishedModule?.filter(module => module.nodeId === moduleCase.moduleId).length > 0
          moduleCase.moduleName = v[0].nodePath
          moduleCase.unPassNum = moduleCase.caseNum - moduleCase.passNum;
          moduleCase.passRate = ((moduleCase.passNum / moduleCase.caseNum) * 100).toFixed(0) + '%'
          console.log(moduleCase)
          this.tableData.push(moduleCase)
          this.totalData.caseNum += moduleCase.caseNum
          this.totalData.passNum += moduleCase.passNum
          this.totalData.unPassNum = this.totalData.caseNum - this.totalData.passNum
          this.totalData.passRate = ((this.totalData.passNum / this.totalData.caseNum) * 100).toFixed(0) + '%'
          this.totalData.unPassRate = ((this.totalData.unPassNum / this.totalData.caseNum) * 100).toFixed(0) + '%'
        });
      }
    },
    handleModulePushlish(row) {
      if (row.isPublish === true) {
        publishPlanModule(this.planId, row.moduleId, (data) => {
          console.log(data)
        })
      } else if (row.isPublish === false) {
        unPublishPlanModule(this.planId, row.moduleId, (data) => {
          console.log(data)
        })
      } else {
        console.log('checkbox的状态未知')
      }
    }
  }
}
</script>

<style scoped>
</style>
