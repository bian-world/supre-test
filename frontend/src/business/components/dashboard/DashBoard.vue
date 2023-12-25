<template>
  <el-col>
    <div id="menu-bar">
      <project-change :project-name="currentProject" :sub-project-name="subCurrentProject"/>
    </div>
    <div>
      <transition>
        <keep-alive>
          <dash-board-home/>
<!--          <router-view :baseUrl="baseUrl"/>-->
        </keep-alive>
      </transition>
    </div>
  </el-col>
</template>
<script>

import ProjectChange from "@/business/components/common/head/ProjectSwitch";
import DashBoardHome from "@/business/components/dashboard/home/components/DashBoardHome";

export default {
  name: "DashBoard",
  components: {DashBoardHome, ProjectChange},
  data() {
    return {
      currentProject: "",
      subCurrentProject: "",
      baseUrl: "/dashboard"
    }
  }
}

</script>

<style scoped>

</style>
