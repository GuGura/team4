<script setup>
import {useModalStore} from "../../../script/stores/modal";

const modalStore = useModalStore();

function closeModal() {
  modalStore.terminate('CreateRoom')
}
</script>

<template>
  <div id="background" @click="closeModal"></div>
  <div id="modal">

  </div>
</template>

<style scoped>
#modal{
  width: 500px;
  height: 300px;
  background-color: #313338;
  z-index: 13;
  position: fixed;
  border-radius: 5px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
#background {
  background-color: black;
  filter: opacity(0.5);
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  position: fixed;
  z-index: 12;
}
</style>