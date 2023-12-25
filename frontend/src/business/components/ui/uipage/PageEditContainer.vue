<template>

  <div class="card-container">
    <el-card class="card-content" >
      <el-form :model="pageForm" :rules="rule" ref="pageForm" label-width="80px" label-position="right">
        <!-- 操作按钮 -->
        <div style="float: right;margin-right: 20px" class="ms-opt-btn">

          <el-button type="primary" size="small" @click="savePage" title="ctrl + s"
                     v-permission="['PROJECT_API_DEFINITION:READ+EDIT_API']">{{ $t('commons.save') }}
          </el-button>
        </div>
        <br/>

        <ms-form-divider :title="$t('test_track.plan_view.base_info')"/>

        <!-- 基础信息 -->
        <div class="base-info">
          <el-row>
            <el-col :span="8">
              <el-form-item :label="$t('ui.page_name')" prop="name">
                <el-input class="ms-http-input" size="small" v-model="pageForm.name" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :label="$t('test_track.module.module')" prop="moduleId">
                <ms-select-tree size="small" :data="moduleOptions" :defaultKey="pageForm.moduleId" @getValue="setModule"
                                :obj="moduleObj" clearable checkStrictly/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item :label="$t('ui.father_page')" prop="parentId">
                <el-select filterable ref="select"
                           v-model="pageForm.parentId"
                           :clearable="true"
                           placeholder="选择父页面"
                           size="small"
                           class="ms-http-select"
                >
                  <el-option v-for="item in parentPageOptions" :key="item.id" :label="item.name" :value="item.id"></el-option>
                </el-select>

              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
<!--            <el-col :span="8">-->
<!--              <el-form-item :label="$t('api_test.definition.request.responsible')" prop="userId">-->
<!--                <el-select v-model="pageForm.userId"-->
<!--                           :placeholder="$t('api_test.definition.request.responsible')" filterable size="small"-->
<!--                           class="ms-http-select">-->
<!--                  <el-option-->
<!--                    v-for="item in maintainerOptions"-->
<!--                    :key="item.id"-->
<!--                    :label="item.name + ' (' + item.id + ')'"-->
<!--                    :value="item.id">-->
<!--                  </el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->

            <el-col :span="8">
              <el-form-item label="所属项目"  prop="subProject" style="width: 100%">
                <el-select filterable ref="select"
                           v-model="pageForm.subProjectId"
                           :clearable="true"
                           placeholder="选择项目"
                           size="small"
                           class="ms-http-select"
                >
                  <el-option v-for="item in subProjects" :key="item.id" :label="item.zendaoProjectName" :value="item.id"></el-option>
                </el-select>

              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item :label="$t('commons.description')" prop="description">
                <el-input class="ms-http-textarea"
                          v-model="pageForm.description"
                          type="textarea"
                          :autosize="{ minRows: 1, maxRows: 10}"
                          :rows="1" size="small"/>
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <ms-form-divider :title="$t('ui.element_list')"/>
        <div class="base-info mock-info scroll" >
          <page-element-item label-width="1"   :form="pageForm" :read-only="false" ref="elements"/>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>



import MsInputTag from "@/business/components/api/automation/scenario/MsInputTag";
import MsSelectTree from "@/business/components/common/select-tree/SelectTree";
import {getCurrentProjectID, getCurrentSubProjectID, getCurrentUser, getUUID, hasLicense} from "@/common/js/utils";
import MsFormDivider from "@/business/components/common/components/MsFormDivider";
import ApiOtherInfo from "@/business/components/api/definition/components/complete/ApiOtherInfo";
import MsDialogFooter from "@/business/components/common/components/MsDialogFooter";
import PageElementItem from "@/business/components/ui/uipage/PageElementItem";
const requireComponent = require.context('@/business/components/xpack/', true, /\.vue$/);
const versionHistory = requireComponent.keys().length > 0 ? requireComponent("./version/VersionHistory.vue") : {};

