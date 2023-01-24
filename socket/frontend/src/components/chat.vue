<template>
  <div>
    <h1>chating page</h1>
    <textarea v-model="message"/>
    <button style="display: block;" @click="sendMessage">전송</button>
  </div>
</template>

<script>
import { io } from 'socket.io-client'
export default {
    name:'chat',
    data() {
        return {
            socket:null,
            message:'',
            receivedMessage: [],
        }
    },
    async created() {
        this.socket = io('http://localhost:3000')
        this.socket.on('connect', ()=> {
            console.log('소켓 열림')
        })
        this.socket.on('message', (messages)=> {
            this.receivedMessage = messages
        })
    },
    methods: {
        sendMessage() {
            this.socket.emit('send', this.message)
            this.message = ''
        }
    }
}
</script>

<style>

</style>