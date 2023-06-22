<template>
    <div id="contents">
        <div id="main_contents">
            <div id="main_content_header">
                <div id="icon_URL">
                    <img src="@/assets/image/userIcon.png" alt="헬로" style="width: 200px; position:relative; z-index: 4;">
                </div>
                <div id="userName" style="z-index: 3;">
                    <div class="col-md-7 order-md-2">
                        <h2 class="featurette-heading fw-normal lh-1">meatTeam </h2>
                        <div class="lead"> 앙용하세요 예,, 한국어가 좋다 이말입니다,,모두들 한국어를 아끼고 사랑하세요 사실 저도 제가 무슨말을 쓰고 있는지 몰라요..그저 유저 정보 설명칸을 채우기 위해 말을 주절주절 써보는데
                            자소서 쓰는것 보다는 재미있지만 뭐 그닥 추천해드리고 싶지는 않아요,,, 오늘 제 옆자리에 아무도 출석을 하지 않아서 심심하기는 한데용,,자리를 짱 넓게 쓸 수 있어서 맥시멀리스트인 저에겐 아주 최고랍니다,,(오히려 조하,,👍) 하 저 증말 말 많이 한 것 같은데
                            왜 아직도 쓸 칸이 많이 남은 것 같죠,? 기분탓이겠지요,, 아 몰루 이제 그만 쓸래욥 앙용!


                        </div>
                    </div>
                </div>
            </div>
            <!--            <div id="description">-->
            <!--                <div id="user_description" style="z-index: 1;">안녕하세요 저는...</div>-->
            <!--            </div>-->
            <div id="main_content_body">
                <div id="main_content1">
                    <div class="accordion" id="accordionExample">
                        <Accordion/>

                    </div>
                </div>
                <div id="main_content2"></div>
            </div>
        </div>
    </div>
</template>
<script setup>
// import axios from "axios";

import Accordion from "@/components/mainpage/lobby/Arccodion.vue";
import {ref} from "vue";

const isExpanded = ref('')

function toggleAccordion(index){
    if (this.isExpanded === index) {
        this.isExpanded = null; // Collapse the active accordion
    } else {
        this.isExpanded = index; // Expand the clicked accordion
    }
}

// export default {
//     name: "Lobby",
//     components: {Accordion},
//
//     data() {
//         return {
//             isExpanded: null
//
//         };
//     },
//     methods: {
//         toggleAccordion(index) {
//             if (this.isExpanded === index) {
//                 this.isExpanded = null; // Collapse the active accordion
//             } else {
//                 this.isExpanded = index; // Expand the clicked accordion
//             }
//         }
//     },
//
// }
</script>

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
#contents{
    display: flex;
    flex:1;
    background: #1E1F22;
    padding-left: 74px;
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


#main_contents{
    display: flex;
    flex: 1;
    flex-direction: column;
}
#main_content_header{
    display: flex;
    height: 35%;
    align-items: flex-end;
    padding: 0 70px;
    gap: 35px;
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
    padding: 0 0 0 30px;
    gap: 10px;
}
#main_content1 {
    margin-top: 15px;
    background: #36393F;
    width:73%;
    height: 700px; /* 스크롤 영역의 높이를 지정 */
    overflow: auto; /* 스크롤이 가능하도록 설정 */
}
#main_content2{
    display: flex;
    background: #36393F;
    width: 25%;
    height: 60%; /* 임시로 */
}

section .box p {margin: 0; color: white; padding: 200px 20px;}

h2 {

    margin: 0;
    font-size: 50px;
}

.lead {
    padding: 10px;
    font-size: 1.25rem;
    font-weight: 300;
    background: #3B3D44;
    margin-top: 20px;
    margin-bottom: 3rem;
    width: 1300px;
}
</style>