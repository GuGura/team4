<script setup>
import {useModalStore} from "../../../script/stores/modal";
import {reactive} from "vue";

const modalStore = useModalStore();
const props = reactive({
  type: 'Text',

})

function closeModal() {
  modalStore.terminate('CreateRoom')
}


</script>

<template>
  <div id="background" @click="closeModal"></div>

  <div id="modal">
    <div id="modalHeader">
      <div style="color: #fff;font-size: 21px">채널 만들기</div>
      <div id="exit" @click="closeModal">
        <img src="/img/serverlist/exit.png">
      </div>
    </div>

    <div id="type">
      <div style="padding: 5px">채널 유형</div>
      <label class="channelTypeBox">
        <div style="height: 20px;display: flex">
          <img src="/img/channel/chat.png">
        </div>
        <div style="display: flex;flex-direction: column;gap: 1px">
          <div style="color: #DBDEE1;font-size: 18px;font-weight:550;">Text</div>
          <div style="color:#B5BAC1">메세지,의견,농담을 전송하세요</div>
        </div>
        <div
            style="height: 50px;display: flex;flex: 1; justify-content: flex-end;align-items: center;">
          <input type="radio" value="Text" name="type" v-model="props.type" style="width: 20px;height: 20px;" checked>
        </div>
      </label>

      <label class="channelTypeBox">
        <div style="height: 20px;display: flex">
          <img src="/img/channel/speak.png">
        </div>
        <div style="display: flex;flex-direction: column;gap: 1px">
          <div style="color:#DBDEE1;font-size: 18px;font-weight:550;">Voice</div>
          <div style="color:#B5BAC1">음성,영상,화면 공유로 함께 어울리세요</div>
        </div>
        <div
            style="height: 50px;display: flex;flex: 1; justify-content: flex-end;align-items: center;">
          <input type="radio" value="Voice" name="type" v-model="props.type" style="width: 20px;height: 20px;">
        </div>
      </label>
    </div>

    <div id="channel_title">
      <div>채널이름</div>
      <div style="display: flex">
        <div style="display: flex;height: 15px">
          <img src="/img/channel/chat.png">
        </div>
        <input name="searchRoom" placeholder="새로운 채널">
      </div>
    </div>

  </div>
</template>

<style scoped>
input[name=searchRoom] {
  display: flex;
  height: 60%;
  background: #1E1F22;
  color: #949BA4;
  outline: none;
  border: none;
  padding: 0 10px;
}

.channelTypeBox {
  height: 70px;
  display: flex;
  background-color: #2b2d31;
  border-radius: 5px;
  align-items: center;
  padding: 0 15px;
  gap: 15px;
  cursor: pointer;
}

.channelTypeBox:hover {
  background-color: #3A3C41;
}

.channelTypeBox:active {
  background-color: #3F4147;
}

#channel_title > div:nth-of-type(1) {
  font-size: 12px;
  font-weight: 600;
  color: #B5BAC1;
}

#channel_title {

}

#type {
  display: flex;
  flex-direction: column;
  gap: 5px;
  cursor: pointer;
}

#type > div:nth-of-type(1) {
  font-size: 12px;
  font-weight: 600;
  color: #B5BAC1;
}

#modalHeader {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#exit {
  height: 16px;
  cursor: pointer;
  filter: opacity(0.5);
  display: flex;
}

#exit:hover {
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
  gap: 15px;
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

</style>