<template>
  <div class="wrapA">
    <h1>로그인</h1>
    <div class="wrap-inputs-login">
      <div class="input-with-label">
        <label for="user_id"
          >ID:
          <input type="text" class="input-id" id="user_id" name="user_id" />
        </label>
      </div>
      <div class="input-with-label pt10">
        <label for="user_password"
          >PW:
          <input
            type="password"
            class="input-login-password"
            id="user_password"
            name="user_password"
          />
        </label>
      </div>
    </div>
    <img src="../../assets/in_btn.png" class="btn-img">
    <div class="wrap-signup-btn">
      <router-link to="/signup"> 아이디가 없으신가요? </router-link>
      <img src="../../assets/signup_btn.png"  class="btn-img-signup" @click="goSignUp"/>
    </div>
  </div>
</template>

<script>
import router from "@/router";


export default {
  methods: {
    goSignUp() {
      router.push({ name: "Signup" });
    },
  }
};
</script>

<style>
</style>
