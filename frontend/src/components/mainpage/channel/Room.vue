<script setup>
import {defineProps, reactive} from 'vue'
import {useLobbyStore} from "../../../../script/stores/lobby";

let props = defineProps({
  item: Object,
  username: String,
})
let roomMembers = reactive([]);
const lobbyStore = useLobbyStore();

function video() {
  let isExist = false;
  roomMembers.forEach((r,index)=>{
    console.log(r.username)
    if(r.username === props.username){
      isExist = true;
      roomMembers.splice(index,1)
    }
  })
  if (!isExist){
    let roomId = props.item.roomId;
    let userEmail = lobbyStore.user.email
    roomMembers.push({roomId:roomId,username:props.username,userEmail:userEmail})
  }
}
</script>

<template>
  <li class="btnRoom" @click="video()">
    <div>
      <img src="/img/channel/speak.png">
    </div>
    <div class="MyMember_Info">
      <div class="MyMember_Name">
        {{item.name}}
      </div>
    </div>
  </li>
  <div style="color:#fff">{{props.item}}</div>
  <div v-for="(roomMember,idx) in roomMembers " :key="idx" >
    <div class="btnRoomMember" v-if="roomMembers !== ''">
      <div>
        <img src="/img/channel/userIcon.png">
      </div>
      <div class="MyMember_Info">
        <div class="MyMember_Name">
          {{roomMember.username}}
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
.btnRoomMember {
  display: flex;
  height: 30px;
  gap: 5px;
  border-radius: 5px;
  padding: 0 15px;
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
li {
  margin: 0;
}
</style>