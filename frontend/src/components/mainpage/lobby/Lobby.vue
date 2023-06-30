<template>
    <div id="main_contents">
        <div id="main_content_header">
            <div id="icon_URL">
                <img src="/img/sidebar/userIcon.png" alt="헬로" style="width: 200px;">
            </div>
            <div id="userName">
                <div class="col-md-7 order-md-2">
                    <h2 class="featurette-heading fw-normal lh-1">{{ lobbyStore.user.username }} </h2>
                    <div class="lead"> {{ lobbyStore.user.user_description }}</div>
                </div>
                <div class="edit-button" @click="openModal()">
                    <span class="material-symbols-outlined">manage_accounts</span>
                    <div>프로필 수정</div>
                    <UserSettingModal v-if="modalStore.modal.userSetting === true"/>
                </div>
            </div>
        </div>
        <div id="main_content_body">
            <div id="main_content1">
                <Tab/>
            </div>
            <div id="main_content2"></div>
        </div>
    </div>
</template>

<script setup>
import {onMounted} from "vue";
import {useLobbyStore} from "../../../../script/stores/lobby";

import Tab from "@/components/mainpage/lobby/Tab.vue";
import UserSettingModal from "@/components/modal/UserSettingModal.vue";
import {useModalStore} from "../../../../script/stores/modal";

const lobbyStore = useLobbyStore();
const modalStore = useModalStore();

function openModal(){
    modalStore.open('userSetting')
}
onMounted(() => {

})


</script>


<style scoped>

form[name=list]{
    display: flex;
    flex-direction: column;
}



#searchBox > input[name=searchbox]{
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



#main_contents {
    display: flex;
    flex: 1;
    flex-direction: column;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;

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
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    height: 30px;
    background: #41434A;
    border-radius: 10px;
    cursor: pointer;
}

#userName > div:nth-of-type(2):hover {
    background: #36373D;
}

#userName > div:nth-of-type(2):active {
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
    background: #212529;
    width: 25%;
    height: 60%; /* 임시로 */
}
</style>