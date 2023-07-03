<script>
import {computed, defineComponent} from "vue";
import ChatBox from "@/components/mainpage/channel/ChatBox.vue";
import {useLobbyStore} from "../../../../script/stores/lobby";
import {useSocketStore} from '/script/socketOperations';
import router from "../../../../script/routes/router";
import ChannelMemberInfo from "@/components/mainpage/channel/ChannelMemberInfo.vue";


const lobbyStore = useLobbyStore();

const updateUsername = computed(() => {
  return lobbyStore.user.username
})
const socketStore = useSocketStore();
export default defineComponent({
  watch: {
    '$route.params.roomId'(to, from) {
      if (to !== from && to) { // id가 바뀌었고, 새로운 id가 존재할 때만 함수 실행
        const url = `${to}`
        this.enterRoom();
      }
    }
  },
  components: {ChannelMemberInfo, ChatBox},
  data() {
    return {
      roomId: '',
      room: {},
      sender: updateUsername,
      message: '',
      sendDate: '',
      messages: [],
      chatMessages: [],
      inputMessage: '',
      canSend: true,
      ws: null, // WebSocket 객체 추가
      connected: false, // 소켓 연결 상태 추가
    };
  },
  setup() {
    const messageList = socketStore.getter
    return {
      messageList
    };
  },
  methods: {
    router() {
      return router
    },
    created() {
      this.roomId = localStorage.getItem('wschat.roomId');
      this.channelId = localStorage.getItem('wschat.channelId')
      this.sender = updateUsername
    },
    async enterRoom() {
      const roomId = localStorage.getItem('wschat.roomId');
      this.messageList = [];

      await socketStore.findRoomMessage(roomId)
          .then(async (data) => {
            console.log(data);
            this.chatMessages = data; // 받은 데이터를 chatMessages에 저장
            await socketStore.subscribeToRoom(roomId, this.sender)
          })
          .catch((error) => {
            console.error(error);
          });
    },

  sendMessage() {

    const roomId = localStorage.getItem('wschat.roomId');
    const sender = this.sender;
    const message = this.inputMessage;

    socketStore.sendMessage(roomId, sender, message);
    this.inputMessage = ''; // 입력 필드 초기화
  },
},


});
</script>

<template>
  <div id="main_contents">

    <div id="header">
      <div id="chatMain_Header">
        <div>
          <div>
            <img src="/img/channel/chat.png" style="height: 100%;">
          </div>
          <div>회의록</div>
        </div>
        <input name="searchRoom" placeholder="검색하기">
      </div>
    </div>
    <div id="chat_body">
      <div id="chatMain">
        <div id="chatInfo" ref="chatInfoRef">
          <div class="scroll box2">
            <div class="Box" v-for="(message, idx) in chatMessages" :key="`chat-${idx}`">
              <ChatBox :messages="message"/>
            </div>
            <div class="Box" v-for="(messages,idx) in messageList" :key="idx">
              <ChatBox :messages="messages"/>
            </div>
          </div>
        </div>

        <div id="MessageBox">
          <form style="width: 100%;" @submit.prevent>
            <input type="text" name="message" placeholder="메세지 보내기"
                   v-model="inputMessage" @keyup.enter="sendMessage">
          </form>
        </div>
      </div>

      <div id="chatSidebar">
        <div id="offline">
          <div class="roomMemberInfo">
            <div>채널맴버</div>
          </div>
          <ChannelMemberInfo name="박재연"/>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

.box2::-webkit-scrollbar {
  width: 10px;
}

/* 스크롤바 막대 설정*/
.box2::-webkit-scrollbar-thumb {
  background-color: #1A1B1E;
  border: 4px solid #1A1B1E;
  border-radius: 50px;
}

/* 스크롤바 뒷 배경 설정*/
.box2::-webkit-scrollbar-track {
  background-color: rgba(0, 0, 0, 0);
  width: 15px;
}

.scroll {
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  flex: 1;
}

/** scroll*/
#header {
  display: flex;
  position: absolute;
  border-bottom: 1px solid #1F2123;
  width: 100%;
  height: 50px;
  z-index: 11;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none
}

#header > div {
  color: #fff;
}

#chatMain_Header {
  display: flex;
  flex: 1;
  font-size: 14px;
  align-items: center;
  justify-content: space-between;
  background: #313338;
  padding: 0 30px 0 10px;
}

#chatMain_Header > div:nth-of-type(1) {
  display: flex;
  gap: 5px;
  align-items: center;
}

#chatMain_Header > div:nth-of-type(1) > div:nth-of-type(1) {
  height: 15px;
}

input[name=searchRoom] {
  display: flex;
  height: 60%;
  background: #1E1F22;
  color: #949BA4;
  outline: none;
  border: none;
  padding: 0 10px;
}

/** Add*/


#main_contents {
  display: flex;
  flex: 1;
  flex-direction: column;
  position: relative;
}

#chat_body {
  display: flex;
  flex: 1;
  top: 50px;
  position: relative;
}

/** chatMain */
#chatMain {
  display: flex;
  flex-direction: column;
  background: #313338;
  flex: 1;
  position: relative;
  bottom: 50px;
}

#chatInfo {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  gap: 5px;

  padding-bottom: 110px;

  position: absolute;
  left: 0;
  top: 50px;

}

#MessageBox {
  display: flex;
  position: absolute;
  height: 60px;
  bottom: 0;
  width: 100%;
  left: 0;
  background: #313338;
}

input[name=message] {
  display: flex;
  width: 100%;
  height: 70%;
  padding-left: 15px;
  background: #383A40;
  outline: none;
  border: none;
  color: #fff;
  border-radius: 15px;
}

/**  chatSidebar */
#chatSidebar {
  display: flex;
  flex-direction: column;
  background: #2b2d31;
  width: 22%;
  height: 100%; /* 임시로 */
  z-index: 10;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none
}

#offline {
  display: flex;
  flex-direction: column;
}

.roomMemberInfo > div:nth-of-type(1) {
  color: #949BA4;
  font-size: 13px;
  font-weight: bold;
  margin: 5px 15px 0;
}

.Box {
  display: flex;
  padding: 0 10px 15px;
  gap: 5px;
}

.Box:hover {
  background: #2E3035;
}

img {
  height: 35px;
}
</style>