export default {
  name: "PageEditContainer",
  components: {
    PageElementItem,
    MsDialogFooter,
    'MsVersionHistory': versionHistory.default,
    ApiOtherInfo,
    MsFormDivider,
    MsInputTag, MsSelectTree
  },
  data() {
    // let validateURL = (rule, value, callback) => {
    //   if (!this.pageForm.path.startsWith("/") || this.httpForm.path.match(/\s/) != null) {
    //     callback(this.$t('api_test.definition.request.path_valid_info'));
    //   }
    //   callback();
    // };
    return {
      rule: {
        name: [
          {required: true, message: this.$t('test_track.case.input_name'), trigger: 'blur'},
          {max: 100, message: this.$t('test_track.length_less_than') + '100', trigger: 'blur'},
        ],
        userId: [{required: true, message: this.$t('test_track.case.input_maintainer'), trigger: 'change'}],
        moduleId: [{required: true, message: this.$t('test_track.case.input_module'), trigger: 'change'}],
      },
      pageForm: {
        userId: '',
        projectId: '',
        subProjectId: '',
        uiElementDTOS: [{
          num: 1,
          name: '',
          location: '',
          locationType: '',
          description: ''
        }],
      },
      moduleObj: {
            id: 'id',
            label: 'name'
      },
      subProjects: [],
      data: {},
      createNewVersionVisible: false,
      maintainerOptions:[],
      parentPageOptions: [],
    };
  },
  watch: {
    currentPage(){
      this.pageForm = JSON.parse(JSON.stringify(this.currentPage))
    }
    },
  props: {
    activeDom: String,
    isShowChangeButton: {
      type: Boolean,
      default: true
    },
    currentPage: {},
    moduleOptions: {},
    syncTabs: Array,
    projectId: String,
    selectNodeIds: Array,
    visible: {
      type: Boolean,
      default: false,
    },
  },
  syncTabs() {
      // if (this.basisData && this.syncTabs && this.syncTabs.includes(this.basisData.id)) {
      //   // 标示接口在其他地方更新过，当前页面需要同步
      //   let url = "/api/definition/get/";
      //   this.$get(url + this.basisData.id, response => {
      //     if (response.data) {
      //       let request = JSON.parse(response.data.request);
      //       let index = this.syncTabs.findIndex(item => {
      //         if (item === this.basisData.id) {
      //           return true;
      //         }
      //       });
      //       this.syncTabs.splice(index, 1);
      //       // this.httpForm.path = response.data.path;
      //       // this.httpForm.method = response.data.method;
      //       // Object.assign(this.request, request);
      //     }
      //   });
      // }
    },
  computed: {

  },
  methods: {
    getSubProjectsByProjectId(){
      let subProjectId = getCurrentSubProjectID();
      let projectId = getCurrentProjectID();
      if(projectId){

        this.$get('/project/getSubProjects/' + projectId, response => {
          this.subProjects = response.data;
          if(subProjectId && !this.pageForm.subProjectId){
            this.pageForm.subProjectId = subProjectId;
          }
        })}
      },
    // apiMapStatus() {
    //   this.$store.state.apiStatus.set("fromChange", true);
    //   if (this.httpForm.id) {
    //     this.$store.state.apiMap.set(this.httpForm.id, this.$store.state.apiStatus);
    //   }
    // },
    currentUser: () => {
      return getCurrentUser();
    },

    getMaintainerOptions() {
      this.$post('/user/project/member/tester/list', {projectId: getCurrentProjectID()}, response => {
        this.maintainerOptions = response.data;
      });
    },
    getParentPageOptions() {
      this.$post('/ui/page/listAll', {projectId: getCurrentProjectID()}, response => {
        this.parentPageOptions = response.data;
      });
    },
    // setParameter() {
    //   this.request.path = this.httpForm.path;
    //   this.request.method = this.httpForm.method;
    //   this.httpForm.request.useEnvironment = undefined;
    //   if (this.httpForm.tags instanceof Array) {
    //     this.httpForm.tags = JSON.stringify(this.httpForm.tags);
    //   }
    // },
    savePage() {
      this.$refs['pageForm'].validate((valid) => {
        if (valid) {
          // this.setParameter();

          if (!this.pageForm.versionId) {
            if (this.$refs.versionHistory && this.$refs.versionHistory.currentVersion) {
              this.pageForm.versionId = this.$refs.versionHistory.currentVersion.id;
            }
          }
          console.log("pageForm o%", this.pageForm)
          this.$emit('savePage', this.pageForm);
          // this.$store.state.apiStatus.set("fromChange", false);
          // this.$store.state.apiMap.set(this.pageForm.id, this.$store.state.apiStatus);
        } else {
          return false;
        }
      });
    },
    createModules() {
      this.$emit("createRootModelInTree");
    },

    setModule(id, data) {
      this.pageForm.moduleId = id;
      this.pageForm.modulePath = data.path;
    },

    dealWithTag(api) {
      if (api.tags) {
        if (Object.prototype.toString.call(api.tags) === "[object String]") {
          api.tags = JSON.parse(api.tags);
        }
      }
      // if (this.httpForm.tags) {
      //   if (Object.prototype.toString.call(this.httpForm.tags) === "[object String]") {
      //     this.httpForm.tags = JSON.parse(this.httpForm.tags);
      //   }
      // }
    },

    cancelCreateNewVersion() {
      this.createNewVersionVisible = false;
      this.getVersionHistory();
    },
    checkout(row) {
      let api = this.versionData.filter(v => v.versionId === row.id)[0];
      if (api.tags && api.tags.length > 0) {
        api.tags = JSON.parse(api.tags);
      }
      this.$emit("checkout", api);
    },
    // create(row) {
    //   // 创建新版本
    //   this.httpForm.versionId = row.id;
    //   this.httpForm.versionName = row.name;
    //
    //   this.$set(this.httpForm, 'newVersionRemark', !!this.httpForm.remark);
    //   this.$set(this.httpForm, 'newVersionDeps', this.$refs.apiOtherInfo.relationshipCount > 0);
    //   this.$set(this.httpForm, 'newVersionCase', this.httpForm.caseTotal > 0);
    //
    //   this.$post('/mockConfig/genMockConfig', {projectId: this.projectId, apiId: this.httpForm.id}, response => {
    //     this.$set(this.httpForm, 'newVersionMock', response.data.mockExpectConfigList.length > 0);
    //
    //     if (this.$refs.apiOtherInfo.relationshipCount > 0 || this.httpForm.remark ||
    //       this.httpForm.newVersionCase || this.httpForm.newVersionMock) {
    //       this.createNewVersionVisible = true;
    //     } else {
    //       this.saveApi();
    //     }
    //   });
    // },
    del(row) {
      this.$alert(this.$t('api_test.definition.request.delete_confirm') + ' ' + row.name + " ？", '', {
        confirmButtonText: this.$t('commons.confirm'),
        callback: (action) => {
          if (action === 'confirm') {
            this.$get('/api/definition/delete/' + row.id + '/' + this.httpForm.refId, () => {
              this.$success(this.$t('commons.delete_success'));
              this.getVersionHistory();
            });
          }
        }
      });
    }
  },

  created() {
    this.getSubProjectsByProjectId();
    this.getMaintainerOptions();
    this.getParentPageOptions();
    // if (!this.basisData.environmentId) {
    //   this.basisData.environmentId = "";
    // }

      //   if (!this.form.uiElementDTOS || this.form.uiElementDTOS.length < 1) {
      //   this.form.uiElementDTOS = [{
      //     num: 1,
      //     name: '',
      //     location: '',
      //     locationType: '',
      //     description: ''
      //   }];
      // }
    // })
    if (this.currentPage.moduleId && this.currentPage.moduleId === 'default-module') {
      this.currentPage.moduleId = this.moduleOptions[0].id;
    }
    console.log("this.currentPage o%", this.currentPage)
    this.pageForm = JSON.parse(JSON.stringify(this.currentPage));
    // this.initMockEnvironment();

    if (hasLicense()) {
      this.getVersionHistory();
    }
  }
};
</script>

<style scoped>

.base-info .el-form-item {
  width: 100%;
}

.mock-info {
  margin: 20px 45px;
}

.scroll{
  height: calc(100vh - 400px);
  overflow-y: auto;
}


.ms-opt-btn {
  position: fixed;
  right: 50px;
  z-index: 1;
  top: 128px;
}

/*.base-info .el-form-item >>> .el-form-item__content {*/
/*  width: 80%;*/
/*}*/

.base-info .ms-http-select {
  width: 100%;
}

.ms-http-textarea {
  width: 100%;
}

.ms-left-cell {
  margin-top: 100px;
}

.ms-left-buttion {
  margin: 6px 0px 8px 30px;
}

/deep/ .el-input__inner{
  height: 32px;
}
</style>
