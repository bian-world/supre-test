<template>
  <ms-container>
    <ms-main-container>
      <el-row :gutter="10">
        <el-col :span="10">
          <div class="square">
            <test-case-count-pie-chart :trackCountData=trackCountData class="track-card"/>
          </div>
        </el-col>

        <el-col :span="14">
          <div class="square">
            <test-plan-progress-line-chart :case-option="planProgressOption" class="track-card"/>
          </div>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="10">
          <div class="square">
            <api-case-count-pie-chart :apiCaseCountData="apiCaseCountData" class="track-card"/>
          </div>
        </el-col>

        <el-col :span="14">
          <div class="square">
            <case-increased-line-chart :case-option="caseIncreasedOption" class="track-card"/>
          </div>
        </el-col>
      </el-row>

      <el-row :gutter="10">

        <el-col :span="10">
          <div class="square">
            <bug-duration-bar-chart :case-option="bugDurationList" class="track-card"/>
          </div>
        </el-col>
        <el-col :span="14">
          <div class="square">
            <bug-count-bar-chart :case-option="bugList" class="track-card"/>
          </div>
        </el-col>
      </el-row>


    </ms-main-container>
  </ms-container>
</template>

<script>

import MsMainContainer from "@/business/components/common/components/MsMainContainer";
import MsContainer from "@/business/components/common/components/MsContainer";
import CaseCountCard from "@/business/components/track/home/components/CaseCountCard";
import RelevanceCaseCard from "@/business/components/track/home/components/RelevanceCaseCard";
import CaseMaintenance from "@/business/components/track/home/components/CaseMaintenance";
import {COUNT_NUMBER, COUNT_NUMBER_SHALLOW} from "@/common/js/constants";
import BugCountCard from "@/business/components/track/home/components/BugCountCard";
import ReviewList from "@/business/components/track/home/components/ReviewList";
import MsRunningTaskList from "@/business/components/track/home/components/RunningTaskList";
import MsFailureTestCaseList from "@/business/components/api/homepage/components/FailureTestCaseList";
import {getCurrentProjectID, getCurrentSubProjectID, getUUID} from "@/common/js/utils";
import TestCaseCountPieChart from "@/business/components/dashboard/home/components/TestCaseCountPieChart";
import MsDoughnutPieChart from "@/business/components/common/components/MsDoughnutPieChart";
import MsPieChart from "@/business/components/common/components/MsPieChart";
import StRosePieChart from "@/business/components/common/components/StRosePieChart";
import TestPlanProgressLineChart from "@/business/components/dashboard/home/components/TestPlanProgressLineChart";
import ApiCaseCountPieChart from "@/business/components/dashboard/home/components/ApiCaseCountPieChart";
import CaseIncreasedLineChart from "@/business/components/dashboard/home/components/CaseIncreasedLineChart";
import ProjectChange from "@/business/components/common/head/ProjectSwitch";
import BugCountBarChart from "@/business/components/dashboard/home/components/BugCountBarChart";
import BugDurationBarChart from "@/business/components/dashboard/home/components/BugDurationBarChart";

