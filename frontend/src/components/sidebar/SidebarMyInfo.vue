<script setup>
import router from "../../../script/routes/router";

function logout(){
    if(confirm('로그아웃 하시겠습니까?')){
        localStorage.clear();
        router.go(0)
    }
}
</script>

<template>
  <div id="side_content_mystatus">
    <div style="display: flex;gap: 15px;">
      <div id="MyIcon">
        <img src="/img/channel/userIcon.png" style="width: 40px;height: 40px;" alt="헬로">
      </div>
      <div id="MyInfo">
        <div id="MyName" >
          meatTeam<!-- 글자제한필요-->
        </div>
        <div id="MyStatus">
          meatTeam#1234
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