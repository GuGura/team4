<script>
import RichTextEditor from "@/components/mainpage/lobby/RichTextEditor.vue";
import Calendar from "@/components/mainpage/lobby/Calendar.vue";
import PostList from "@/components/mainpage/lobby/PostList.vue";

export default {
    components: {PostList, Calendar, RichTextEditor },
    data() {
        return {
            calendarKey: 0,
            profileKey: 0,
            tabs: ['프로필 수정', '일정 보기', '글 작성', '글 목록'],
            tabContents: [
                'Content for Tab 1',
                'Content for Tab 2',
                null, // Placeholder for the RichTextEditor component
                'Content for Tab 4'
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
            <button v-for="(tab, index) in tabs" :key="index" :class="{'tab-button': true, 'active': activeTab === index}" @click="changeTab(index)">
                {{ tab }}
            </button>
        </div>
        <div class="tab-content">
            <div v-for="(content, index) in tabContents" :key="index" :class="{'tab-pane': true, 'active': activeTab === index}">
                <div v-if="index === 0">
                    <div class="content-box">

                    </div>
                </div>
                <div v-else-if="index === 1">
                    <div class="content-box">
                        <Calendar :key="calendarKey" />
                    </div>
                </div>
                <div v-else-if="index === 2">
                    <div class="content-box">
                        <RichTextEditor/>
                    </div>
                </div>
                <div v-else-if="index === 3">
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
    max-height: 400px;
    overflow-y: auto;
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none
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
</style>
