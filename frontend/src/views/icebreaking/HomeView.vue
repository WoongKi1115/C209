<template>
  <div class="wrapA">
    <h1>너나들이</h1>
    <div class="wrap-btn mt100">
      <button class="btn-ice" @click="goIceMain">Ice Breaking</button>
      <button class="btn-ice" @click="goGameMain">Game</button>
    </div>
    <div class="input-with-label pt100">
      <label for="pin_num" class="input-pin"
        >PIN:
        <input type="number" id="pin_num" name="pin" />
        <button>입장하기</button>
      </label>
    </div>
    <div class="wrap-btn mt100">
      <button class="btn-login" @click="goLogin">로그인 하러가기</button>
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
