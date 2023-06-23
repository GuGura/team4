<script setup>
import {defineProps} from 'vue'

import {useServerListStore} from "@/stores/serverlist";
import {useModalStore} from "@/stores/modal";

const serverListStore = useServerListStore();
const modalStore = useModalStore();

const props = defineProps({
  buttonData: Object,
})

function btnClick() {
  console.log(props.buttonData.channel_title)
  if (serverListStore.btnResult.isLobby === true && props.buttonData.channel_title === 'addServer') {
    modalStore.modal.addServer = true
  } else if (serverListStore.btnResult.isLobby === false && props.buttonData.channel_title === 'addServer') {
    modalStore.modal.addServer = true
  } else {
    const channelTitle = props.buttonData.channel_title;
    serverListStore.btnResult.isLobby = channelTitle === 'lobby';
  }
}
</script>

<template>
  <div class="server_Icon" @click="btnClick">
    <div class="colorBlue" style="width: 100%;height: 100%;text-align: center;"
         v-if="props.buttonData.channel_icon_url === null">
      <div class="title">{{ props.buttonData.channel_title }}</div>
    </div>

    <img class="img"
         v-else-if="props.buttonData.channel_title === 'lobby' || props.buttonData.channel_title === 'addServer' || props.buttonData.channel_title === 'public'"
         :src="props.buttonData.channel_icon_url" alt=""
         :class="{ colorGreen: props.buttonData.channel_title === 'addServer' || props.buttonData.channel_title === 'public', colorBlue: props.buttonData.channel_title === 'lobby' }">

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

.colorGreen:hover {
  background: #23A559;
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