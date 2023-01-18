<template>
  <header>
      <button @click="goBack">뒤버튼</button>
      <img src="../../assets/penguin.png" @click="goHome">
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