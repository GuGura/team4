<template>
  <div id="container">
    <ServerList/>
    <MainContainer/>
  </div>
</template>

<script setup>
import ServerList from "@/components/serverlist/ServerList.vue";
import MainContainer from "@/components/mainpage/MainContainer.vue";
import loginService from "../../script/LoginService";


loginService.initTokenCheck();
</script>

<style scoped>
#container {
  position: fixed;
  display: flex;
  width: 100%;
  height: 100%;
}
</style>