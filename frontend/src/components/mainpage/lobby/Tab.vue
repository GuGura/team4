<script>

import Calendar from "@/components/mainpage/lobby/Calendar.vue";
import PostList from "@/components/mainpage/friends/PostList.vue";

export default {
    components: {PostList, Calendar},
    data() {
        return {
            calendarKey: 0,
            profileKey: 0,
            tabs: ['내 피드', '일정 보기', '글 목록'],
            tabContents: [
                'Content for Tab 1',
                'Content for Tab 2',
                null, // Placeholder for the RichTextEditor component
            ],
            activeTab: 0,
        };
    },
    methods: {
        changeTab(index) {
            if (index === 1) {
                this.callCalendarLoad();
            }else if(index === 0) {
                this.callProfileLoad();
            }
            this.activeTab = index;
        },
        callCalendarLoad() {
            this.calendarKey += 1;
        },
        callProfileLoad(){
            this.profileKey +=1;
        },
    },
};
</script>
<template>
    <div class="tab-container card">
        <div class="tab-buttons card-header">
            <button
                v-for="(tab, index) in tabs"
                :key="index"
                :class="{'tab-button': true, 'active': activeTab === index}"
                @click="changeTab(index)"
            >
                <div v-if="index === 0">
                    <span class="material-symbols-outlined">home</span></div>
                <div v-else-if="index === 1">
                    <span class="material-symbols-outlined">calendar_month</span></div>
                <div v-else-if="index === 2">
                    <span class="material-symbols-outlined">list_alt</span></div>
                {{ tab }}
            </button>
        </div>
        <div class="tab-content">
            <div v-for="(content, index) in tabContents" :key="index" :class="{'tab-pane': true, 'active': activeTab === index }">
                <div v-if="index === 0">
                    <div class="content-box"></div>
                </div>
                <div v-else-if="index === 1">
                    <div class="content-box">
                        <Calendar :key="calendarKey" />
                    </div>
                </div>
<!--                <div v-else-if="index === 2">-->
<!--                    <div class="content-box">-->
<!--                        <RichTextEditor/>-->
<!--                    </div>-->
<!--                </div>-->
                <div v-else-if="index === 2">
                    <div class="content-box">
                        <PostList/>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>



<style scoped>
.content-box {
    width: 100%;
    height: 100%;
    background: #41434A;
    padding: 20px;
}

.content-box::-webkit-scrollbar{
    width: 10px;
}

.content-box::-webkit-scrollbar-thumb{
    background-color: #1A1B1E;
    border: 4px solid #1A1B1E;
    border-radius: 50px;
}

/* 스크롤바 뒷 배경 설정*/
.content-box::-webkit-scrollbar-track{
    background-color: rgba(0,0,0,0);
    width: 15px;
}

.tab-container {
    width: 100%;
    color: white;
    background: #41434A;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}

.tab-buttons {
    display: flex;
    background-color: #36373D;
    color: white;
    margin: 0;
    padding: 0;
    border-radius: 10px;

}

.tab-button {
    width: 100%;
    padding: 10px 20px;
    background-color: #36373D;
    outline: none;
    cursor: pointer;
    color: white;
    border-radius: 10px 10px 0px 0px;
    border: none;
}
.tab-button:hover{
    background: #41434A;
}

.tab-button.active {
    background-color: gray;
    color: white;
}

.tab-pane {
    display: none;
}

.tab-pane.active {
    display: block;
}

.tab-buttons.card-header{
    border-radius: 0px;
}

.material-symbols-outlined{
    display: flex;
    justify-content: space-evenly;
    align-items: center;

}
</style>
