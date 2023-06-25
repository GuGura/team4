<script setup>
import ServerList from "@/components/channellist/ChannelList.vue";

import Channel from "@/components/mainpage/channel/Channel.vue";
import LobbySidebar from "@/components/mainpage/lobby/LobbySidebar.vue";
import Lobby from "@/components/mainpage/lobby/Lobby.vue";
import ChannelSidebar from "@/components/mainpage/channel/ChannelSidebar.vue";
import {useServerListStore} from "../../script/stores/serverlist";
import {onMounted} from "vue";

const store = useServerListStore();
onMounted(()=>{
  store.btnResult.endPoint = localStorage.getItem('endPoint');
  store.btnResult.isActive = localStorage.getItem('activeChannel');
})
</script>
<template>
  <div id="container">
    <ServerList/>
    <div id="contents" v-if="store.btnResult.endPoint === 'lobby'">
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
  background: #1E1F22;
}
</style>