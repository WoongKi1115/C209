<template>
  <div class="wrapA">
    <h1>너나들이</h1>
    <img src="../../assets/ani3.png" class="img-ani3"/>
    <div class="wrap-input-pin">
      <span> PIN: </span>
      <input type="number" id="pin_num" />
      <img src="../../assets/enter_btn.png" />
    </div>
    <img src="../../assets/makeroom_btn.png" class="btn-img" @click="goLogin" />
  </div>
</template>

<script>
import router from "@/router";

export default {
  setup() {
    
    const goLogin = () => {
      router.push({ name: "Login" });
    }
    return {
      goLogin,
    }
  },
  methods: {

    // goLogin() {
    //   router.push({ name: "Login" });
    // },
  },
};
</script>

<style>
</style>
