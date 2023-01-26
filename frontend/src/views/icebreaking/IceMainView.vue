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
    },
  }
};
</script>

<style>
</style>