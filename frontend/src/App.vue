<template>
  <div id="app">
    <!-- <HeaderView /> -->
    <router-view> </router-view>
  </div>
</template>

<script>

import "./components/css/style.scss";

// import HeaderView from "./components/common/HeaderView.vue";
export default {
  name: "App",
  components: {
    // HeaderView,
  },
};
</script>

