<script setup>
import {defineProps, reactive} from 'vue'
import api from "../../../../script/token/axios";
import {useFriendStore} from "../../../../script/stores/friend";

const props = defineProps({
  friendInfo: Object,
  request: Boolean,
})
let isRequest = reactive([props.request])

function friendSend() {
console.log(props.friendInfo.id)
  api.post(`/friend/send/${props.friendInfo.id}`)
      .then(({data}) => {
        console.log(data)
        isRequest[0] = true
      })
      .catch(err=>{
        console.log(err)
        alert(err.response.data)
      })
}
</script>

<template>
  <div class="btnList" >
    <div style="width: 35px;">
        <img class="rounded" v-if="props.friendInfo.user_ICON_URL === 'data:image/png;base64,null'" src="/img/serverlist/bright_icon.png">
        <img class="rounded" :src="props.friendInfo.user_ICON_URL">
    </div>
    <div class="MyMember_Info">
      <div class="MyMember_Name">
        {{ props.friendInfo.username }}
      </div>
      <button style="outline: none;border: none;cursor: pointer;" @click="friendSend()" v-if="isRequest[0]===false">수락하기</button>
      <button style="outline: none;border: none;cursor: pointer;" v-else-if="isRequest[0]===true">요청완료</button>
    </div>
  </div>
</template>

<style scoped>
.btnList {
  display: flex;
  height: 45px;
  gap: 20px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;

}

.btnList:hover {
  background: #36373D;
}

.btnList:active {
  background: #3B3D44;
}

.btnList > div:nth-of-type(1) {
  display: flex;
  color: #fff;
  width: 30px;
}

.btnList > div:nth-of-type(2) {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

.MyMember_exit {
  display: flex;
  height: 8px;
  width: 8px;
}


img {
  width: 100%;
}
</style>