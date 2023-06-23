<script setup>
import {defineProps} from 'vue'

import {useServerListStore} from "@/stores/serverlist";

const store = useServerListStore();

const props = defineProps({
  buttonData: Object,
  isLobby: Boolean
})

function btnClick(){
  store.btnResult.channel_title = props.buttonData.channel_title
  store.btnResult.isLobby =  store.btnResult.channel_title === 'lobby'
  console.log(store.btnResult.channel_title)
  console.log(store.btnResult.isLobby)
}
</script>

<template>
  <div class="server_Icon" @click="btnClick">
    <div class="colorBlue" style="width: 100%;height: 100%;text-align: center;"
         v-if="props.buttonData.channel_icon_url === null">
      <div class="title">{{ props.buttonData.channel_title }}</div>
    </div>

    <img class="img" v-else-if="props.buttonData.channel_title === 'lobby' || props.buttonData.channel_title === 'addServer' || props.buttonData.channel_title === 'public'"
         :src="props.buttonData.channel_icon_url" alt=""
         :class="{ colorGreen: props.buttonData.channel_title === 'addServer' || props.buttonData.channel_title === 'public', colorBlue: props.buttonData.channel_title === 'lobby' }">

    <div class="img channelImage" v-else :style="{backgroundImage: `url(${props.buttonData.channel_icon_url})`}"/>
  </div>
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
.channelImage{
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