<template>
  <div class="wrapA">
    <h1>로그인</h1>
    <div class="wrap-inputs-login">
      <div class="input-with-label">
        <label for="user_id"
          >ID:
          <input type="text" class="input-id" id="user_id" />
        </label>
      </div>
      <div class="input-with-label pt10">
        <label for="user_password"
          >PW:
          <input
            type="password"
            class="input-login-password"
            id="user_password"
          />
        </label>
      </div>
    </div>
    <img src="../../assets/in_btn.png" class="btn-img" @click="login" />
    <div class="wrap-signup-btn">
      <router-link to="/signup"> 아이디가 없으신가요? </router-link>
      <img
        src="../../assets/signup_btn.png"
        class="btn-img-signup"
        @click="goSignUp"
      />
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref } from "vue";
import axios from "axios";

export default {
  setup() {
    const state = ref({
      credentials: {
        email: null,
        password: null,
      },
    });
    const login = () => {
      axios({
        methods: "post",
        url: "url자리",
        data: state.value.credentials,
      })
        .then(() => {
          alert("성공");
          router.push({ name: "SelectModeView" });
        })
        .catch((err) => {
          console.log(err);
        });
    };
    const goSignUp = () => {
      router.push({ name: "Signup" });
    };
    return {
      login,
      state,
      goSignUp,
    };
  },
};
</script>

<style>
</style>
