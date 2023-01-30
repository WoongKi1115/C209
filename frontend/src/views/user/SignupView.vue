<template>
  <div class="wrapA">
    <h1>회원가입</h1>
    <div class="wrap-inputs-signup">
      <div class="input-with-label">
        <label for="user_email"
          >이메일 :
          <input
            type="email"
            class="input-email"
            id="user_email"
            v-model="state.credentials.email"
          />
          <button>중복확인</button>
          <p v-show="state.valid.email" class="input-error">
            이메일 주소를 정확히 입력해주세요.
          </p>
        </label>
      </div>
      <div class="input-with-label">
        <label for="user_nickname"
          >닉네임 :
          <input
            type="text"
            class="input-nickname"
            id="user_nickname"
            v-model="state.credentials.nickname"
          />
          <button>중복확인</button>
        </label>
      </div>
      <div class="input-with-label">
        <label for="user_password"
          >PW:
          <input
            type="password"
            class="input-signup-password"
            id="user_password"
            v-model="state.credentials.password"
          />
        </label>
      </div>
      <div class="input-with-label">
        <label for="user_password"
          >PWCONFIRM:
          <input
            type="password"
            class="input-signup-password"
            id="user_password_confirm"
            v-model="state.credentials.passwordConfirm"
          />
        </label>
      </div>
    </div>
    <div class="wrap-btn">
      <button @click="signup">JOIN</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import * as EmailValidator from "email-validator";
import { ref } from "vue";

export default {
  setup() {
    const state = ref({
      credentials: {
        email: null,
        nickname: null,
        password: null,
        passwordConfirm: null,
      },
      valid: {
        email: false,
      },
    });

    const signup = () => {
      console.log(state.value.credentials);
       axios({
        methods: "post",
        url: "url자리",
        data: state.value.credentials,
      })
      .then(() => {
        alert("성공")
      })
      .catch((err) => {
        console.log(err)
      })
    };
    
    const checkEmail = () => {
      let isValid = EmailValidator.validate(state.value.credentials.email);
      state.value.valid.email = !isValid;
    };

    return {
      state,
      signup,
      checkEmail,
    };
  },
  watch: {
    "state.credentials.email": {
      handler() {
        this.checkEmail();
      },
      deep: true,
    },
  },
};
</script>

<style>
</style>
