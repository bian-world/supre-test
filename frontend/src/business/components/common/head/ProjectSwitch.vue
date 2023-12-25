<template>

  <el-menu class="header-menu" :unique-opened="false" mode="horizontal" default-active="1" style="width:20%;margin-right: 20px" router>
    <!-- 不激活项目路由-->
    <el-menu-item index="1" v-show="false">Placeholder</el-menu-item>
    <el-submenu index="2" popper-class="submenu" style="width:50%" >
      <template v-slot:title>
        <el-tooltip effect="light" placement="right" :enterable="false" style="width:100%">
          <div slot="content" >{{ currentProject }}</div>
          <span class="project-name" >
           {{ currentProject }}
          </span>
        </el-tooltip>
      </template>
      <search-list :current-project.sync="currentProject" type="project"/>
      <div v-permission="['WORKSPACE_PROJECT_MANAGER:READ']">
        <el-divider/>
      </div>
      <el-menu-item :index="'/setting/project/create'" v-permission="['WORKSPACE_PROJECT_MANAGER:READ+CREATE']">
        <font-awesome-icon :icon="['fa', 'plus']"/>
        <span style="padding-left: 7px;">{{ $t("project.create") }}</span>
      </el-menu-item>
      <el-menu-item :index="'/setting/project/:type'" v-permission="['WORKSPACE_PROJECT_MANAGER:READ']">
        <font-awesome-icon :icon="['fa', 'list-ul']"/>
        <span style="padding-left: 7px;">{{ $t('commons.show_all') }}</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="3" popper-class="submenu" style="width:50%"  v-show="hasSubProjects" >
      <template v-slot:title>
        <el-tooltip effect="light" placement="right" :enterable="false" style="width:100%" >
          <div slot="content" >{{ currentSubProject }}</div>
          <el-input v-model = currentSubProject :clearable="true" @change="clearCurrentSubProject"  placeholder="选择项目"/>
        </el-tooltip>
      </template>
      <search-list :current-sub-project.sync="currentSubProject" type="subProject" :has-sub-projects.sync="hasSubProjects"/>
      <div v-permission="['WORKSPACE_PROJECT_MANAGER:READ']">
        <el-divider/>
      </div>

    </el-submenu>
  </el-menu>

</template>

<script>
import SearchList from "@/business/components/common/head/SearchList";
import {PROJECT_NAME, SUB_PROJECT_ID, SUB_PROJECT_NAME} from "@/common/js/constants";
import {getCurrentProjectID, getCurrentSubProjectID} from "@/common/js/utils";

export default {
  name: "ProjectSwitch",
  props: {
    projectName: String,
    subProjectName:{
      type: String,
      default(){
        return ''
      }
    }
  },
  inject: [
    'reload',
    'reloadTopMenus'
  ],
  components: {SearchList},
  watch: {
    currentProject() {
      sessionStorage.setItem(PROJECT_NAME, this.currentProject);
    },
    // currentSubProject() {
    //   sessionStorage.setItem(SUB_PROJECT_NAME, this.currentSubProject);
    // }
  },
  methods: {
    getCurrentProjectID,
    getCurrentSubProjectID,
    clearCurrentSubProject(text){
      if(!text) {
        console.log("clear subproject")
        this.reload();
        sessionStorage.removeItem(SUB_PROJECT_ID)
        // this.currentSubProject = this.$t('project.select_sub_project')
      }
    },
    subProjectClass(){
      return this.currentSubProject != this.$t('project.select_sub_project')?'project-name':'no-project-name';
   }
  },
  data() {
    return {
      currentProject: this.projectName,
      currentSubProject: this.subProjectName?this.subProjectName:'',
      hasSubProjects: false
    };
  }
};
</script>

<style scoped>
.project-name {
  display: inline-block;
  width: 160px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
}

.no-project-name {
  display: inline-block;
  width: 120px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: lightgray;
  text-align: left;
}

.el-divider--horizontal {
  margin: 0;
}

/deep/ .el-input__inner{
  border: 0px;

}

/deep/ .el-submenu__title{
  padding: 0 25px 0 10px
}

</style>
