<script setup>
import {enterRoom} from "../../../../script/chatOperations";
import {defineProps} from 'vue'

let props = defineProps({
  item: Object
})
</script>

<template>
  <li class="btnRoom" @:click="enterRoom(props.item.roomId)">
    <div>
      <img src="/img/channel/speak.png">
    </div>
    <div class="MyMember_Info">
      <div class="MyMember_Name">
        {{ props.item.name }}
      </div>
    </div>
  </li>

  <div>
    <div class="btnRoomMember">
      <div>
        <img src="/img/channel/userIcon.png">
      </div>
      <div class="MyMember_Info">
        <div class="MyMember_Name">
          박재연
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped>
.btnRoom {
  display: flex;
  height: 30px;
  gap: 20px;
  border-radius: 5px;
  align-items: center;
  cursor: pointer;
}
.btnRoomMember {
  display: flex;
  height: 30px;
  gap: 5px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
  width: 90%;
}

.btnRoomMember:hover {
  background: #36373D;
}

.btnRoomMember:active {
  background: #3B3D44;
}

.MyMember_Info {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

.btnRoomMember > div:nth-of-type(1) {
  display: flex;
  color: #fff;
  width: 40px;
}

.btnRoomMember > div:nth-of-type(1) > img:nth-of-type(1) {
  padding: 8px;
}

.MyMember_Info {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}
.btnRoom:hover {
  background: #36373D;
}

.btnRoom:active {
  background: #3B3D44;
}

.btnRoom > div:nth-of-type(1) {
  display: flex;
  color: #fff;
  width: 30px;
}

.btnRoom > div:nth-of-type(2) {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

.btnRoom > div:nth-of-type(1) > img:nth-of-type(1) {
  padding: 8px;
}



.btnRooms > div:nth-of-type(2) {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 5px;
}

li{
  margin: 0;
}
</style>