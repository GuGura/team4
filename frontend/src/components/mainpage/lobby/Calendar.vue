<script>

import {defineComponent} from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import loginService from "../../../../script/LoginService";
import router from "../../../../script/routes/router";
import api from "../../../../script/token/axios";

export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Calendar",
    components: {
        FullCalendar,
    },
    data() {
        return {
            calendarOptions: {
                customButtons: {
                    today: {
                        text: "TODAY",
                        click: () => {
                            this.getApi().today()
                            this.getApi().removeAllEvents();
                            console.log(this.getApi().getDate() + ": 프리브")
                            api.post("/event/listMonthly", {
                                date: this.getApi().getDate()
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
                            api.post("/event/listMonthlyBtn", {
                                date: this.getApi().getDate()
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
                            api.post("/event/listMonthlyBtn", {
                                date: this.getApi().getDate()
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
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth'
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
        this.initCalendar();
    },
    methods: {
        initCalendar() {
            api.post("/event/listMonthly", {
                date: this.getApi().getDate()
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
        handleDateSelect(selectInfo) {
            let title = prompt('추가할 일정명을 입력해주세요.')
            let calendarApi = selectInfo.view.calendar

            calendarApi.unselect() // clear date selection

            if (title) {
                api.post("/home/saveEvent", {
                    title,
                    start: selectInfo.startStr,
                    end: selectInfo.endStr,
                    allDay: selectInfo.allDay
                }).then(({data}) => {
                    calendarApi.addEvent({
                        id: data,
                        title,
                        start: selectInfo.startStr,
                        end: selectInfo.endStr,
                        allDay: selectInfo.allDay
                    })
                })


            }
        },
        handleEventClick(clickInfo) {
            if (confirm(`일정 '${clickInfo.event.title}'를 삭제하시겠습니까?`)) {
                clickInfo.event.remove()
                api.post("/home/deleteEvent", {
                    id: clickInfo.event.id,
                    title: clickInfo.event.title,
                    start: clickInfo.event.start,
                    end: clickInfo.event.end,
                    allDay: clickInfo.event.allDay
                }).then(() => {
                    clickInfo.event.remove()
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

li {
    margin: 1.5em 0;
    padding: 0;
}

b { /* used for event dates/times */
    margin-right: 3px;
}

.demo-app-calendar {

    width: 100%;

}

.fc-col-header-cell-cushion {
    color: #1F2123;
}

.fc-event-main {
    background-color: yellow;
    border-color: yellow;
    border-radius: 2px;
}

:root {
    --fc-event-border-color: black;
}


</style>


