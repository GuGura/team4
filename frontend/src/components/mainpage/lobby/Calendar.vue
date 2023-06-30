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
                    today: { // this overrides the prev button
                        text: "TODAY",
                        click: () => {
                            const isTest = loginService.methodTokenCheck()
                            if (isTest) {
                                this.getApi().today()
                                this.getApi().removeAllEvents();
                                console.log(this.getApi().getDate() + ": 프리브")
                                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthly", {
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

                            } else {
                                router.push("/login").then()
                            }


                        }
                    },
                    prev: { // this overrides the prev button
                        text: "PREV",
                        click: () => {
                            const isTest = loginService.methodTokenCheck()
                            if (isTest) {
                                this.getApi().prev();
                                this.getApi().removeAllEvents();
                                console.log(this.getApi().getDate() + ": 프리브")
                                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthlyBtn", {
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

                            } else {
                                router.push("/login").then()
                            }


                        }
                    },
                    next: { // this overrides the next button
                        text: "NEXT",
                        click: () => {
                            const isTest = loginService.methodTokenCheck()
                            if (isTest) {
                                this.getApi().next();
                                this.getApi().removeAllEvents();
                                console.log(this.getApi().getDate() + ": 넥스트")
                                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthlyBtn", {
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

                            } else {
                                router.push("/login").then()
                            }


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
        this.initCalendar();
    },
    methods: {
        initCalendar() {
            const isTest = loginService.methodTokenCheck()
            if (isTest) {
                console.log("캘린더로드")
                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthly", {
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
            } else {
                router.push("/login").then()
            }

        },
        handleWeekendsToggle() {
            this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
        },
        handleDateSelect(selectInfo) {
            let title = prompt('추가할 일정명을 입력해주세요.')
            let calendarApi = selectInfo.view.calendar

            calendarApi.unselect() // clear date selection

            if (title) {
                let id;
                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/saveEvent", {
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
                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/deleteEvent", {
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

<style scoped>
.demo-app-calendar {
    width: 100%;
    background-color: gray;
    color: #ffffff;
    border-radius: 5px;
}

.fc-daygrid-view .fc-daygrid-day-frame {
    border-color: black;
}


.fc-daygrid-event-harness {
    background-color: black;
}

.fc-today-button {
    margin-top: -10px;
}

.fc-day-sun a {
    color: red;
    text-decoration: none;
}

.fc-col-header-cell-cushion{
    color: #1E1F22;
}
.fc-daygrid-day-number{
    color: white;
}

</style>





