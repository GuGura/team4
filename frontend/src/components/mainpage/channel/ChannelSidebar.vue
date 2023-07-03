<script setup>
import {computed, onMounted, reactive} from 'vue';
import SidebarMyInfo from "@/components/sidebar/SidebarMyInfo.vue";
import {useChannelStore} from "../../../../script/stores/channel";
import {useLobbyStore} from "../../../../script/stores/lobby";
import api from "/script/token/axios.js";
import router from "../../../../script/routes/router";
import {useServerListStore} from "../../../../script/stores/serverlist";
import ChannelSidebarHead from "@/components/mainpage/channel/ChannelSidebarHead.vue";

const channelStore = useChannelStore();
const lobbyStore = useLobbyStore();
const serverListStore = useServerListStore();

const roomInfo = reactive({
  name: '',
  room_type: false
}); // ChatRoom Name
const textChatRooms = reactive([]); // Text Chat Room List
const voiceChatRooms = reactive([]); // Voice Chat Room List

const updateUsername = computed(() => {
  return lobbyStore.user.username
})
const updateChannelId = computed(() => {
  return serverListStore.getEndPoint;
});

const findAllRoom = async () => {
  console.log("findAllRoom")
  await api.get('/chat/rooms').then(({data}) => {
    console.log("findAllRoom Result : " + JSON.stringify(data));
    data.forEach(item => {
      if (item['roomType'] === true) voiceChatRooms.push(item)
      else if (item['roomType'] === false) textChatRooms.push(item)
    })
  }).catch(err => {
    console.log("err" + err);
  });
};

const createRoom = () => {
  if ("" === roomInfo.name) {
    alert("방 제목을 입력해 주십시요.");
  } else {
    api.post('/chat/room', roomInfo)
        .then(({data}) => {
          console.log(data)
          if (data.roomType === false) textChatRooms.push(data)
          else if (data.roomType === true) voiceChatRooms.push(data)
          alert(data.name + "방 개설에 성공하였습니다.")
          roomInfo.name = '';
        })
        .catch(() => {
          alert("채팅방 개설에 실패하였습니다.");
        });
  }
};

const enterRoom = (roomId) => {
  console.log("Start EnterRoom in ChannelSideBar.vue")
  let sender = updateUsername.value
  let channelId = updateChannelId.value
  localStorage.setItem('wschat.roomId', roomId);
  localStorage.setItem('wschat.channelId', channelId);

  console.log("ChannelSideBar.vue sender : " + sender);
  console.log("ChannelSideBar.vue roomId : " + roomId);
  console.log("ChannelSideBar.vue channelId : " + channelId);

  router.push(`/channel/${channelId}/chat/room/enter/${roomId}`);
};

onMounted(() => {
  findAllRoom();
});

</script>

<template>
  <div id="side_contents">
    <ChannelSidebarHead
        :channel_title="channelStore.channelInfo.channel_title"/>

    <div id="side_content_info">
      <div id="chatRooms">

        <div class="chatRoom">

          <div class="roomName">
            <div>채팅</div>
          </div>

          <ul class="btnRooms">
            <li class="btnRoom" v-for="item in textChatRooms" :key="item.roomId" v-on:click="enterRoom(item.roomId)">
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

            <li class="btnRoom" v-for="item in voiceChatRooms" :key="item.roomId" v-on:click="enterRoom(item.roomId)">
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
                  <img src="/img/channel/userIcon.png">
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
            <input type="text" v-model="roomInfo.name" @keyup.enter="createRoom">
            <div>
              <input type="checkbox" id="roomType" v-model="roomInfo.room_type">
              <label for="roomType">음성채팅방으로 설정</label>
            </div>
          </div>

        </div>
      </div>
    </div>
    <SidebarMyInfo/>
  </div>
</template>

<style scoped>


/**Add**/

img {
  width: 100%;
}

/**  side_contents */
#side_contents {
  display: flex;
  flex-direction: column;
  min-width: 240px;
  background: #2b2d31;
  border-radius: 10px 0 0 0;
  position: relative;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none
}

#side_content_info {
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 50px 10px;
  gap: 1px;
}

#chatRooms {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.chatRoom {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.roomName {
  display: flex;
}

.roomName > div:nth-of-type(1) {
  color: #949BA4;
  font-size: 13px;
  font-weight: bold;
  margin-top: 10px;
  padding: 0 5px;
}

.roomName > div:nth-of-type(1):hover {
  color: #fff;
  cursor: pointer;
  display: flex;
}

.btnRoom {
  display: flex;
  height: 30px;
  gap: 20px;
  border-radius: 5px;
  align-items: center;
  cursor: pointer;
}
li{
  margin: 0;
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

.btnRooms {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.btnRooms > div:nth-of-type(2) {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 5px;
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


input[name=message] {
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