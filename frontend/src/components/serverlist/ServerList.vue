<script setup>
import ServerIcon from "@/components/serverlist/ServerIcon.vue";
import AddServerModel from "@/components/serverlist/AddServerModel.vue";
import {reactive} from "vue";


function choiceServer(index) {
  if (index === 'addServer') {
    this.status.addServer = true
  }
}

function addServer(event) {
  this.status.addServer = event;
}

const status = reactive({
  lobby: true,
  addServer: false,
  publicServer: false,
  myServerList: []
})

</script>

<template>
  <div id="serverList">
    <form name="serverList">
      <div id="lobby" class="server_Icon" @click="choiceServer('lobby')">
        <img src="/img/serverlist/discord_Icon.png" alt="서버">
      </div>
      <div style="border: 1px solid #35363c;margin: 0 10px;"></div>
      <ServerIcon v-for="i in 5" :key="i"></ServerIcon>
      <div id="addServer" class="server_Icon" @click="choiceServer('addServer')"
           :style="{overflow:  !status.addServer?'':'inherit'}"
      >
        <img src="/img/serverlist/add_server1.png" alt="서버" style="padding: 17px;"
             :style="{ background:  status.addServer? '#23A559':''
        ,borderRadius:status.addServer?'30%':''}">
      </div>

      <div id="publicServer" class="server_Icon" @click="choiceServer('publicServer')"
           :style="{overflow:  !status.publicServer?'':'inherit'}">
        <img src="/img/serverlist/public_icon.png" alt="서버" style="padding: 15px;"
             :style="{ background:  status.publicServer? '#23A559':''
        ,borderRadius:status.publicServer?'30%':''}">
      </div>
    </form>
    <AddServerModel :is-model-active="status.addServer" @update:isModelActive="addServer(event)"/>

  </div>
</template>

<style scoped>

/** #serverlist */
/** fixed */
#serverList {
  display: flex;
  flex-direction: column;
  position: fixed;
  min-width: 74px;
  height: 100%;
  padding: 10px 12px;
  background: #1E1F22;
  z-index: 12;
}

img {
  width: 100%;
  height: 100%;
}

form[name=serverList] {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

#serverList > form[name=serverList] > div > img {
  width: 100%;
  height: 100%;
  box-sizing: inherit;
  padding: 12px;
  cursor: pointer;
}

#serverList > form[name=serverList] > div:not(:nth-last-of-type(1),:nth-last-of-type(2)):hover > img {
  background: #5865F2;
}

#serverList > form[name=serverList] > div:nth-last-of-type(1):hover, #serverList > form[name=serverList] > div:nth-last-of-type(2):hover > img {
  background: #23A559;
}

.server_Icon {
  width: 50px;
  height: 50px;
  background: #313338;
  border-radius: 50%;
  overflow: hidden;
}

.server_Icon:hover {
  border-radius: 30%;
}
</style>