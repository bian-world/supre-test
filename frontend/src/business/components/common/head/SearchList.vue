<template>
  <div v-loading="result.loading">
    <el-input :placeholder="$t('project.search_by_name')"
              prefix-icon="el-icon-search"
              v-model="searchString"
              clearable
              class="search-input"
              size="small"/>
    <div v-if="(type === 'project' && items.length === 0) || ((type === 'subProject' && subItems.length === 0))"
         style="text-align: center; margin: 15px 0">
        <span style="font-size: 15px; color: #8a8b8d;">
          {{ $t('project.no_data') }}
        </span>
    </div>
    <div v-else-if="type === 'project'" style="height: 150px;overflow: auto">
      <el-menu-item :key="i.id" v-for="i in items" @click="changeProject(i.id)">
        <template slot="title">
          <div class="title">
            {{ i.name }}
          </div>
          <i class="el-icon-check" v-if="i.id === getCurrentProjectID()"></i>
        </template>
      </el-menu-item>
    </div>
    <div v-else-if="type === 'subProject'" style="height: 150px;overflow: auto">
      <el-menu-item :key="i.id" v-for="i in subItems" @click="changeSubProject(i.id)">
        <template slot="title">
          <div class="title">
            {{ i.zendaoProjectName }}
          </div>
          <i class="el-icon-check" v-if="i.id === getCurrentSubProjectID()"></i>
        </template>
      </el-menu-item>
    </div>
  </div>
</template>

<script>
import {
  fullScreenLoading,
  getCurrentProjectID,
  getCurrentSubProjectID,
  getCurrentUser,
  getCurrentUserId,
  getCurrentWorkspaceId,
  hasPermissions,
  saveLocalStorage,
  stopFullScreenLoading
} from "@/common/js/utils";
import {PROJECT_ID, SUB_PROJECT_ID} from "@/common/js/constants";
import {getSubProjects} from "@/network/project";

