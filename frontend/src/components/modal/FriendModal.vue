<script setup>
import {useModalStore} from "../../../script/stores/modal";
import {reactive, ref} from "vue";
import OtherUsers from "@/components/mainpage/lobby/OtherUsers.vue";
import api from "../../../script/token/axios";
import RequestUsers from "@/components/mainpage/lobby/RequestUsers.vue";
import router from "../../../script/routes/router";


const modalStore = useModalStore();

let buttonType = ref(1)
let OtherUser = reactive([]);
let RequestUser = reactive([]);
let username = reactive(null);
let afterSearch = reactive(null);

function closeM() {
  modalStore.openClose('Friend')
  router.go(0)
}

function initRequestUser() {
  api.get(`/friend/requestUser`)
      .then(({data}) => {
        console.log("initRequestUser")
        data.forEach(member => {
          RequestUser.push(member)
        })
      })
      .catch(err => {
        console.log(err)
      })
}

function findOtherUser() {
  if (afterSearch !== username) {
    OtherUser.splice(0, OtherUser.length)
    afterSearch = username;
    if (username !== null && username !== '') {
      api.get(`/friend/search/${username}`)
          .then(({data}) => {
            data.forEach(user => {
              if (user.user_ICON_URL !== null && user.user_ICON_URL !== '') {
                user.user_ICON_URL = "data:image/png;base64," + user.user_ICON_URL
              } else {
                user.user_ICON_URL = "data:image/png;base64,null"
              }
              OtherUser.push(user)
            })
          })
    }
  }

}

initRequestUser();
</script>

<template>
  <div id="container1">
    <div id="background" @click="closeM()"></div>
    <div id="modal">
      <div id="modalHeader">
        <div style="color: #fff;font-size: 21px">친구창</div>
        <div id="exit" @click="closeM()">
          <img src="/img/serverlist/exit.png">
        </div>
      </div>
      <div id="buttons">
        <div :class="(buttonType===1)? 'active':'normal' " @click="buttonType=1">친구찾기</div>
        <div :class="(buttonType===2)? 'active':'normal' " @click="buttonType=2">친구요청</div>
      </div>
      <div id="findUser" v-if="buttonType===1">
        <div id="searchBox">
          <input name="searchbox" placeholder="친구 찾기" v-model="username">
          <div id="check" @click="findOtherUser">확인</div>
        </div>
        <OtherUsers v-for="user in OtherUser" :key="user"
                    :friendInfo="user"
                    :request="false"/>
      </div>
      <div id="" v-else-if="buttonType===2">
        <RequestUsers v-for="user in RequestUser" :key="user"
                      :friendInfo="user"
                      :request="false"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
#check {
  display: flex;
  width: 50px;
  height: 30px;
  background-color: #B5BAC1;
  color: #fff;
  align-items: center;
  margin-top: 10px;
  border-radius: 5px;
  justify-content: center;
  cursor: pointer;
}

#check:hover {
  background-color: #949BA4;
}

#buttons {
  display: flex;
  gap: 10px;
}

#buttons div {
  padding: 5px;
  border-radius: 5px;
  display: flex;
  justify-items: center;
  cursor: pointer;
}

#searchBox {
  display: flex;
  margin-top: -10px;
  align-items: center;
}

#searchBox > input[name=searchbox] {
  display: flex;
  background-color: #1E1F22;
  outline: none;
  border-inline: none;
  border-bottom: none;
  color: #fff;
  padding: 0 5px;
  height: 35px;
  width: 100%;
  margin-top: 10px;
}

.normal {
  background-color: #2b2d31;
  color: #B5BAC1;
}

.active {
  background-color: #B5BAC1;
  color: #fff;
}

#modalHeader {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#exit {
  height: 16px;
  cursor: pointer;
  filter: opacity(0.5);
  display: flex;
}

#exit:hover {
  filter: opacity(1);
}

#container1 {
  display: flex;
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  justify-content: center;
  align-items: center;
  z-index: 90;
}

#background {
  display: flex;
  position: fixed;
  width: 100%;
  height: 100%;
  background: black;
  filter: opacity(0.5);
  z-index: 1;
}

#modal {
  width: 430px;
  background-color: #313338;
  z-index: 13;
  position: fixed;
  border-radius: 5px;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  padding: 20px 15px 90px;
  gap: 10px;
}


</style>