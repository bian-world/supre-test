<template>
  <div id="menu-bar">
    <el-row type="flex">
      <project-change :project-name="currentProject"/>
      <el-col :span="12">
        <el-menu class="header-menu" :unique-opened="true" mode="horizontal" router :default-active="pathName">
<!--          <el-menu-item :index="'/performance/home'">-->
<!--            {{ $t("i18n.home") }}-->
<!--          </el-menu-item>-->
          <el-menu-item :index="'/performance/test/all'" v-permission="['PROJECT_PERFORMANCE_TEST:READ']">
            {{ $t('commons.test') }}
          </el-menu-item>
          <el-menu-item :index="'/performance/report/all'" v-permission="['PROJECT_PERFORMANCE_REPORT:READ']">
            {{ $t('commons.report') }}
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="12"/>
    </el-row>
  </div>
</template>

<script>

import MsRecentList from "../../common/head/RecentList";
import MsCreateButton from "../../common/head/CreateButton";
import MsShowAll from "../../common/head/ShowAll";
import ProjectChange from "@/business/components/common/head/ProjectSwitch";

export default {
  name: "PerformanceHeaderMenus",
  components: {
    ProjectChange,
    MsCreateButton,
    MsShowAll,
    MsRecentList,
  },
  data() {
    return {
      currentProject: '',
      pathName: '',
    };
  },
  methods: {},
  watch: {
    '$route': {
      immediate: true,
      handler(to, from) {
        if (to.params && to.params.testId) {
          this.pathName = '/performance/test/all';
        } else if (to.params && to.params.reportId) {
          this.pathName = '/performance/report/all';
        } else {
          this.pathName = to.path;
        }
      }
    }
  },
};

</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}

.el-menu.el-menu--horizontal {
  border-bottom: none;
}

#menu-bar {
  border-bottom: 1px solid #E6E6E6;
  background-color: #FFF;
}

.el-menu-item {
  padding: 0 10px;
}
</style>
