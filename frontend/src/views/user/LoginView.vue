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
    <div class="router-link mt50">
      <router-link to="/signup"> 아이디가 없으신가요? </router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>
