<template>
  <p>
    <span>
      {{title}}
    </span>
    <span>
      <slot></slot>
    </span>
  </p>
</template>

<script>
export default {
  name: "MsFormDivider",
  props: ['title']
};
</script>

<style scoped>
p {
  padding: 3px 5px;
  font-size: 14px;
  font-weight: bold ;
  border-radius: 0px;
  border-left: 4px solid #63B8FF;
  margin: 5px 5px 10px 5px;
}

</style>
