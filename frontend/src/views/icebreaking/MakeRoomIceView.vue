<template>
  <div class="wrapA">
    <h1>ICE. 1</h1>
    <div class="wrap-count-num">
      <button @click="minusNum">-</button>
      <span v-bind:numOfMember="numOfMember">{{ numOfMember }}</span>
      <button @click="plusNum">+</button>
    </div>
  </div>
</template>

<script>
import router from "@/router";
export default {
	data() {
		return {
			numOfMember: 5 //멤버수
		}
	},
	methods: {
		minusNum() {
			if(this.numOfMember > 4) {
				this.numOfMember -= 1
			}
		},
		plusNum() {
			if(this.numOfMember < 6) {
				this.numOfMember += 1
			}
		},
		goIceQr() {
			this.$store.state.numOfMember =this.numOfMember
      router.push({ name: "IceQr" });
		}
	}
};
</script>

<style>
</style>