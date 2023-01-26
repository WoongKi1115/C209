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

    <div class="wrap-btn mt50">
      <button class="btn-in">IN</button>
    </div>
    <div class="router-link mtp20">
      <router-link to="/signup"> 아이디가 없으신가요? </router-link>
      <img src="../../assets/signup_btn.png"  @click="goSignUp"/>
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
