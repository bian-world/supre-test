<template>
  <div>
    <ms-tag v-if="value === 'P0' || value === '1'" type="danger" :content="value" effect="plain"/>
    <ms-tag v-else-if="value === 'P1' || value === '2'"  type="warning"  :content="value" effect="plain"/>
    <ms-tag v-else-if="value === 'P2' || value === '3'" effect="plain" type="success" :content="value" />
    <ms-tag v-else-if="value === 'P3' || value === '4'" effect="plain" type="info" :content="value" />
    <ms-tag v-else-if="value" :content="value"/>
  </div>

</template>

<script>
    import MsTag from "../../../../common/components/MsTag";
    export default {
      name: "PriorityTableItem",
      components: {MsTag},
      props: {
        value: {
          type: String
        }
      }
    }
</script>

<style scoped>

/deep/ .el-tag--mini {
  height: 25px;
  padding: 0 4px;
  line-height: 25px;
  width: 25px;
  text-align: center;
  border-radius: 18px;
  border-width: 2px;
}

/deep/ .el-tag--plain.el-tag--warning {
  background-color: #fff;
  border-color: #ff9800;
  color: #ff9800;
}

/deep/ .el-tag--plain.el-tag--danger {
  background-color: #fff;
  border-color: #d50000;
  color: #d50000;
}

/deep/ .el-tag--plain.el-tag--success {
  background-color: #fff;
  border-color: #2098ee;
  color: #2098ee;
}

/deep/ .el-tag--plain.el-tag--info {
  background-color: #fff;
  border-color: #009688;
  color: #009688;
}

</style>
