<script setup>

import {useLobbyStore} from "../../../../script/stores/lobby";
import {onMounted} from "vue";
import Tab from "@/components/mainpage/lobby/Tab.vue";

const lobbyStore = useLobbyStore();

onMounted(()=>{

})


</script>


<template>
    <div id="contents">
        <div id="main_contents">
            <div id="main_content_header">
                <div id="icon_URL">
                    <img :src="lobbyStore.user.user_icon_url" alt="헬로" style="width: 200px; position:relative; z-index: 4;">
                </div>
                <div id="userName" style="z-index: 3;">
                    <div class="col-md-7 order-md-2">
                        <h2 class="featurette-heading fw-normal lh-1">{{lobbyStore.user.username}} </h2>
                        <div class="lead"> {{lobbyStore.user.user_description}}</div>
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
    </div>
</template>
<style scoped>


#description{
    background: #41434A;
    width: 71.5%;
    height: 10%;
    margin-left: 30px;
    margin-bottom: 10px;
    margin-top: 20px;
    display: flex;

}
#user_description{
    display: flex;
    color: #FFFFFF;
    font-family: arial;
    font-size: 2em;
}
.accordion-button {
    background-color: white;
}

.accordion-button.collapsed {
    background-color: white;
}

.sr-only {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    white-space: nowrap;
    border-width: 0;
}

/**  contents */
#contents {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

}

form[name=list]{
    display: flex;
    flex-direction: column;
}
.btnList > div:nth-of-type(1){
    display: flex;
    color:#fff;
    width: 30px;
}
.btnList > div:nth-of-type(2){
    display: flex;
    font-size: 15px;
    color:#fff;
    flex: 1;
    justify-content: space-between;
    align-items: center;
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
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 77vw;
    height: 50vh;
    margin: auto;
}

#main_content_header {
    display: flex;
    align-items: flex-end;
    justify-content: center;
    margin-bottom: 20px;
    margin-right: 500px;
    margin-top: 300px;
}
#icon_URL{
    display: flex;
    width: 13em;
    height: 13em;
}
#userName{
    display: flex;
    flex-direction: column;
    color: #fff;
    height: 70%;
    justify-content: space-around;
}
#userName > div:nth-of-type(1){
    font-size: 3em;
}
#userName > div:nth-of-type(2){
    display: flex;
    justify-content: center;
    align-items: center;
    width: 60px;
    height: 30px;
    background: #41434A;
    border-radius: 10px;
    cursor: pointer;
}
#userName > div:nth-of-type(2):hover{
    background: #36373D;
}
#userName > div:nth-of-type(2):active{
    background: #3B3D44;
}
#main_content_body{
    display: flex;
    flex:1;
    gap: 10px;
    width: 100%;
    height: 100%;
}
#main_content1 {
    background: #36393F;
    width: 100%;
    height: 100%;
    overflow: auto;
}
#main_content2 {
    display: flex;
    background: #36393F;
    width: 30%;
    height: 40vh;
}

section .box p {margin: 0; color: white; padding: 200px 20px;}

h2 {

    font-size: 40px;
}

.lead {
    padding: 10px;
    font-size: 1.25rem;
    font-weight: 300;
    background: #3B3D44;
    margin-top: 20px;
    width: 100%;
}
</style>
