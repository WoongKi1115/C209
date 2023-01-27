<template>
  <div class="wrapA">
    <div class="wrap-mode-btn">
      <img src="../../assets/ice_btn.png" @click="goMakeRoomIce">
      <img src="../../assets/game_btn.png">
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  methods: {
    goMakeRoomIce() {
      router.push({ name: "MakeRoomIce" });
    },
  }
};
</script>

<style>
</style>