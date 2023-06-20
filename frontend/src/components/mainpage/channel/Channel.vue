<script>

import {defineComponent, reactive} from "vue";
import ChatBox from "@/components/mainpage/channel/ChatBox.vue";

export default defineComponent({
  components: {ChatBox},
  setup() {
    const messageList = reactive({
      messages: [
        {
          user: "원식킴",
          userIcon: '',
          sendDate: "2023.05.21 오후 10:41",
          message: "안녕하세요"
        },
        {
          user: "재연퐉",
          userIcon: '',
          sendDate: "2023.05.25 오후 10:41",
          message: "힘들다"
        },
        {
          user: "민화",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다2"
        },
        {
          user: "도연",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다3"
        },
        {
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다4"
        },{
          user: "선아",
          userIcon: '',
          sendDate: "2023.05.27 오후 10:41",
          message: "힘들다55"
        },

      ]
    })

    return {
      messageList
    }
  }
})
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
        <div id="chatInfo">
          <div class="scroll box2">
            <div class="Box" v-for="(message,idx) in messageList.messages" :key="idx">
              <ChatBox :message="message"/>
            </div>
          </div>
        </div>

        <div id="MessageBox">
          <form style="width: 100%;padding-left: 30px">
            <input name="message" placeholder="메세지 보내기">
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