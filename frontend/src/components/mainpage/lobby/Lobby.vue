<template>
  <div id="main_contents">
    <div id="main_content_header">
      <div id="icon_URL">
          <img src="/img/serverlist/user_icon.png" alt="UserIcon" style="width: 200px;" class="rounded" v-if="lobbyStore.user.user_icon_url==='data:image/png;base64,null' ">
          <img :src="lobbyStore.user.user_icon_url"  alt="UserIcon" style="width: 200px;" class="rounded" v-else>
      </div>
      <div id="userName" class="">
        <div class="order-md-2">
          <h2 class="featurette-heading fw-normal lh-1">{{ lobbyStore.user.username }} </h2>
          <div class="lead"> {{ lobbyStore.user.user_description }}</div>
        </div>
        <div class="row">
          <div class="edit-button col-1" @click="openModal()">
            <span class="material-symbols-outlined">manage_accounts</span>
            <div>프</div>
          </div>
          <UserSettingModal v-if="modalStore.modal.userSetting === true"/>
          <div class="writing-button col-1" @click="openModal2()">
            <span class="material-symbols-outlined">manage_accounts</span>
            <div>글</div>
          </div>
          <WritingContentModal v-if="modalStore.modal.writingContent === true"/>
        </div>
      </div>
    </div>
    <div id="main_content_body">
      <div id="main_content1">
        <Tab/>
      </div>
      <div id="main_content2">
        <TodaySchedule/>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import {useLobbyStore} from "../../../../script/stores/lobby";

import Tab from "@/components/mainpage/lobby/Tab.vue";
import UserSettingModal from "@/components/modal/UserSettingModal.vue";
import {useModalStore} from "../../../../script/stores/modal";
import TodaySchedule from "@/components/mainpage/lobby/TodaySchedule.vue";
import WritingContentModal from "@/components/modal/WritingContentModal.vue";

const lobbyStore = useLobbyStore();
const modalStore = useModalStore();

function openModal() {
  modalStore.open('userSetting')
}

function openModal2() {
  modalStore.open('writingContent')
}

onMounted(() => {

})


</script>


<style scoped>

form[name=list] {
  display: flex;
  flex-direction: column;
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
  margin-top: 9px;
}


#main_contents {
  display: flex;
  flex: 1;
  flex-direction: column;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  overflow-y: auto;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none;

}

#main_contents::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}

#main_content_header {
  display: flex;
  height: 25%;
  align-items: flex-end;
  padding: 10px 30px 15px;
  gap: 35px;
  color: #fff;
}

#icon_URL {
  display: flex;
  width: 10em;
  height: 10em;
}

#userName {
  display: flex;
  color: #fff;
  height: 70%;
  justify-content: flex-start;
  flex-direction: column-reverse;
}

.user-info {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

h2.featurette-heading {
  font-size: 0.8em;
  margin: 0;
}

.lead {
  font-size: 0.4em;
  margin-top: 10px;
}

.edit-button {
  margin-top: 10px;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  font-size: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  height: 30px;
  background: #41434A;
  border-radius: 10px;
  cursor: pointer;
  margin-right: 10px;
}

.writing-button {
  margin-top: 10px;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  font-size: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  height: 30px;
  background: #41434A;
  border-radius: 10px;
  cursor: pointer;
}

.writing-button .material-symbols-outlined {
  font-size: 20px;
  color: #fff;
  margin-right: 5px;
}

.edit-button .material-symbols-outlined {
  font-size: 20px;
  color: #fff;
  margin-right: 5px;
}

#userName > div:nth-of-type(1) {
  font-size: 3em;
}

#userName > div:nth-of-type(2) {

}

.edit-button:hover {
  background: #36373D;
}

.edit-button:active {
  background: #3B3D44;
}


.writing-button:hover {
  background: #36373D;
}

.writing-button:active {
  background: #3B3D44;
}


#main_content_body {
  display: flex;
  flex: 1;
  padding: 5px 0 0 30px;
  gap: 10px;
  padding-top: 30px;
  background-color: #36373d;
}

#main_content1 {
  display: flex;
  background: #36393F;
  width: 73%;
  height: 98%;
}

#main_content2 {
  display: flex;
  width: 25%;
  height: 60%; /* 임시로 */
}
</style>