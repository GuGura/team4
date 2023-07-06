<script setup>
import ServerList from "@/components/channellist/ChannelList.vue";

import Channel from "@/components/mainpage/channel/Channel.vue";
import LobbySidebar from "@/components/mainpage/lobby/LobbySidebar.vue";
import Lobby from "@/components/mainpage/lobby/Lobby.vue";
import ChannelSidebar from "@/components/mainpage/channel/ChannelSidebar.vue";
import {useServerListStore} from "../../script/stores/serverlist";
import {onMounted, provide, reactive, watch} from "vue";
import {useLobbyStore} from "../../script/stores/lobby";
import {useRouter} from "vue-router";
import {useChannelStore} from "../../script/stores/channel";
import {useSocketStore} from '/script/socketOperations';


const serverListStore = useServerListStore();
const lobbyStore = useLobbyStore();
const channelStore = useChannelStore();
const socketStore = useSocketStore();

const route = useRouter()

watch(route.currentRoute, (to,form) => {
  if (to.path !== form.path){
   const channel_type = serverListStore.getPathEndPoint;
   console.log(typeof channel_type)
   switch (channel_type){
     case 'lobby':
       break;
     case 'public':
       break;
     default:
       channelStore.init()
       break;
   }
  }
})
onMounted(async () => {
  await lobbyStore.updateMyInfo();
  await socketStore.connectSocket();
  provide('socket', socketStore.ws);
  provide('wsConnected', socketStore.wsConnected);  // 상태를 provide로 제공
});


</script>

<template>
  <div id="container">
    <ServerList/>
    <div id="contents" v-if="route.currentRoute.value.path === '/channel/lobby'">
      <LobbySidebar/>
      <Lobby/>
    </div>
    <div id="contents" v-else>
      <ChannelSidebar/>
      <Channel/>
    </div>
  </div>
</template>

<style scoped>
#container {
  position: fixed;
  display: flex;
  width: 100%;
  height: 100%;
}

#contents {
    display: flex;
    flex: 1;
    background: #1F2123;
}
</style>