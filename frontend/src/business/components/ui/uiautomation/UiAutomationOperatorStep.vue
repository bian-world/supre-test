<template>

    <div v-if="request.type === 'StWebDriverSampler'">

      <el-row class="row">
        <el-col :span="6">
          <span>{{$t('ui.select_page')}}</span>
              <el-select filterable ref="select"
                         v-model="request.step.pageId"
                         :clearable="true"
                         placeholder="请选择"
                         size="mini"
              >
              <el-option v-for="item in pages" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
<!--          <el-input-->
<!--                          class="table-edit-input"-->
<!--                          size="mini"-->
<!--                          :disabled="false"-->
<!--                          :placeholder="$t('commons.input_content')"-->
<!--                          clearable/>-->


        </el-col >
        <el-col :span="6">
          <span>{{$t('ui.select_element')}}</span>
          <el-select filterable ref="select"
                     v-model="request.step.elementName"
                     :clearable="true"
                     placeholder=""
                     size="mini"
                     @change="setElement"
                     @focus="getElements"
          >
            <el-option v-for="item in elements" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>

        </el-col>
        <el-col :span="5" >
          <span>{{$t('ui.operator')}}</span>
<!--          <el-select  filterable ref="select"-->
<!--                     v-model="request.step.operationId"-->
<!--                     :clearable="true"-->
<!--                     placeholder="选择操作类"-->
<!--                     size="mini"-->
<!--                     @change="setOperation"-->
<!--          >-->
<!--            <el-option v-for="item in operations" :key="item.id" :label="item.operation" :value="item.id"></el-option>-->

<!--          </el-select>-->
          <el-cascader
            placeholder="请选择操作项"
            v-model = "selectedCasValue"
            :options="operations"
            filterable
            size="mini"
            @change="setOperation"
          >

          </el-cascader>


          <el-input
            v-model="request.step.sendKeys"
            class="ms-input"
            size="mini"
            :disabled="false"
            :placeholder="$t('commons.input_content')"
            clearable/>
        </el-col>
      </el-row>
      <el-row class="row">
        <el-col :span="4">
          <span>{{$t('ui.wait_time')}}</span>
          <el-input
                                      v-model="request.step.waitTime"
                                      class="ms-input"
                                      size="mini"
                                      :disabled="false"
                                      :placeholder="$t('commons.input_content')"
                                      clearable/>
        </el-col>
      </el-row>
      </div>
</template>

<script>
import {REQ_METHOD} from "@/business/components/api/definition/model/JsonData";
import {KeyValue} from "@/business/components/api/definition/model/ApiTestModel";
import SelectTree from "@/business/components/common/select-tree/SelectTree";
import {getCurrentProjectID} from "@/common/js/utils";

export default {
  name: "UiAutomationOperatorStep",
  components: {SelectTree},
  props: ['request', 'isCustomizeReq'],
  data() {
    return {
      selectedCasValue: [],
      reqOptions: REQ_METHOD,
      isUrl: false,
      pages: [],
      elements: [],
      operations: []
    }
  },
  watch: {
    request() {
      this.selectedCasValue = [this.request.step.operationType, this.request.step.operationId]
    }
  },
  mounted(){
    this.getPages()
    this.getOperations()
    this.selectedCasValue = [this.request.step.operationType, this.request.step.operationId]
  },
  methods: {
    setElement(elementId){
      this.elements.forEach(element => {
        if(element.id === elementId){
          this.request.step.elementName = element.name;
          this.request.step.elementId = element.id;
        }
      })
      // this.request.step.elementId = element.id;
      // this.request.step.elementName = element.name;
    },
    setOperation(option){
      console.log('option o%', option)
      this.request.step.operationType = option[0]
      this.request.step.operationId = option[1]
    },
    getOperations(){

      this.$get('/ui/element/get/operation', response => {
        let typeMap = new Map()
        let resData = response.data
        resData.forEach( data => {
          let type = data.type
          let operation = data.operation
          let id = data.id
          if(typeMap.has(type)){
            let option = {
              'value': id,
              'label': operation
            }
            typeMap.get(type).children.push(option)
          }
          else{
            let option = {
              'value': type,
              'label': type,
              'children': [{'value': id, 'label': operation}]
            }
            typeMap.set(type, option)
          }
        })
        this.operations = Array.from(typeMap.values());

        console.log('this.operations o%', this.operations)
      });
    },
    getElements(){
      let pageId = this.request.step.pageId;
      if(pageId){
      this.$get('/ui/page/releated/element/' + pageId, response => {
        this.elements = response.data;
      });
    }},
    getPages() {
      this.$post('/ui/page/listAll', {projectId: getCurrentProjectID()}, response => {
        this.pages = response.data;
      });
    },
    pathChange() {
      this.isUrl = false;
      if (!this.request.path || this.request.path.indexOf('?') === -1) return;
      let url = this.getURL(this.addProtocol(this.request.path));
      if (url && this.isUrl) {
        this.request.path = decodeURIComponent(this.request.path.substr(0, this.request.path.indexOf("?")));
      }
    },
    urlChange() {
      this.isUrl = false;
      if (this.request.isRefEnvironment) {
        this.pathChange();
      } else {
        if (!this.request.url || this.request.url.indexOf('?') === -1) return;
        let url = this.getURL(this.addProtocol(this.request.url));
        if (url) {
          let paramUrl = this.request.url.substr(this.request.url.indexOf("?") + 1);
          if (paramUrl && this.isUrl) {
            this.request.url = decodeURIComponent(this.request.url.substr(0, this.request.url.indexOf("?")));
          }
        }
      }
    },
    addProtocol(url) {
      if (url) {
        if (!url.toLowerCase().startsWith("https") && !url.toLowerCase().startsWith("http")) {
          return "https://" + url;
        }
      }
      return url;
    },
    getURL(urlStr) {
      try {
        let url = new URL(urlStr);
        url.searchParams.forEach((value, key) => {
          if (key && value) {
            this.isUrl = true;
            this.request.arguments.splice(0, 0, new KeyValue({name: key, required: false, value: value}));
          }
        });
        return url;
      } catch (e) {
        this.$error(this.$t('api_test.request.url_invalid'), 2000);
      }
    },
    setDomain() {
      this.$emit("setDomain");
    }
  }
}
</script>

<style scoped>
.server-input {
  width: 50%;
}

span {
  margin-right: 8px;
}

.row {
  margin: 15px 10px
}

.scenario-step-request-name {
  display: inline-block;
  margin: 0 5px;
  overflow-x: hidden;
  padding-bottom: 0;
  text-overflow: ellipsis;
  vertical-align: middle;
  white-space: nowrap;
  width: 120px;
}

.ms-select {
  width: 100px;
  margin-right: 10px;
}

.ms-input {
  width: 150px;
  margin-right: 10px;
}

.is-ref-environment {
  margin-left: 15px;
}
</style>















