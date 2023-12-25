<template>

  <el-form-item :label-width="labelWidth" prop="uiElementDTOS" >
    <el-table
      :data="form.uiElementDTOS"
      class="tb-edit"
      border
      size="mini"
      :default-sort="{prop: 'num', order: 'ascending'}"
      highlight-current-row>
      <el-table-column :label="$t('test_track.case.number')" prop="num" min-width="10%"></el-table-column>
      <el-table-column v-if="false" label="id" prop="id" width="0%"></el-table-column>
      <el-table-column :label="$t('ui.element_name')" prop="name" min-width="35%">
        <template v-slot:default="scope">
          <el-input
            class="table-edit-input"
            size="mini"
            :disabled="readOnly"
            type="textarea"
            :autosize="{ minRows: 1, maxRows: 6}"
            :rows="2"
            v-model="scope.row.name"
            :placeholder="$t('commons.input_content')"
            clearable/>
        </template>
      </el-table-column>
      <el-table-column :label="$t('ui.find_by')" prop="locationType" min-width="35%">
        <template v-slot:default="scope">
          <el-select filterable ref="select"
                     v-model="scope.row.locationType"
                     :clearable="true"
                     placeholder="定位方式"
                     size="small"
                     class="ms-http-select"
          >
            <el-option v-for="item in locationTypes" :key="item.id" :label="item.locationType" :value="item.id"></el-option>
          </el-select>

        </template>
      </el-table-column>
      <el-table-column :label="$t('ui.find_value')" prop="location" min-width="35%">
        <template v-slot:default="scope">
          <el-input
            class="table-edit-input"
            size="mini"
            :disabled="readOnly"
            type="textarea"
            :autosize="{ minRows: 1, maxRows: 6}"
            :rows="2"
            v-model="scope.row.location"
            :placeholder="$t('commons.input_content')"
            clearable/>
        </template>
      </el-table-column>
      <el-table-column :label="$t('ui.element_description')" prop="description" min-width="35%">
        <template v-slot:default="scope">
          <el-input
            class="table-edit-input"
            size="mini"
            :disabled="readOnly"
            type="textarea"
            :autosize="{ minRows: 1, maxRows: 6}"
            :rows="2"
            v-model="scope.row.description"
            :placeholder="$t('commons.input_content')"
            clearable/>
        </template>
      </el-table-column>
      <el-table-column :label="$t('commons.input_content')" min-width="30%">
        <template v-slot:default="scope">
          <el-button
            type="primary"
            :disabled="readOnly"
            icon="el-icon-plus"
            circle size="mini"
            @click="handleAddStep(scope.$index, scope.row)"></el-button>
          <el-button
            icon="el-icon-document-copy"
            type="success"
            :disabled="readOnly"
            circle size="mini"
            @click="handleCopyStep(scope.$index, scope.row)"></el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            circle size="mini"
            @click="handleDeleteStep(scope.$index, scope.row)"
            :disabled="readOnly || (scope.$index == 0 && form.uiElementDTOS.length <= 1)"></el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-form-item>
</template>

<script>
import {getCurrentProjectID} from "@/common/js/utils";

export default {
  name: "PageElementItem",
  props: {
    labelWidth: String,
    form: Object,
    readOnly: Boolean,

  },
 watch: {
   form(val){
     console.log('watch o%', val)
     this.form = val

}
 },
  data() {
    return {
      loading: true,
      locationTypes: []
    }
  },
  created() {
    console.log('form  o%', this.form)
    this.getLocationTypes();
    // this.$get('/ui/page/releated/element/'+ this.form.id, response => {
    //     this.form.uiElementDTOS = response.data
    //   this.loading = true
    //     console.log("this.form.uiElementDTOS o%",this.form.uiElementDTOS)
      if (!this.form.uiElementDTOS || this.form.uiElementDTOS.length < 1) {
      this.form.uiElementDTOS = [{
        num: 1,
        name: '',
        location: '',
        locationType: '',
        description: ''
      }];
    }
  // })
    },
  methods: {
    getLocationTypes() {
      this.$get('/ui/element/get/locationType', response => {
        this.locationTypes = response.data;
      });
    },
    handleAddStep(index, data) {
      let step = {};
      step.num = data.num + 1;
      step.name = '';
      step.location = '';
      step.locationType = '';
      step.description = '';
      this.form.uiElementDTOS.forEach(step => {
        if (step.num > data.num) {
          step.num++;
        }
      });
      this.form.uiElementDTOS.splice(index + 1, 0, step);
    },
    handleCopyStep(index, data) {
      let step = {};
      step.num = data.num + 1;
      step.name = data.name;
      step.location = data.location;
      step.locationType = data.locationType;
      step.description = data.description;
      this.form.uiElementDTOS.forEach(step => {
        if (step.num > data.num) {
          step.num++;
        }
      });
      this.form.uiElementDTOS.splice(index + 1, 0, step);
    },
    handleDeleteStep(index, data) {
      if(data.id){
        let alertMsg = '确认删除该元素？'
        this.$alert(alertMsg, '', {
          confirmButtonText: this.$t('commons.confirm'),
          cancelButtonText: this.$t('commons.cancel'),
          callback: (action) => {
            if (action === 'confirm') {
              this.$post('/ui/element/delete', {"id": data.id}, () => {
                this.$success(this.$t('commons.delete_success'))
              });
            }
          }
        });
      }
      this.form.uiElementDTOS.splice(index, 1);
      this.form.uiElementDTOS.forEach(step => {
        if (step.num > data.num) {
          step.num--;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
