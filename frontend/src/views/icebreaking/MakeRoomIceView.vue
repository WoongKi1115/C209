<template>
  <div class="wrapA">
    <h1>ICE. 1</h1>
    <div class="wrap-count-num">
      <button @click="minusNum">-</button>
      <span v-bind:numOfMember="numOfMember">{{ numOfMember }}</span>
      <button @click="plusNum">+</button>
    </div>
    <button @click="goIceQr">방만들기</button>
  </div>
</template>

<script>

import router from "@/router";
import { ref } from "vue"
import { useStore } from "vuex";

export default {
	setup() {
		const store = useStore()
		const numOfMember = ref(5)

		const minusNum = () => {
			if (numOfMember.value > 4) {
				numOfMember.value -=1
			}
		}
		const plusNum = () => {
			if (numOfMember.value < 6) {
				numOfMember.value +=1
			}
		}
		const goIceQr = () => {
			store.state.numOfMember = numOfMember.value
			router.push({ name: "IceQr"});
			// console.log(store.state.numOfMember)
		}
		return {
			numOfMember,
			minusNum,
			plusNum,
			goIceQr
		}
	},
  methods: {
  },
};
</script>

<style>
</style>