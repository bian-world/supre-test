<template>
  <span>
     <el-select v-if="(data.type === 'select' && data.name !== '用例等级')|| data.type === 'multipleSelect'"
                :disabled="disabled"
                :multiple="data.type === 'multipleSelect'"
                @change="handleChange"
                filterable v-model="data[prop]" :placeholder="$t('commons.default')">
      <el-option
        v-for="(item,index) in data.options ? data.options : []"
        :key="index"
        @change="handleChange"
        :label="getTranslateOption(item)"
        :value="item.value">
      </el-option>
    </el-select>

<!--    用例等级的样式特殊处理-->
     <el-select v-if="(data.type === 'select' && data.name === '用例等级')|| data.type === 'multipleSelect' "
                :disabled="disabled"
                :multiple="data.type === 'multipleSelect'"
                @change="handleChange"
                :popper-append-to-body="false"
                filterable v-model="data[prop]" :placeholder="$t('commons.default')">
<!--                :popper-append-to-body="false"-->
<!--                :popper-class="optionsContent"-->
      <el-option
        v-for="(item,index) in data.options ? data.options : []"
        :key="index"
        @change="handleChange"
        :label="getTranslateOption(item)"
        :value="item.value">
<!--        <priority-table-item :value="item.text">{{getTranslateOption(item)}}</priority-table-item>-->
        <div :class="getClass(item)" >{{getTranslateOption(item)}}</div>
<!--        <span style="float: right;color: #ccc;">{{item.value}}</span>-->

      </el-option>
    </el-select>

    <el-cascader
      v-else-if="data.type === 'cascadingSelect'"
      expand-trigger="hover"
      @change="handleChange"
      :props="{label: 'text'}"
      :options="data.options"
      v-model="data[prop]">
    </el-cascader>

    <el-input
      v-else-if="data.type === 'textarea'"
      type="textarea"
      @change="handleChange"
      :rows="2"
      :disabled="disabled"
      :placeholder="$t('commons.input_content')"
      class="custom-with"
      v-model="data[prop]">
    </el-input>

    <el-checkbox-group
      v-else-if="data.type === 'checkbox'"
      :disabled="disabled"
      v-model="data[prop]">
      <el-checkbox v-for="(item, index) in data.options ? data.options : []"
                   :key="index"
                   @change="handleChange"
                   :label="item.value">
        {{ getTranslateOption(item) }}
      </el-checkbox>
    </el-checkbox-group>

    <el-radio
      v-else-if="data.type === 'radio'"
      v-model="data[prop]"
      :disabled="disabled"
      v-for="(item,index) in data.options ? data.options : []"
      :key="index"
      @change="handleChange"
      :label="item.value">
      {{ getTranslateOption(item) }}
    </el-radio>

    <el-input-number
      v-else-if="data.type === 'int'"
      v-model="data[prop]"
      :disabled="disabled"
      @change="handleChange"/>

    <el-input-number
      v-else-if="data.type === 'float'"
      :disabled="disabled"
      @change="handleChange"
      v-model="data[prop]" :precision="2" :step="0.1"/>

     <el-date-picker
       class="custom-with"
       @change="handleChange"
       v-else-if="data.type === 'date' || data.type === 'datetime'"
       :disabled="disabled"
       v-model="data[prop]"
       :type="data.type === 'date' ? 'date' : 'datetime'"
       :placeholder="$t('commons.select_date')">
    </el-date-picker>

    <el-select v-else-if="data.type === 'member' || data.type === 'multipleMember'"
               :multiple="data.type === 'multipleMember'"
               @change="handleChange"
               :disabled="disabled"
               filterable v-model="data[prop]" :placeholder="$t('commons.default')">
       <el-option
         v-for="(item) in memberOptions"
         :key="item.id"
         :label="item.name + ' (' + item.id + ')'"
         :value="item.id">
       </el-option>
    </el-select>

    <ms-input-tag v-else-if="data.type === 'multipleInput'"
                  @input="handleChange"
                  :read-only="disabled" :currentScenario="data" :prop="prop"/>

    <ms-mark-down-text v-else-if="data.type === 'richText'"
                       :prop="prop"
                       @change="handleChange"
                       :data="data" :disabled="disabled"/>

<!--    <el-input class="custom-with"-->
<!--              @input="handleChange"-->
<!--              :disabled="disabled"-->
<!--              v-else v-model="data[prop]"/>-->

  </span>

</template>

<script>
import MsTableColumn from "@/business/components/common/components/table/MsTableColumn";
import {getCurrentProjectID} from "@/common/js/utils";
import MsInputTag from "@/business/components/api/automation/scenario/MsInputTag";
import MsMarkDownText from "@/business/components/track/case/components/MsMarkDownText";

export default {
  name: "CustomFiledComponent",
  components: {MsMarkDownText, MsInputTag, MsTableColumn},
  props: [
    'data',
    'prop',
    'form',
    'disabled'
  ],
  data() {
    return {
      memberOptions: [],
    };
  },
  mounted() {
    this.$post('/user/project/member/tester/list', {projectId: getCurrentProjectID()}, response => {
      this.memberOptions = response.data;
    });
  },
  methods: {
    getTranslateOption(item) {
      return item.system ? this.$t(item.text) : item.text;
    },
    handleChange() {
      if (this.form) {
        this.$set(this.form, this.data.name, this.data[this.prop]);
      }
    },
    getClass(item){
      return "label-pri"+item.text
    }
  }
};
</script>

<style scoped>
.el-select {
  width: 100%;
}
.el-date-editor.el-input {
  width: 100%;
}
.custom-with >>> .el-input__inner{
  height: 32px;
}
>>> .el-input--suffix .el-input__inner{
  height: 32px;
}
/*::v-deep .optionsContent {*/

/*  display: table-cell;*/
/*  border-radius: 18px;*/
/*  background-color: #0052d9;*/
/*  width: 25px;*/
/*  height: 25px;*/
/*  text-align: center;*/
/*  vertical-align: middle;*/
/*  color: #fff;*/
/*}*/
/*::v-deep .optionsContent {*/
/*  color: #d50000;*/
/*  border-color: #d50000;*/
/*}*/

.label-priP0 {
  color: #d50000;
  border-color: #d50000;
  border: 2px solid;
  border-radius: 18px;
  width: 25px;
}
.label-priP1 {
  color: #ff9800;
  border-color: #ff9800;
  border-radius: 18px;
  border: 2px solid;
  width: 25px;
}
.label-priP2 {
  color: #2098ee;
  border-color: #2098ee;
  border-radius: 18px;
  border: 2px solid;
  width: 25px;
}
.label-priP3{
  color: #009688;
  border-color: #009688;
  border-radius: 18px;
  border: 2px solid;
  width: 25px;
}

::v-deep .el-select-dropdown__item {
  padding:0 4px;
  display: table-cell;
  text-align: center;
  vertical-align: middle;
  height: 25px !important;
  line-height: 25px !important;
}

::v-deep .el-select-dropdown__list {
  padding: 2px 10px;
}

/*/deep/.el-input__inner {*/
/*  padding:0 4px;*/
/*  text-align: center;*/
/*  vertical-align: middle;*/
/*  height: auto !important;*/
/*  line-height: 25px !important;*/
/*  color: var(--color);*/
/*  border-color: #009688;*/
/*  border-radius: 18px;*/
/*  border: 2px solid;*/
/*  width: 25px;*/
/*}*/




</style>
