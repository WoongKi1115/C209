<template>
  <div class="wrapA">
    <h1>너나들이</h1>
    <img src="../../assets/ani3.png" class="img-ani3" />
    <div class="wrap-input-pin">
      PIN:
      <input type="number" id="pin_num" />
      <img src="../../assets/enter_btn.png"/>
    </div>
    <!-- <div>
      <img src="../../assets/makeroom_btn.png" @click="goLogin" />
    </div> -->
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
