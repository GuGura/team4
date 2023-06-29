<script setup>
import {defineProps} from 'vue'
import {useModalStore} from "../../../../script/stores/modal";
import CreateRoomModal from "@/components/modal/CreateRoomModal.vue";

const props = defineProps({
  channel_title: String
});
const modalStore = useModalStore();

function btnCreateRoom(){
  modalStore.openClose('RoomToggle')
  modalStore.open('CreateRoom')
}
</script>

<template>
  <div id="chatRooms_Header" @click="modalStore.openClose('RoomToggle')">
    <div style="font-size: 18px;font-weight: bold; color: #fff;">
      {{ props.channel_title }}
    </div>
    <div style="display: flex; height: 25px">
      <img src="/img/sidebar/down.png" style="width: 100%;height: 100%">
    </div>
  </div>
  <div id="toggle" v-if="modalStore.modal.RoomToggle ===true">
    <div>초대코드 생성</div>
    <div @click="btnCreateRoom()">방 생성</div>
    <div>서버장 넘기기</div> <!-- 오너일때-->
    <div>매니저 위임</div>  <!-- 오너일때-->
    <div>추방</div>       <!-- 오너 or 매니저 일때-->
    <div>서버 나가기</div>   <!-- 유저 or 매니저-->
  </div>
  <CreateRoomModal v-if="modalStore.modal.CreateRoom === true"/>
</template>

<style scoped>
#toggle {
  color: #fff;
  position: absolute;
  top: 53px;
  left: 10px;

  gap: 10px;
  display: flex;
  flex-direction: column;
  padding: 5px 10px;
  width: 220px;
  border-radius: 5px;
  background-color: #111214;
}

#toggle > div {
  color: #B5BAC1;
  cursor: pointer;
  padding: 7px 5px;
  border-radius: 3px;
  display: flex;
}

#toggle div:nth-last-child(1) {
  color: red;
}
#toggle div:nth-of-type(1) {
  color: #4752C4;
}

#toggle div:hover {
  background-color: #4752C4;
  color: #fff;
}

#chatRooms_Header {
  display: flex;
  min-width: 240px;
  align-items: center;
  padding: 0 15px;
  cursor: pointer;
  height: 50px;
  top: 0;
  left: 0;
  position: absolute;
  border-bottom: 1px solid #1F2123;
  border-radius: 10px 0 0 0;
  justify-content: space-between;
  z-index: 12;
}

#chatRooms_Header:hover {
  background: #36373D;
}

img {
  width: 100%;
  height: 100%;
}
</style>