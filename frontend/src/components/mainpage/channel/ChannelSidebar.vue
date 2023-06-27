<script setup>
import { ref, onMounted, onNextTick } from 'vue';
import SidebarMyInfo from "@/components/sidebar/SidebarMyInfo.vue";
import { useChannelStore } from "../../../../script/stores/channel";
import api from "axios";

const channelStore = useChannelStore();

const room_name = ref(''); // ChatRoom Name
const textChatrooms = ref([]); // Text Chat Room List
const voiceChatrooms = ref([]); // Voice Chat Room List
const room_type = ref(false); // Text and Voice Type

const findAllRoom = () => {
  api.get('/chat/rooms').then(response => {
    console.log(response.data);
    textChatrooms.value = response.data.filter(item => item.roomType === false);
    voiceChatrooms.value = response.data.filter(item => item.roomType === true);
  });
};

const createRoom = () => {
  if ("" === room_name.value) {
    alert("방 제목을 입력해 주십시요.");
    return;
  } else {
    var params = new URLSearchParams();
    params.append("name", room_name.value);
    params.append("room_type", room_type.value);
    api.post('/chat/room', params)
        .then(
            response => {
              alert(response.data.name + "방 개설에 성공하였습니다.")
              room_name.value = '';
              findAllRoom();
            }
        )
        .catch(() => {
          alert("채팅방 개설에 실패하였습니다.");
        });
  }
};

const enterRoom = (roomId) => {
  var sender =
  localStorage.setItem('wschat.roomId', roomId);
  window.location.href = '/channel/:channel_title';

  onNextTick(() => {
    window.location.href = (`/channel/:channel_title/chat/room/enter/${roomId}`);
  });

  console.log("RoomList.vue sender : " + sender);
  console.log("RoomList.vue roomId : " + roomId);
};

onMounted(() => {
  findAllRoom();
});

</script>

<template>
  <div id="side_contents">
    <div id="chatRooms_Header">{{channelStore.channelInfo.channel_title}}</div>
    <div id="side_content_info">
      <div id="chatRooms">

        <div class="chatRoom">

          <div class="roomName">
            <div>채팅</div>
          </div>

          <ul class="btnRooms">
            <li class="btnRoom" v-for="item in textChatrooms" :key="item.roomId" v-on:click="enterRoom(item.roomId)">
              <div>
                <img src="/img/channel/chat.png">
              </div>
              <div class="MyMember_Info">
                <div class="MyMember_Name">
                  {{ item.name }}
                </div>
              </div>
            </li>
          </ul>

        </div>

        <div class="voiceRoom">
          <div class="roomName">
            <div>음성 채팅</div>
          </div>

          <ul class="btnRooms">

            <li class="btnRoom" v-for="item in voiceChatrooms" :key="item.roomId" v-on:click="enterRoom(item.roomId)">
              <div>
                <img src="/img/channel/speak.png">
              </div>
              <div class="MyMember_Info">
                <div class="MyMember_Name">
                  {{ item.name }}
                </div>
              </div>
            </li>

            <div>
              <div class="btnRoomMember">
                <div>
                  <img src="/img/channel/userIcon.png" >
                </div>
                <div class="MyMember_Info">
                  <div class="MyMember_Name">
                    박재연
                  </div>
                </div>
              </div>

            </div>
          </ul>

          <div class="inputChatRoomName">
            <div>
              <label>방제목</label>
            </div>
            <input type="text" v-model="room_name" @keyup.enter="createRoom">
            <div>
              <input type="checkbox" id="roomType" v-model="room_type">
              <label for="roomType">음성채팅방으로 설정</label>
            </div>
          </div>


        </div>
      </div>
    </div>
    <SidebarMyInfo />
  </div>
</template>

<style scoped>


/**Add**/
#chatRooms_Header{
  display: flex;
  min-width: 240px;
  font-size: 18px;
  font-weight: bold;
  align-items: center;
  padding-left: 15px;
  background:#2B2D31;
  cursor: pointer;
  color: #fff;
  height: 50px;
  top: 0;
  left: 0;
  position: absolute;
  border-bottom: 1px solid #1F2123;
  border-radius: 10px 0 0 0;
}
#chatRooms_Header:hover{
  background:#36373D;
}

img{
  width: 100%;
}
/**  side_contents */
#side_contents{
  display: flex;
  flex-direction: column;
  min-width: 240px;
  background: #2b2d31;
  border-radius: 10px 0 0 0;
  z-index: 10;
  position: relative;
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none
}
#side_content_info{
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 50px 10px;
  gap: 1px;
}

#chatRooms{
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.chatRoom{
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.roomName{
  display: flex;
}
.roomName > div:nth-of-type(1){
  color: #949BA4;
  font-size: 13px;
  font-weight: bold;
  margin-top: 10px;
  padding: 0 5px;
}
.roomName > div:nth-of-type(1):hover{
  color: #fff;
  cursor: pointer;
  display: flex;
}
.btnRoom{
  display: flex;
  height: 30px;
  gap: 20px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
}
.btnRoom:hover{
  background: #36373D;
}
.btnRoom:active{
  background: #3B3D44;
}
.btnRoom > div:nth-of-type(1){
  display: flex;
  color:#fff;
  width: 30px;
}
.btnRoom > div:nth-of-type(2){
  display: flex;
  font-size: 15px;
  color:#fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}
.btnRoom > div:nth-of-type(1) >img:nth-of-type(1){
  padding: 8px;
}
.btnRooms{
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.btnRooms > div:nth-of-type(2){
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 5px;
}
.btnRoomMember{
  display: flex;
  height: 30px;
  gap: 5px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
  width: 90%;
}
.btnRoomMember:hover{
  background: #36373D;
}
.btnRoomMember:active{
  background: #3B3D44;
}
.btnRoomMember > div:nth-of-type(1){
  display: flex;
  color: #fff;
  width: 40px;
}
.btnRoomMember > div:nth-of-type(1) > img:nth-of-type(1){
  padding: 8px;
}
.MyMember_Info{
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}


input[name=message]{
  display: flex;
  width: 70%;
  height: 70%;
  margin-left: 30px;
  padding-left: 15px;
  background: #383A40;
  outline: none;
  border: none;
  color: #fff;
  border-radius: 15px;
}
</style>