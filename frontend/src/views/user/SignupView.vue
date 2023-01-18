<template>
  <div class="wrapA">
    <h1>회원가입</h1>
    <div class="wrap-inputs-signup">
      <div class="input-with-label">
        <label for="user_email"
          >이메일 :
          <input
            type="text"
            class="input-email"
            id="user_email"
            name="user_email"
          />
          <button>중복확인</button>
        </label>
      </div>
      <div class="input-with-label">
        <label for="user_nickname"
          >닉네임 :
          <input
            type="text"
            class="input-nickname"
            id="user_nickname"
            name="user_nickname"
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
            name="user_password"
          />
        </label>
      </div>
      <div class="input-with-label">
        <label for="user_password"
          >PWCONFIRM:
          <input
            type="password"
            class="input-signup-password"
            id="user_password"
            name="user_password"
          />
        </label>
      </div>
    </div>
    <div class="wrap-btn">
      <button>JOIN</button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>
