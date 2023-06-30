<script setup>

 import LobbySidebar from "@/components/mainpage/lobby/LobbySidebar.vue";
 import Lobby from "@/components/mainpage/lobby/Lobby.vue";
//import ChannelSidebar from "@/components/mainpage/channel/ChannelSidebar.vue";
//import Channel from "@/components/mainpage/channel/Channel.vue";
</script>

<template>
  <!-- lobby-->
  <div id="contents">
    <LobbySidebar/>
    <Lobby/>
  </div>

  <!-- channel
  <div id="contents">
    <ChannelSidebar/>
    <Channel/>
  </div>
  -->
</template>

<style scoped>
#contents{
  display: flex;
  flex:1;
  background: #1E1F22;
  padding-left: 74px;
}
</style>