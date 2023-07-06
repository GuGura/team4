<script setup>

import router from "../../../script/routes/router";
import {computed, reactive} from "vue";
import {useLobbyStore} from "../../../script/stores/lobby";

const lobbyStore = useLobbyStore();

function logout(){
  if(confirm('로그아웃 하시겠습니까?')){
    localStorage.clear();
    router.go(0)
  }
}

const updateUsername = computed(()=>{
  return lobbyStore.user.username
})
const updateEmail = computed(()=>{
  const email = lobbyStore.user.email
  return email.split('@')[0]
})


const info = reactive({
  username: updateUsername,
  testUID: ''
})
</script>

<template>
  <div id="side_content_mystatus">
    <div style="display: flex;gap: 15px;">
      <div id="MyIcon">
          <img src="/img/serverlist/user_icon.png" alt="UserIcon" style="width: 40px;height: 40px;" class="rounded" v-if="lobbyStore.user.user_icon_url==='' ">
          <img :src="lobbyStore.user.user_icon_url"  alt="UserIcon" style="width: 40px;height: 40px;" class="rounded" v-else-if="lobbyStore.user.user_icon_url!=='data:image/png;base64,null'">
          <img src="/img/serverlist/user_icon.png" alt="UserIcon" style="width: 40px;height: 40px;" class="rounded" v-else>
      </div>
      <div id="MyInfo">
        <div id="MyName" >
          {{info.username}}

        </div>
        <div id="MyStatus">
          {{updateEmail}}#1234
        </div>
      </div>
    </div>
    <div style="display: flex;">
      <div id="Mike">
        <img src="/img/channel/mike.png" alt="마이크">
      </div>
      <div id="headset" @click="logout()">
        <img src="/img/channel/headset.png" alt="헤드셋">
      </div>
      <div id="btnHome">
        <img src="/img/channel/btnHome.png" alt="홈버튼">
      </div>
    </div>
  </div>
</template>

<style scoped>
#side_content_mystatus{
  display: flex;
  position: absolute;
  left: 0;
  bottom: 0;
  height: 60px;
  min-width: 240px;
  background: #232428;
  align-items: center;
  gap: 5px;
  padding: 0 0 0 5px;
}
#MyInfo{
  display: flex;
  flex-direction: column;
  height: 40px;
  line-height: 1;
  color:#fff;
  justify-content: space-evenly;
}
#MyIcon{
  display: flex;
}

#MyName{
  font-weight: bold;
  font-size: 18px;
}
#MyStatus{
  font-size: 9px;
}
#Mike,#btnHome,#headset{
  display: flex;
  height: 30px;
  padding: 7px;
}
#Mike:hover,#btnHome:hover,#headset:hover{
  background: #383940;
}
</style>