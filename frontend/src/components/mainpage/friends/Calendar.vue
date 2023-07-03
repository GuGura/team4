<script>

import { defineComponent } from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import api from "../../../../script/token/axios";
import {useFriendStore} from "../../../../script/stores/friend";

export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name:"Calendar",
    components: {
        FullCalendar,
    },
    data() {
        const friendStore = useFriendStore();
        return {

            calendarOptions: {
                customButtons: {
                    today: {
                        text: "TODAY",
                        click: () => {
                            this.getApi().today()
                            this.getApi().removeAllEvents();
                            api.post(process.env.VUE_APP_BASEURL_V1 + "/event/listMonthlyFriend", {
                                date: this.getApi().getDate(),id:friendStore.user.id
                            }).then(({data}) => {
                                for (const i in data) {
                                    this.getApi().addEvent({
                                        id: data[i].id,
                                        title: data[i].title,
                                        start: data[i].start,
                                        end: data[i].end,
                                        allDay: true
                                    })
                                }
                            })
                        }
                    },
                    prev: { // this overrides the prev button
                        text: "PREV",
                        click: () => {
                            this.getApi().prev();
                            this.getApi().removeAllEvents();
                            api.post(process.env.VUE_APP_BASEURL_V1 + "/event/listMonthlyBtnFriend", {
                                date: this.getApi().getDate(),id:friendStore.user.id
                            }).then(({data}) => {
                                for (const i in data) {
                                    this.getApi().addEvent({
                                        id: data[i].id,
                                        title: data[i].title,
                                        start: data[i].start,
                                        end: data[i].end,
                                        allDay: true
                                    })
                                }
                            })


                        }
                    },
                    next: { // this overrides the next button
                        text: "NEXT",
                        click: () => {
                            this.getApi().next();
                            this.getApi().removeAllEvents();
                            console.log(this.getApi().getDate() + ": 넥스트")
                            api.post(process.env.VUE_APP_BASEURL_V1 + "/event/listMonthlyBtnFriend", {
                                date: this.getApi().getDate(),id:friendStore.user.id
                            }).then(({data}) => {
                                for (const i in data) {
                                    this.getApi().addEvent({
                                        id: data[i].id,
                                        title: data[i].title,
                                        start: data[i].start,
                                        end: data[i].end,
                                        allDay: true
                                    })
                                }
                            })
                        }
                    }
                },
                plugins: [
                    dayGridPlugin,
                    timeGridPlugin,
                    interactionPlugin // needed for dateClick
                ],
                headerToolbar: {
                    left: 'prev,next',
                    center: 'title',
                    right: 'today'
                },
                initialView: 'dayGridMonth',
                editable: true,
                selectable: true,
                selectMirror: true,
                dayMaxEvents: true,
                weekends: true,
                select: this.handleDateSelect,
                eventClick: this.handleEventClick,
                eventsSet: this.handleEvents,
            },
            currentEvents: [],
        }
    },
    mounted() {
        this.initCalendar(useFriendStore());
    },
    methods: {
        initCalendar(friendStore) {
            this.getApi().removeAllEvents()
            api.post(process.env.VUE_APP_BASEURL_V1 + "/event/listMonthlyFriend", {
                date: this.getApi().getDate(), id:friendStore.user.id
            }).then(({data}) => {
                for (const i in data) {
                    this.getApi().addEvent({
                        id: data[i].id,
                        title: data[i].title,
                        start: data[i].start,
                        end: data[i].end,
                        allDay: true
                    })
                }
            })


        },
        handleWeekendsToggle() {
            this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
        },
        handleDateSelect() {

        },
        handleEventClick(clickInfo) {
            if (confirm(`일정 '${clickInfo.event.title}'을 내 캘린더에 추가하시겠습니까?`)) {
                api.post(process.env.VUE_APP_BASEURL_V1 + "/event/saveFriendEvent", {
                    id: clickInfo.event.id,
                }).then(() => {
                    alert("추가되었습니다.")
                })
            }
        },
        handleEvents(events) {
            this.currentEvents = events

        },
        getApi() {
            let calendarApi = this.$refs.fc.getApi();
            return calendarApi;
        },


    }
})

</script>

<template>
    <FullCalendar
        class='demo-app-calendar'
        :options='calendarOptions'
        ref="fc"
    >
        <template v-slot:eventContent='arg'>
            <b>{{ arg.timeText }}</b>
            <i>{{ arg.event.title }}</i>
        </template>
    </FullCalendar>
</template>

<style>
#app {
    flex: 1;
    margin: 0;
    padding: 0;

}

h2 {
    margin: 0;
    font-size: 16px;
}

ul {
    margin: 0;
    padding: 0 0 0 1.5em;
}

.demo-app-calendar {
    width: 100%;
    color: #ffffff;
    border-radius: 5px;
    height: 100%;
}

.fc-daygrid-view .fc-daygrid-day-frame {
    border-color: black;
}
.fc-col-header-cell-cushion{
    color: #1F2123;
}

.fc-event-main{
    background-color: #2b2d31;
}


.fc-daygrid-event-harness {
    background-color: black;
}

.fc-today-button {
    margin-top: -10px;
}



.fc-col-header-cell-cushion{
    color: #5965f3;
}
.fc-daygrid-day-number{
    color: #b6b8cf;
}
#fc-dom-95.fc-daygrid-day-number{
    color: #FFFFFF;
}
.fc .fc-button-primary:disabled {
    margin-top: -2px;
    margin-right: 5px;
}
.fc .fc-button-primary:hover{
    margin-top: -2px;
}
.fc-today-button fc-button fc-button-primary{
    margin-top:-2px
}
.fc .fc-button:disabled{
    margin-top: -2px;
}

.fc .fc-button-primary{
    margin-top: -1px;
    background-color: transparent;
    border-color: transparent;
    margin-right: 5px;
}
.fc .fc-toolbar.fc-header-toolbar {
    margin-bottom: 13px;
    margin-top: 10px;
}
fc-daygrid-more-link fc-more-link{
    color: #F23F42;
}
fc-event-main{
    background-color: grey;
}

.fc-h-event .fc-event-main{
    background-color: #23A559;
}

a {

    text-decoration: none;
}

.fc .fc-more-popover .fc-popover-body{
    background-color: #FFFFFF;
}

.fc-theme-standard .fc-popover-header{
    background-color: #5965f3;

}

.fc-daygrid-event-harness{
    background-color: transparent;
}
.fc-h-event .fc-event-main{
    text-align: center;
}

fc-h-event{
    background-color: yellow;


}
:root{
    --fc-event-border-color: #yellow;
}

.fc-h-event .fc-event-main{
    background-color: transparent;
}

</style>





