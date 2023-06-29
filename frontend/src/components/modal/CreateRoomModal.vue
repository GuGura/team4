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
    <div style="display: flex;align-items: center;justify-content: space-between">
      <div style="color: #fff;font-size: 21px">채널 만들기</div>
      <div id="exit" @click="closeModal">
        <img src="/img/serverlist/exit.png">
      </div>
    </div>
  </div>
</template>

<style scoped>
#exit{
  height:16px;
  cursor: pointer;
  filter: opacity(0.5);
}
#exit:hover{
  filter: opacity(1);
}
#modal {
  width: 430px;
  height: 300px;
  background-color: #313338;
  z-index: 13;
  position: fixed;
  border-radius: 5px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  padding: 20px 15px;
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
img{
  width: 100%;
  height: 100%;
}
</style>