<template>
  <div id="container">
    <ServerList/>
    <MainContainer/>
  </div>
</template>

<script setup>
import ServerList from "@/components/channellist/ChannelList.vue";
import MainContainer from "@/components/mainpage/MainContainer.vue";


</script>

<style scoped>
#container {
  position: fixed;
  display: flex;
  width: 100%;
  height: 100%;
}
</style>