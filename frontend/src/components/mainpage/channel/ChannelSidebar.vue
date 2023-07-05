<script setup>
import {computed, onMounted, reactive, watch} from 'vue';
import SidebarMyInfo from "@/components/sidebar/SidebarMyInfo.vue";
import {useChannelStore} from "../../../../script/stores/channel";
import {useLobbyStore} from "../../../../script/stores/lobby";
import {useServerListStore} from "../../../../script/stores/serverlist";
import ChannelSidebarHead from "@/components/mainpage/channel/ChannelSidebarHead.vue";
import {createRoom, enterRoom, findAllRoom} from '/script/chatOperations';

const channelStore = useChannelStore();
const lobbyStore = useLobbyStore();
const serverListStore = useServerListStore();

const roomInfo = reactive({
  name: '',
  room_type: false,
}); // ChatRoom Name
const textChatRooms = reactive([]); // Text Chat Room List
const voiceChatRooms = reactive([]); // Voice Chat Room List

const updateUsername = computed(() => {
  return lobbyStore.user.username
})
const updateChannelId = computed(() => {
  return serverListStore.getEndPoint;
});

onMounted(async () => {
  await findAllRoom(updateChannelId.value, textChatRooms, voiceChatRooms);
  if (textChatRooms.length > 0) {
    enterRoom(textChatRooms[0].roomId); // Here only roomId is needed
  }
});

watch(()=>updateChannelId.value,
    async () => {
      console.log("ChannelId.value -------- : " + updateChannelId.value);
      if (updateChannelId.value !== "lobby") {
        await findAllRoom(updateChannelId.value, textChatRooms, voiceChatRooms);
        if (textChatRooms.length === 0 && voiceChatRooms.length === 0) {
          roomInfo.name = "Text Chatting Room";
          roomInfo.room_type = false;
          await createRoom(updateChannelId.value, roomInfo, textChatRooms, voiceChatRooms);
          roomInfo.name = "Voice Chatting Room";
          roomInfo.room_type = true;
          await createRoom(updateChannelId.value, roomInfo, textChatRooms, voiceChatRooms);
          enterRoom(updateUsername.value, updateChannelId.value, textChatRooms[0].roomId);
        }
        if (textChatRooms.length > 0) {
          enterRoom(textChatRooms[0].roomId); // Here only roomId is needed
        }
      }
    },
);

// To use the createRoom function, the channel_id and roomInfo object are needed
const createRoomInChannel = () => {
  createRoom(updateChannelId.value, roomInfo, textChatRooms, voiceChatRooms);
};


</script>

<template>
  <div id="side_contents">
    <ChannelSidebarHead
        :channel_title="channelStore.channelInfo.channel_title"
        :channel_invite_code="channelStore.channelInfo.channel_invite_code"/>

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
            <input type="text" v-model="roomInfo.name" @keyup.enter="createRoomInChannel">
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