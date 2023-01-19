<template>
  <header>
      <img src="../../assets/back_btn.png" @click="goBack" class="back-btn ">
      <img src="../../assets/logo3.png" @click="goHome" class="logo">
      <div>사용자님?</div>
  </header> 
</template>

<script>
import router from "@/router";
export default {
  methods: {
    goHome() {
      router.push({ name: "Home" });
    },
    goBack() {
      this.$router.go(-1);
  }
}
}
</script>

<style>

</style>