<script setup>
import {defineProps, defineEmits, reactive} from 'vue'
import {useStore} from "vuex";

const props = defineProps({
  isModelActive: {
    type: Boolean,
    default: false
  },
  defaultURL: String,
  serverN: String
})
const store = useStore();
let dataForm = reactive({
  fileURL: '',
  serverName: props.serverN
})
const emit = defineEmits(['update:isModelActive', 'update:fileURL'])

function handleModel() {
  emit('update:isModelActive', false)
  dataForm.fileURL = ''
  dataForm.serverName = store.getters.getUsername + '님의 서버'
}

async function imgPreview(event) {
  const img = event.target.files[0];
  if (img instanceof Blob) {
    const fileReader = new FileReader();
    fileReader.readAsDataURL(img)
    fileReader.onload = function (e) {
      dataForm.fileURL = e.target.result;
    }
  }
}

function test() {
  console.log(dataForm.serverName)
}

test()
</script>

<template>
  {{ store.getters.getUsername }}
  <div id="container" v-if="props.isModelActive">
    <div id="background" @click="handleModel"></div>
    <div id="modal">
      <div id="header">
        <div id="header_sumName">
          <div>서버 만들기</div>
          <img src="/img/serverlist/exit.png" alt="" @click="handleModel">
        </div>
        <div id="description">서버는 나와 친구들이 함께 어울리는 공간입니다. 내 서버를 만들고 대화를 시작해보세요.</div>
      </div>
      <div id="body">
        <div id="img_upload">
          <div id="Icon" class="IconURL" :style="{backgroundImage: `url(${dataForm.fileURL})`}"
               v-if="dataForm.fileURL!=='' "></div>
          <div id="Icon" :style="{backgroundImage: `url(${defaultURL})`}" v-else></div>
          <input class="file-input" type="file" tabindex="0" accept=".jpg,.jpeg,.png,.gif" aria-label="서버 아이콘 업로드하기"
                 @change="imgPreview">
        </div>
        <div id="ChannelNameInputBox">
          <div>서버 이름</div>
          <input name="" v-model=dataForm.serverName >
          <div id="box3">
            <div>서버를 만들어서 잘 운용해보세요. 행운을 빕니다.</div>
            <div id="btnCreate">
              <button>생성하기</button>
            </div>
          </div>
        </div>
      </div>
      <div id="footer">
        <div id="footer_sumName">이미 초대장을 받으셨나요?</div>
        <div id="btnAttend">
          <button>서버 참가하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#Icon {
  width: 80px;
  height: 80px;
}

.IconURL {
  border-radius: 50%;
  background-position: center;
  background-size: contain;
}

#container {
  display: flex;
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  justify-content: center;
  align-items: center;
}

#background {
  display: flex;
  position: fixed;
  width: 100%;
  height: 100%;
  background: black;
  filter: opacity(0.5);
  z-index: 1;
}

#modal {
  display: flex;
  width: 430px;
  padding-bottom: 100px;
  background: #fff;
  z-index: 2;
  border-radius: 10px;
  flex-direction: column;
  position: relative;
  overflow: hidden;
  padding-top: 10px;
  gap: 20px;
}

#header {
  display: flex;
  flex-direction: column;
  gap: 5px;
  width: 100%;
}

#header_sumName {
  display: flex;
  width: 100%;
  position: relative;
  justify-content: center;
  padding: 10px;
}

#header_sumName > div:nth-of-type(1) {
  font-size: 22px;
  font-weight: 550;
  display: flex;
  color: #060607;
}

#header_sumName > img:nth-of-type(1) {
  position: absolute;
  height: 15px;
  right: 20px;
  cursor: pointer;
}

#description {
  font-size: 15px;
  padding: 0 10px;
  text-align: center;
  color: #4E5058;
}

/** */
#body {
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 2;
  gap: 20px;
}

#img_upload {
  display: flex;
  position: relative;
  overflow: hidden;
}

.file-input {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
  font-size: 0;
}

#ChannelNameInputBox {
  display: flex;
  flex-direction: column;
  gap: 5px;
  width: 90%;
}

#ChannelNameInputBox > div:nth-of-type(1) {
  font-size: 12px;
  font-weight: 600;
  color: #4E5058;
}

#ChannelNameInputBox > input:nth-of-type(1) {
  height: 40px;
  background: #E3E5E8;
  outline: none;
  border: none;
  padding: 0 10px;
  border-radius: 3px;
  font-size: 15px;
  color: #313338;
}

#box3 {
  display: flex;
  justify-content: space-between;
}

#box3 > div:nth-of-type(1) {
  color: #5C5E66;
  font-size: 12px;
}

#btnCreate > button {
  height: 25px;
  font-size: 12px;
  width: 60px;
  border: none;
  background-color: #E3E5E8;
  color: #5C5E66;
  cursor: pointer;
  border-radius: 2px;
}

#btnCreate > button:hover {
  background-color: #CECECE;
}

#footer {
  display: flex;
  flex-direction: column;
  bottom: 0;
  background: #F2F3F5;
  position: absolute;
  width: 100%;
  align-items: center;
  height: 90px;
}

#footer_sumName {
  font-size: 20px;
  font-weight: 550;
  display: flex;
  width: 100%;
  justify-content: center;
  padding: 10px 0 5px 0;
}

#btnAttend {
  width: 100%;
  padding: 0 10px;
  height: 34px;
}

#btnAttend > button {
  width: 100%;
  height: 100%;
  background: #6D6F78;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

#btnAttend > button:hover {
  background: #4E5058;
}

#btnAttend > button:active {
  background: #41434A;
}
</style>