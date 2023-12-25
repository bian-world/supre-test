<template>
  <el-col>
    <ui-header-menus/>
    <div>
      <transition>
        <keep-alive>
          <router-view :baseUrl="baseUrl"/>
        </keep-alive>
      </transition>
    </div>
  </el-col>
</template>
<script>


import UiHeaderMenus from "@/business/components/ui/head/UiHeaderMenus";
export default {
  name: "UiTest",
  components: {UiHeaderMenus},
  data() {
    return {
      baseUrl: "/ui"
    }
  }
}

</script>

<style scoped>

</style>