export default {
  name: "SearchList",
  props: {
    type: String,
    options: Object,
    currentProject: String,
    currentSubProject: String,
    hasSubProjects: Boolean
  },
  created() {
    this.init();
  },
  inject: [
    'reload',
    'reloadTopMenus'
  ],
  data() {
    return {
      result: {},
      items: [],
      subItems: [],
      searchProjectArray: [],
      searchSubProjectArray: [],
      searchString: '',
      userId: getCurrentUser().id,
    };
  },
  watch: {
    searchString(val) {
      if (this.type === 'project') {
        this.queryProject(val);
      } else {
        this.querySubProject(val);
      }
    }
  },
  methods: {
    getCurrentProjectID,
    getCurrentSubProjectID,
    init: function () {
      let data = {
        userId: getCurrentUserId(),
        workspaceId: getCurrentWorkspaceId()
      };
      this.result = this.$post("/project/list/related", data, response => {
        this.items = response.data;
        this.searchProjectArray = response.data;
        let projectId = getCurrentProjectID();
        if (projectId) {
          console.log(projectId)
          // 保存的 projectId 在当前项目列表是否存在; 切换工作空间后
          if (this.searchProjectArray.length > 0 && this.searchProjectArray.map(p => p.id).indexOf(projectId) === -1) {
            this.change(this.items[0].id);
          }
        } else {
          if (this.items.length > 0) {
            this.change(this.items[0].id);
          }
        }
        this.changeProjectName(projectId);
        this.result = this.$get('/project/getSubProjects/' + projectId, response => {
          this.subItems = response.data;
          this.searchSubProjectArray = response.data;
          let subProjectId = getCurrentSubProjectID();
          this.changeSubProjectName(subProjectId);

        });
      })
    },
    queryProject(queryString) {
      this.items = queryString ? this.searchProjectArray.filter(this.createFilter(queryString)) : this.searchProjectArray;
    },
    querySubProject(queryString) {
      console.log(queryString)
      this.subItems = queryString ? this.searchSubProjectArray.filter(this.createFilter(queryString)) : this.searchSubProjectArray;
    },
    createFilter(queryString) {
      return item => {
        console.log(item)
        if (this.type === 'project') {
          return (item.name.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
        } else {
          return (item.zendaoProjectName.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
        }
      };
    },
    reloadPage: function () {
      let redirectUrl = sessionStorage.getItem('redirectUrl');
      console.log('redirectUrl %s', redirectUrl);
      let trackPermission = hasPermissions('PROJECT_TRACK_CASE:READ', 'PROJECT_TRACK_PLAN:READ', 'PROJECT_TRACK_REVIEW:READ', 'PROJECT_TRACK_ISSUE:READ', 'PROJECT_TRACK_REPORT:READ');
      let apiPermission = hasPermissions('PROJECT_API_DEFINITION:READ', 'PROJECT_API_SCENARIO:READ', 'PROJECT_API_REPORT:READ');
      let performancePermission = hasPermissions('PROJECT_PERFORMANCE_TEST:READ', 'PROJECT_PERFORMANCE_REPORT:READ');
      let projectPermission = hasPermissions('PROJECT_USER:READ', 'PROJECT_ENVIRONMENT:READ', 'PROJECT_OPERATING_LOG:READ', 'PROJECT_FILE:READ+JAR', 'PROJECT_FILE:READ+FILE', 'PROJECT_CUSTOM_CODE:READ');
      let uiPermission = hasPermissions('PROJECT_USER:READ', 'PROJECT_ENVIRONMENT:READ', 'PROJECT_OPERATING_LOG:READ', 'PROJECT_FILE:READ+JAR', 'PROJECT_FILE:READ+FILE', 'PROJECT_CUSTOM_CODE:READ');


      let redirectMap = {
        plan: trackPermission,
        dashboard: trackPermission,
        track: trackPermission,
        api: apiPermission,
        performance: performancePermission,
        project: projectPermission,
        ui: uiPermission,
      };
      let locations = redirectUrl.split('/');
      if (locations.length > 2) {
        if (!redirectMap[locations[1]]) {
          let v = true;
          for (const k in redirectMap) {
            if (redirectMap[k]) {
              this.$router.push("/" + k);
              v = false;
              break;
            }
          }
          if (v) {
            this.$router.push("/");
          }
        }
      }
      this.reloadTopMenus();
      this.reload();
    },
    changeSubProject(subProjectId) {
      let currentSubProjectId = getCurrentSubProjectID();
      if (subProjectId === currentSubProjectId) {
        return;
      }
      const loading = fullScreenLoading(this);
      this.currentSubProjectId = subProjectId;

      this.$EventBus.$emit('projectChange');
      // 保存session里的projectId
      sessionStorage.setItem(SUB_PROJECT_ID, subProjectId);
      // 刷新路由
      this.reloadPage();
      stopFullScreenLoading(loading, 1500);
      this.changeSubProjectName(subProjectId);
    },
    changeProject(projectId) {
      let currentProjectId = getCurrentProjectID();
      if (projectId === currentProjectId) {
        return;
      }
      const loading = fullScreenLoading(this);
      this.$post("/user/update/current", {id: this.userId, lastProjectId: projectId}, (response) => {
        saveLocalStorage(response);
        this.currentProjectId = projectId;

        this.$EventBus.$emit('projectChange');
        // 保存session里的projectId
        sessionStorage.setItem(PROJECT_ID, projectId);
        // 刷新路由
        this.reloadPage();
        stopFullScreenLoading(loading, 1500);
        sessionStorage.setItem(SUB_PROJECT_ID, '');
        this.changeProjectName(projectId);

      }, () => {
        stopFullScreenLoading(loading, 1500);
      });
    },
    changeProjectName(projectId) {
      if (projectId) {
        let project = this.searchProjectArray.filter(p => p.id === projectId);
        if (project.length > 0) {
          this.$emit("update:currentProject", project[0].name);
          this.result = this.$get('/project/getSubProjects/' + projectId, response => {
            if (response.data.length > 0) {
              this.$emit("update:hasSubProjects", true)
              this.subItems = response.data;
              this.searchSubProjectArray = response.data;
            }
          })
        } else {
          this.$emit("update:currentProject", this.$t('project.select'));
        }
      }
    },
    changeSubProjectName(subProjectId) {
      if (subProjectId) {
        let subProject = this.searchSubProjectArray.filter(p => p.id === subProjectId);
        if (subProject.length > 0) {
          this.$emit("update:currentSubProject", subProject[0].zendaoProjectName);
        }}
      // } else {
      //   this.$emit("update:currentSubProject", this.$t('project.select_sub_project'));
      // }
    },
  }
};
</script>

<style scoped>

.search-input {
  padding: 0;
  margin-top: -5px;
}

.search-input >>> .el-input__inner {
  border-radius: 0;
}

.title {
  display: inline-block;
  padding-left: 10px;
  max-width: 140px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.el-icon-check {
  color: #773888;
  margin-left: 2px;
  font-weight: bold;
}
</style>
