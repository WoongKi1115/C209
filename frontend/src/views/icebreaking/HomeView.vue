<template>
  <div class="wrapA">
    <img src="../../assets/title.png" />
    <div class="wrap-btn">
      <img src="../../assets/ani3.png" class="img-ani3" />
    </div>
    <div class="wrap-input-pin">
      PIN:
      <input type="number" id="pin_num" />
      <img src="../../assets/enter_btn.png" />
    </div>
    <img src="../../assets/makeroom_btn.png" @click="goLogin" />
  </div>
</template>

<script>
import router from "@/router";

export default {
  methods: {
    goIceMain() {
      router.push({ name: "IceMain" });
    },
    goGameMain() {
      router.push({ name: "GameMain" });
    },
    goLogin() {
      router.push({ name: "Login" });
    },
  },
};
</script>

<style>
</style>
