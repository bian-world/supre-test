<template>
  <el-col>
    <div id="menu-bar">
      <project-change :project-name="currentProject"/>
    </div>
    <div>
      <transition>
        <keep-alive>
          <router-view :baseUrl="baseUrl"/>
        </keep-alive>
      </transition>
    </div>
  </el-col>
</template>
<script>

import ProjectChange from "@/business/components/common/head/ProjectSwitch";

export default {
  name: "TestPlanHome",
  components: {ProjectChange},
  data() {
    return {
      currentProject: "",
      baseUrl: "/plan"
    }
  }
}

</script>

<style scoped>

</style>
