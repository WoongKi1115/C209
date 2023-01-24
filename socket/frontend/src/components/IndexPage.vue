<template>
  <div v-if="user">
    <h1>접속한 유저</h1>
    <p>아이디 : {{user.id}}</p>
    <p>패스워드 : {{user.password}}</p>
    <p>이름 : {{user.name}}</p>
  </div>
</template>

<script>
export default {
    name:"IndexPage",
    data() {
        return {
            user:null,
        }
    },
    created() {
        this.$http.get('/api/login')
        .then((res)=>{
            const user = res.data.user;
            if (user) this.user = user;
        })
        .catch((err)=> {
            console.error(err);
        })
    }
}
</script>

<style>

</style>