<template>
  <functional-relevance
    :page="page"
    :get-table-data="getTestCases"
    :get-node-tree="getTreeNodes"
    :is-test-plan="true"
    :save="saveCaseRelevance"
    :version-enable="versionEnable"
    ref="functionalRelevance">
  </functional-relevance>
</template>

<script>

import {buildPagePath, getPageDate, getPageInfo} from "@/common/js/tableUtils";
import {TEST_PLAN_RELEVANCE_FUNC_CONFIGS} from "@/business/components/common/components/search/search-components";
import FunctionalRelevance from "@/business/components/track/plan/view/comonents/functional/FunctionalRelevance";
import {getCurrentSubProjectID} from "@/common/js/utils";

export default {
  name: "TestPlanFunctionalRelevance",
  components: {
    FunctionalRelevance,
  },
  data() {
    return {
      openType: 'relevance',
      result: {},
      page: getPageInfo({
        components: TEST_PLAN_RELEVANCE_FUNC_CONFIGS
      }),
    };
  },
  props: {
    planId: {
      type: String
    },
    versionEnable: {
      type: Boolean,
      default: false
    },
  },
  watch: {
    planId() {
      this.page.condition.planId = this.planId;
    },
  },
  methods: {
    open() {
      if (this.$refs.functionalRelevance) {
        this.$refs.functionalRelevance.open();
      }
    },
    saveCaseRelevance(param, vueObj) {
      if (param.ids.length > 0) {
        param.planId = this.planId;
        vueObj.result = this.$post('/test/plan/relevance', param, () => {
          vueObj.isSaving = false;
          this.$success(this.$t('commons.save_success'));
          vueObj.$refs.baseRelevance.close();
          vueObj.setSelectCounts(0);
          this.$emit('refresh');
        }, (error) => {
          vueObj.isSaving = false;
        });
      } else {
        vueObj.isSaving = false;
        this.$warning(this.$t('test_track.plan_view.please_choose_test_case'));
      }
    },
    search() {
      this.getTestCases();
    },
    getTestCases() {
      let condition = this.page.condition;
      let subProjectId = getCurrentSubProjectID();
      if(subProjectId){
        condition.subProjectId = subProjectId;
      }
      if (this.planId) {
        condition.planId = this.planId;
      }
      this.page.result = this.$post(buildPagePath('/test/case/relate', this.page), condition, response => {
        getPageDate(response, this.page);
        let data = this.page.data;
        data.forEach(item => {
          item.checked = false;
          item.tags = JSON.parse(item.tags);
        });
      });
    },
    getTreeNodes(vueObj) {
      vueObj.$refs.nodeTree.result = this.$post("/case/node/list/all/plan",
        {testPlanId: this.planId, projectId: vueObj.projectId}, response => {
          vueObj.treeNodes = response.data;
        });
      vueObj.selectNodeIds = [];
    }
  }
}
</script>

<style scoped>
</style>
