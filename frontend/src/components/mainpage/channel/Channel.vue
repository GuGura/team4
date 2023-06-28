<script>
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

import {computed, defineComponent, reactive} from "vue";
import ChatBox from "@/components/mainpage/channel/ChatBox.vue";
import {useLobbyStore} from "../../../../script/stores/lobby";
import authHeader from "../../../../script/token/authHeader";
import api from "/script/token/axios.js";

const lobbyStore = useLobbyStore();

const updateUsername = computed(()=>{
  return lobbyStore.user.username
})

export default defineComponent({
  watch: {
    '$route'() {
      this.created();
    }
  },
  components: {ChatBox},
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
    const messageList = reactive({
      messages: [
        {
          sender: "보내는 사람의 이름",
          userIcon: '',
          sendDate: "보낸 시각",
          message: "기본 페이지 입니다"
        },
      ]
    });
    return {
      messageList
    };
  },
  methods: {
    created() {
      this.roomId = localStorage.getItem('wschat.roomId');
      this.channelId = localStorage.getItem('wschat.channelId')
      this.sender = updateUsername
      console.log("Channel.vue sender : " + this.sender);
      console.log("Channel.vue roomId : " + this.roomId);
      console.log("Channel.vue channelId : " + this.channelId);

      this.disconnect(); // 이전 웹소켓 연결 끊기
      this.messageList.messages = []; // messageList 초기화
      this.connect(); // 새로운 웹소켓 연결 수행
    },
    connect() {
      const serverURL = process.env.VUE_APP_BASEURL_V1;
      let socket = new SockJS(serverURL);
      console.log("1")
      this.ws = Stomp.over(socket);
      console.log('serverURL : ' + serverURL);

      this.findRoomMessage();
      console.log("2")

      const headers = {
        accessJwt: authHeader()
      };

      this.ws.connect(
          headers,
          frame => {
            console.log("3")
            this.connected = true;
            console.log('소켓 연결 성공', frame);
            this.ws.subscribe("/sub/chat/room/" + this.roomId, message => {
              console.log('구독으로 받은 메시지 입니다.', message.body);
              this.recvMessage(JSON.parse(message.body));
            });
            if (this.ws && this.ws.connected) {
              console.log('Start ws.connect Channel.vue ')
              const msg = {
                type: 'ENTER',
                roomId: this.roomId,
                sender: this.sender,
              };

              this.ws.send(
                  "/pub/chat/message",
                  JSON.stringify(msg),
                  {}
              );
            }
          },
          error => {
            console.log('소켓 연결 실패 ', error);
            this.connected = false;
          }
      );
    },
    disconnect() {
      if (this.ws && this.ws.connected) {
        this.ws.disconnect();
        this.connected = false;
      }
    },
    sendMessage() {
      if (!this.canSend) {
        return;
      }

      if (this.ws && this.ws.connected) {
        const msg = {
          type: 'TALK',
          roomId: this.roomId,
          sender: this.sender,
          message: this.inputMessage,
          sendDate: new Date(),
        };
        this.ws.send(
            "/pub/chat/message",
            JSON.stringify(msg),
            {}
        );
      }
      this.inputMessage = '';
      this.canSend = false;
      setTimeout(() => {
        this.canSend = true;
      }, 1000);
    },
    recvMessage(recv) {
      if (recv.type !== 'ENTER') { // 'ENTER' 타입인 경우에는 출력하지 않음
        this.messageList.messages.push({
          type: recv.type,
          sender: recv.sender,
          message: recv.message,
          sendDate: recv.sendDate,
        });
        this.$nextTick(() => {
          const chatInfoElement = this.$refs.chatInfoRef;
          if (chatInfoElement.scrollHeight - chatInfoElement.clientHeight <= chatInfoElement.scrollTop + 100) {
            chatInfoElement.scrollTop = chatInfoElement.scrollHeight;
          }
        });
      }
    },
    findRoomMessage() {
      console.log('5')
      api.get(process.env.VUE_APP_BASEURL_V1+`/chat/room/enter/${this.roomId}`).then(response => {
        console.log(response.data);
        console.log(6)
        this.chatMessages = response.data;
        console.log('7')
      });
    },
  },
  beforeUnmount() {
    this.disconnect(); // 컴포넌트가 해제되기 전에 웹소켓 연결 끊기
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
          <div class="Box" v-for="(messages,idx) in messageList.messages" :key="idx">
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
        <div id="online">
          <div class="roomMemberInfo">
            <div>온라인</div>
          </div>
          <div class="chatSidebarInfo">
            <div>
              <img src="/img/channel/userIcon.png">
            </div>
            <div class="MyMember_Info">
              <div class="MyMember_Name1">
                박재연
              </div>
            </div>
          </div>
          <div class="chatSidebarInfo">
            <div>
              <img src="/img/channel/userIcon.png">
            </div>
            <div class="MyMember_Info">
              <div class="MyMember_Name1">
                박재연
              </div>
            </div>
          </div>
        </div>
        <div id="offline">
          <div class="roomMemberInfo">
            <div>오프라인</div>
          </div>
          <div class="chatSidebarInfo">
            <div>
              <img src="/img/channel/userIcon.png">
            </div>
            <div class="MyMember_Info">
              <div class="MyMember_Name1">
                박재연
              </div>
            </div>
          </div>
          <div class="chatSidebarInfo">
            <div>
              <img src="/img/channel/userIcon.png">
            </div>
            <div class="MyMember_Info">
              <div class="MyMember_Name1">
                박재연
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

.box2::-webkit-scrollbar{
  width: 10px;
}

/* 스크롤바 막대 설정*/
.box2::-webkit-scrollbar-thumb{
  background-color: #1A1B1E;
  border: 4px solid #1A1B1E;
  border-radius: 50px;
}

/* 스크롤바 뒷 배경 설정*/
.box2::-webkit-scrollbar-track{
  background-color: rgba(0,0,0,0);
  width: 15px;
}

.scroll{
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
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none
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
input[name=searchRoom]{
  display: flex;
  height: 60%;
  background: #1E1F22;
  color: #949BA4;
  outline: none;
  border: none;
  padding: 0 10px;
}
/** Add*/
.MyMember_Info {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

.MyMember_exit {
  display: flex;
  height: 8px;
  width: 8px;
}

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
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none
}

#online {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

#offline {
  display: flex;
  flex-direction: column;
}

.chatSidebarInfo {
  display: flex;
  height: 45px;
  gap: 10px;
  border-radius: 5px;
  margin: 0px 15px;
  align-items: center;
  cursor: pointer;
}

.chatSidebarInfo:hover {
  background: #36373D;
}

.chatSidebarInfo:active {
  background: #3B3D44;
}

.chatSidebarInfo > div:nth-of-type(1) {
  display: flex;
  color: #fff;
  width: 40px;
}

.chatSidebarInfo > div:nth-of-type(1) > img:nth-of-type(1) {
  padding: 2px;
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