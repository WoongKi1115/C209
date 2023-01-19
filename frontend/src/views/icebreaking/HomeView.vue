<template>
  <div class="wrapA">
    <img src="../../assets/title.png">
    <div class="wrap-btn">
      <img src="../../assets/ani3.png" class="img-ani3">
    </div>
    <div class="input-with-label ptp10">
      <label for="pin_num" class="input-pin"
        >PIN:
        <input type="number" id="pin_num" name="pin" />
        <button>입장하기</button>
      </label>
    </div>
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
