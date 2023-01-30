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
  setup() {
    const goMakeRoomIce = () => {
      router.push({ name: "MakeRoomIce" });
    }
    return {
      goMakeRoomIce,
    }
  },
  methods: {
   
  }
};
</script>

<style>
</style>