
<template>
    <div class="tab-container">
        <div class="tab-buttons">
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

<style scoped>
.content-box {
    width: 100%;
    height: 100%;
    background-color: white;
    border: 1px solid black;
    padding: 20px;
}

.tab-container {
    width: 100%;
}

.tab-buttons {
    display: flex;
}

.tab-button {
    width: 100%;
    padding: 10px 20px;
    background-color: lightgray;
    border: none;
    outline: none;
    cursor: pointer;
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




