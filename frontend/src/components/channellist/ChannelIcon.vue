<script setup>
import {defineProps} from 'vue'

import {useServerListStore} from "../../../script/stores/serverlist";
import {useModalStore} from "../../../script/stores/modal";
import {useRouter} from "vue-router";

const serverListStore = useServerListStore();
const modalStore = useModalStore();
const router = useRouter();

const props = defineProps({
  buttonData: Object,
})

function btnClick(event) {
  if (event.target['__vnode'].el['__vueParentComponent'].vnode.key[('channel_title')] !== 'addServer'){
    serverListStore.btnResult.isActive = event.target['__vnode'].el['__vueParentComponent'].vnode.key[('channel_title')]
    localStorage.setItem('activeChannel',serverListStore.btnResult.isActive)
  }
  if (props.buttonData.channel_title === 'addServer') {
    modalStore.modal.addServer = true
  }
  let endPoint = props.buttonData.channel_type;
  if (!(endPoint === 'lobby') && !(endPoint === 'addServer') && !(endPoint === 'public'))
    endPoint = props.buttonData.channel_UID
  if (endPoint !== 'addServer') {
    localStorage.setItem('endPoint', endPoint);
    serverListStore.btnResult.endPoint = endPoint;
    router.push(`/channel/${endPoint}`)
  }
}

function text(){
  serverListStore.btnResult.onlyText = localStorage.getItem('onlyText');
}
text();
</script>

<template>
  <div class="server_Icon" @click="btnClick"
       :class=" {br : (props.buttonData.channel_title === serverListStore.btnResult.isActive)}">
    <div class="colorBlue" style="width: 100%;height: 100%;text-align: center;"
         :class="{colorBlue1 :(serverListStore.btnResult.isActive === props.buttonData.channel_title)}"
         v-if="props.buttonData.channel_icon_url === null">
      <div class="title">{{ props.buttonData.channel_title }}</div>
    </div>

    <img class="img"
         v-else-if="props.buttonData.channel_title === 'lobby' || props.buttonData.channel_title === 'addServer' || props.buttonData.channel_title === 'public'"
         :src="props.buttonData.channel_icon_url" alt=""
         :class="{ colorGreen: props.buttonData.channel_title === 'addServer' || props.buttonData.channel_title === 'public', colorBlue: props.buttonData.channel_title === 'lobby' ,
         colorBlue1 : (serverListStore.btnResult.isActive === props.buttonData.channel_title) && props.buttonData.channel_title==='lobby',
         colorGreen1:(serverListStore.btnResult.isActive === props.buttonData.channel_title) && props.buttonData.channel_title === 'public'}">

    <div class="img channelImage" v-else :style="{backgroundImage: `url(${props.buttonData.channel_icon_url})`}"/>
  </div>
  <div style="border: 1px solid #35363c;margin: 0 10px;" v-if="props.buttonData.channel_title === 'lobby'"></div>
</template>

<style scoped>

.server_Icon {
  width: 50px;
  height: 50px;
  background: #313338;
  border-radius: 50%;
  overflow: hidden;
  position: relative;
}

.server_Icon:hover {
  border-radius: 30%;
}

.colorBlue:hover {
  background: #5865F2;
}

.colorBlue1 {
  background: #5865F2;
}

.colorGreen:hover {
  background: #23A559;
}

.colorGreen1 {
  background: #23A559;
}

.br {
  border-radius: 30%;
}

.channelImage {
  background-position: center;
  background-size: contain;
}

.title {
  color: #fff;
  cursor: pointer;
  position: absolute;
  display: flex;
  width: 100%;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  font-size: 12px;
}

.img {
  width: 100%;
  height: 100%;
  box-sizing: inherit;
  padding: 12px;
  cursor: pointer;
}
</style>