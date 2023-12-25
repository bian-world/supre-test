<template>
  <span>
    <span>{{title}}</span>
    <span class="el-step__icon is-text ms-api-col ms-header" :class="{'fix-color': fixColor}" v-if="count > 0">
      <span class="el-step__icon-inner">{{count}}</span>
    </span>
  </span>
</template>

<script>
export default {
  name: "TabPaneCount",
  props: ['title', 'count', 'fixColor']
}
</script>

<style scoped>
.ms-header {
  /*background: #63B8FF;*/
  /*color: white;*/
  color: #63B8FF;
  height: 20px;
  border: 0 !important;
  display: inline-block;
  /*border-radius: 42%;*/
  margin-left: 6px;
}

.fix-color {
  background: #63B8FF;
  color: white;
  /*color: #63B8FF;*/
  /*height: 20px;*/
  /*border: 0px !important;*/
  border-radius: 42%;
  margin-left: 4px;
}

/deep/ .el-step__icon-inner{
  font-size: 10px;
  font-weight: 1;
}

</style>
