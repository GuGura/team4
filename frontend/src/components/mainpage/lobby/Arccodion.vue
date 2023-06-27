
<template>
    <div id="accordionExample" class="h-100 p-5 bg-body-tertiary border rounded-3">
        <div class="accordion-item" v-for="i in 3" :key="i">
            <h2 class="accordion-header" :id="'heading' + i">
                <button
                        class="accordion-button"
                        type="button"
                        :data-bs-toggle="'#collapse' + i"
                        :data-bs-target="'#collapse' + i"
                        :aria-expanded="isExpanded === i ? 'true' : 'false'"
                        :aria-controls="'collapse' + i"
                        @click="toggleAccordion(i)"
                        :class="{ 'collapsed': isExpanded !== i, 'expanded': isExpanded === i }"
                >
                    <span v-if="isExpanded !== i" class="sr-only">Expand</span>
                    <span v-else class="sr-only">Collapse</span>
                    <span v-if="i === 1">프로필 수정</span>
                    <span v-else-if="i === 2">글쓰기</span>
                    <span v-else>달력 보기</span>
                </button>
            </h2>
            <div
                    :id="'collapse' + i"
                    class="accordion-collapse collapse"
                    :class="{ 'show': isExpanded === i }"
                    :aria-labelledby="'heading' + i"
            >
                <div class="accordion-body d-flex justify-content-center align-items-center">
                    <template v-if="i === 1">
                        <Profile :key="profileKey"/>


                    </template>
                    <template v-else-if="i === 2">
                        <RichTextEditor />
                        <button @click="uploadText()">제출</button>

                    </template>
                    <template v-else>
                        <Calendar :key="calendarKey" />
                    </template>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Calendar from "@/components/mainpage/lobby/Calendar.vue";
import RichTextEditor from "@/components/mainpage/lobby/RichTextEditor.vue";
import Profile from "@/components/mainpage/lobby//Profile.vue";


export default {
    name: "Accordion",
    components: {
        Profile,
        RichTextEditor,
        Calendar,
    },
    data() {
        return {
            isExpanded: null,
            calendarKey: 0,
            profileKey: 0
        };
    },
    methods: {
        callCalendarLoad() {
            this.calendarKey += 1;
        },
        callProfileLoad(){
            this.profileKey +=1;
        },
        toggleAccordion(index) {
            if (this.isExpanded === index) {
                this.isExpanded = null; // Collapse the active accordion
            } else {
                if (index == 3) {
                    this.callCalendarLoad();
                }else if(index == 1) {
                    this.callProfileLoad();
                }
                this.isExpanded = index; // Expand the clicked accordion
            }
        },
    },
};
</script>

<style scoped>
.accordion-body {
    width: 100%;
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

.h-100 p-5 bg-body-tertiary border rounded-3 {
    background-color: #1A1B1E;

}
</style>