require('echarts/lib/component/legend');
export default {
  name: "DashBoardHome",
  components: {
    BugDurationBarChart,
    BugCountBarChart,
    CaseIncreasedLineChart,
    ApiCaseCountPieChart,
    TestPlanProgressLineChart,
    StRosePieChart,
    MsPieChart,
    MsDoughnutPieChart,
    TestCaseCountPieChart,
    ReviewList,
    BugCountCard,
    RelevanceCaseCard,
    CaseCountCard,
    MsMainContainer,
    MsContainer,
    CaseMaintenance,
    MsRunningTaskList,
    MsFailureTestCaseList,
    ProjectChange
  },
  data() {
    return {
      result: {},
      trackCountData: {},
      weekPlanProgress: {},
      caseIncreasedOption: {},
      planProgressOption: {},
      currentProject: '',
      apiCaseCountData: '',
      bugList:'',
      bugDurationList:'',
      caseOption: {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value',
          splitLine: {
            show: true,
            lineStyle: {
              type: 'dashed'
            }
          }

        },
        legend: {
          data: ["测试计划一", "测试计划二"],
          orient: 'horizontal',
          right: '80',
          icon: 'pin'
        },

        series: [
          {
            name: "测试计划一",
            data: [820, 932, 901, 934, 1290, 1330, 1320],
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

          },
          {
            name: "测试计划二",
            data: [520, 932, 801, 834, 1090, 1230, 1020],
            type: 'line',
            smooth: true,
            showSymbol: false,
            lineStyle: {      // 阴影部分
              shadowOffsetX: 0, // 折线的X偏移
              shadowOffsetY: 9,// 折线的Y偏移
              shadowBlur: 8,  // 折线模糊
              shadowColor: "#63B8FF", //折线颜色

            },
            itemStyle: {
              color: "#63B8FF"
            }

          }
        ]
      }

    }
  },
  activated() {
    this.init();
  },
  computed: {
    projectId() {
      return getCurrentProjectID();
    },
    subProjectId(){
      return getCurrentSubProjectID();
    }
  },
  methods: {
    init() {
      let selectProjectId = this.projectId;
      let selectSubProjectId = this.subProjectId?this.subProjectId:'';
      if (!selectProjectId) {
        return;
      }
      console.log(selectProjectId)
      this.$get("/track/count/" + selectProjectId + "/" + selectSubProjectId, response => {
        this.trackCountData = this.formatTrackData(response.data);
      });

      this.$get("/project/weekPlanProgress/related/" + selectProjectId + "/" + selectSubProjectId, response => {
        this.setPlanProgressOption(response.data)
      });

      this.$get("/project/weekCaseList/related/" + selectProjectId + "/" + selectSubProjectId, response => {
        let data = response.data;
        this.setBarOption(data);
      });
      this.$get("/api/testCaseInfoCount/" + selectProjectId + "/" + selectSubProjectId, response => {
        this.apiCaseCountData = this.formatApiCaseData(response.data);
      });
      this.$get("/project/getBugList/" + selectProjectId + "/" + selectSubProjectId, response => {
        this.bugList = this.formatBugList(response.data);
      });
      this.$get("/project/getBugDuration/" + selectProjectId + "/" + selectSubProjectId, response => {
        this.bugDurationList = this.formatBugList(response.data);
      });
    },
    formatBugList(data){
     //按bug数从高到底排序
      var map = new Map(Object.entries(data).sort((a,b) => {
        return a[1] < b[1] ? 1 : (a[1] > b[1] ? -1 : 0)
      }))
      let xAxis =  Array.from(map.keys());
      let yAxis = Array.from(map.values());
      let option = {'xAxis': xAxis, 'yAxis': yAxis}
      return option;
    },
    //
    // formatBugDuration(data){
    //   let xAxis = Object.keys(data)
    //   let yAxis = Object.values(data);
    //   let option = {'xAxis': xAxis, 'yAxis': yAxis}
    //   return option;
    // },

    formatApiCaseData(data) {
      if (!data) {
        return;
      }
      let result = {}
      let arr = []
      result.coverageRage = data.coverageRage.split(/%|[.]/)[0]
      arr.push({name: 'HTTP', value: data.httpApiDataCountNumber, itemStyle: {color: '#0099ff'}})
      arr.push({name: 'RPC', value: data.rpcApiDataCountNumber, itemStyle: {color: '#6666ff'}})
      arr.push({name: 'TCP', value: data.tcpApiDataCountNumber, itemStyle: {color: '#00ccff'}})
      arr.push({name: 'SQL', value: data.sqlApiDataCountNumber, itemStyle: {color: '#33cccc'}})
      result.data = arr
      result.thisWeekAddedCount = data.thisWeekAddedCount
      result.thisWeekExecutedCount = data.thisWeekExecutedCount
      result.executedCount = data.executedCount
      result.coverageCount = data.coverageCount
      result.uncoverageCount = data.uncoverageCount
      return result
    },
    formatTrackData(data) {
      if (!data) {
        return;
      }
      let result = {}
      result.reviewRage = data.reviewRage.split(/%|[.]/)[0]
      let arr = []
      arr.push({name: 'P0', value: data.p0CaseCountNumber, itemStyle: {color: '#0099ff'}})
      arr.push({name: 'P1', value: data.p1CaseCountNumber, itemStyle: {color: '#6666ff'}})
      arr.push({name: 'P2', value: data.p2CaseCountNumber, itemStyle: {color: '#00ccff'}})
      arr.push({name: 'P3', value: data.p3CaseCountNumber, itemStyle: {color: '#33cccc'}})
      result.data = arr
      result.prepareCount = data.prepareCount
      result.unPassCount = data.unPassCount
      result.passCount = data.passCount
      result.thisWeekAddedCount = data.thisWeekAddedCount
      return result
    },
    getXAxis() {
      let xAxis = Array(7).fill(1000 * 60 * 60 * 24).map((v, k) => {
        let date = new Date();
        date.setTime(date.getTime() - v * k);
        let month = date.getMonth() + 1;
        let day = date.getDate();
        return month + '月' + day + '日';
      }).reverse();
      return xAxis;
    },

    setPlanProgressOption(data) {
      let xAxis = this.getXAxis();
      let legend = Object.keys(data);
      let series = []
      let serie = {
        name: '',
        data: '',
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
      let colorList =[
        "#5470c6",
        "#91cc75",
        "#fac858",
        "#73c0de",
        "#3ba272",
        "#3fb1e3",
        "#6be6c1",
        "#626c91",
        "#a0a7e6",
        "#c4ebad",
        "#96dee8"
        ]
      //遍历测试计划
      Object.values(data).forEach((plan,index) => {
        let yAxis = Array(7).fill(0);
        // let tempSerie = serie;
        //遍历测试计划下的七天数据
        plan.forEach(dateData => {
          let index = xAxis.indexOf(dateData.createDay)
          if (index !== -1) {
            yAxis[index] = dateData.passRate;
            // console.log(plan.passRate);
          }
        })
        serie.name = plan[plan.length - 1].planName;
        serie.data = yAxis;
        serie.lineStyle.shadowColor = colorList[index % 11]
        serie.itemStyle.color = colorList[index % 11]
        series.push({...serie,lineStyle:{...serie.lineStyle},itemStyle:{...serie.itemStyle}});
      });
      let option = {
        xAxis: xAxis,
        legend: legend,
        series: series
      }
      console.log(option);
      this.planProgressOption = option;
    },
    setBarOption(data) {
      let xAxis = this.getXAxis();
      let yAxis = Array(7).fill(0)
      data.forEach(d => {
        let index = xAxis.indexOf(d.caseCreateDay)
        if (index !== -1) {
          yAxis[index] = d.caseNum;
        }
      });
      let option = {
        yAxisData: yAxis,
        xAxisData: xAxis
      }
      this.caseIncreasedOption = option;
    },
    redirectPage(page, dataType, selectType, title) {
      //api页面跳转
      //传入UUID是为了进行页面重新加载判断
      let uuid = getUUID();
      switch (page) {
        case "api":
          this.$router.push({
            name: 'ApiDefinition',
            params: {redirectID: uuid, dataType: dataType, dataSelectRange: selectType}
          });
          break;
        case "scenario":
          this.$router.push({
            name: 'ApiAutomation',
            params: {redirectID: uuid, dataType: dataType, dataSelectRange: selectType}
          });
          break;
        case "testPlanEdit":
          this.$router.push('/plan/view/' + selectType)
          break;
      }
    }
  }
}
</script>

<style scoped>
.square {
  width: 100%;
  height: 400px;
}

.rectangle {
  width: 100%;
  height: 400px;
}

.el-row {
  margin-bottom: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

.track-card {
  height: 100%;
}

#menu-bar {
  border-bottom: 1px solid #E6E6E6;
  background-color: #FFF;
}
</style>
