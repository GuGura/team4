<script setup>
import {useModalStore} from "../../../script/stores/modal";

const modalStore = useModalStore();

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
    <form>
      <div id="modalBody">
        <div style="padding: 5px">채널 유형</div>
        <div class="channelTypeBox">
          <div style="height: 20px;display: flex">
            <img src="/img/channel/chat.png">
          </div>
          <div style="display: flex;flex-direction: column;gap: 1px">
            <div style="color: #DBDEE1;font-size: 18px;font-weight:550;">Text</div>
            <div style="color:#B5BAC1">메세지,의견,농담을 전송하세요</div>
          </div>
          <div
              style="height: 50px;display: flex;flex: 1; justify-content: flex-end;align-items: center;">
            <input type="radio" value="Text" name="type" style="width: 20px;height: 20px;" checked>
          </div>
        </div>

        <div class="channelTypeBox">
          <div style="height: 20px;display: flex">
            <img src="/img/channel/chat.png">
          </div>
          <div style="display: flex;flex-direction: column;gap: 1px">
            <div style="color: #DBDEE1;font-size: 18px;font-weight:550;">Text</div>
            <div style="color:#B5BAC1">메세지,의견,농담을 전송하세요</div>
          </div>
          <div
              style="height: 50px;display: flex;flex: 1; justify-content: flex-end;align-items: center;">
            <input type="radio" value="audio" name="type" style="width: 20px;height: 20px;">
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped>
.channelTypeBox {
  height: 70px;
  display: flex;
  background-color: #2b2d31;
  border-radius: 5px;
  align-items: center;
  padding: 0 15px;
  gap: 15px;
}

.channelTypeBox:hover {
  background-color: #3A3C41;
}

.channelTypeBox:active {
  background-color: #3F4147;
}

#modalBody {
  display: flex;
  flex-direction: column;
  gap: 5px;
  cursor: pointer;
}

#modalBody > div:nth-of-type(1) {
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