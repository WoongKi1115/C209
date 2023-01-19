<template>
  <div class="wrapA">
    <h1>Ice Breaking</h1>
    <div class="wrap-btn">
      <button class="btn-ice" @click="goMakeRoomIce">ICE1</button>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  methods: {
    goMakeRoomIce() {
      router.push({ name: "MakeRoomIce" });
    }, // 로그인 여부에 따라 보내야함 
  }
};
</script>

<style>
</style>