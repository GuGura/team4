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
            api.post("/event/listMonthly", {
                date: this.getApi().getDate()
            }).then(({data}) => {
                    if(data && data.length) {
                        for (const i in data) {
                            this.getApi().addEvent({
                                id: data[i].id,
                                title: data[i].title,
                                start: data[i].start,
                                end: data[i].end,
                                allDay: true
                            })
                        }
                    }
            })


        },
        handleWeekendsToggle() {
            this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
        },


        async handleDateSelect(selectInfo) {
            const title = await Swal.fire({
                title: '추가할 일정명을 입력해주세요.',
                html: '<input id="swal-input1" class="swal2-input">',
                focusConfirm: false,
                preConfirm: () => {
                    return document.getElementById('swal-input1').value
                }
            })
            console.log("title-------------", title.value)
            let calendarApi = selectInfo.view.calendar
            calendarApi.unselect() // clear date selection
            if (title.value) {
                api.post("/event/saveEvent", {
                    title: title.value,
                    start: selectInfo.startStr,
                    end: selectInfo.endStr,
                    allDay: selectInfo.allDay
                }).then(({data}) => {
                    calendarApi.addEvent({
                        id: data,
                        title:title.value,
                        start: selectInfo.startStr,
                        end: selectInfo.endStr,
                        allDay: selectInfo.allDay
                    })
                }).catch(err=>{
                    console.log(err)
                })
            }
        },
        handleEventClick(clickInfo) {
            Swal.fire({
                title: `일정 '${clickInfo.event.title}'를 삭제하시겠습니까?`,
                confirmButtonText: "삭제",
            }).then((result) => {
                if (result.isConfirmed) {
                    api.post("/event/deleteEvent", {
                        id: clickInfo.event.id,
                        title: clickInfo.event.title,
                        start: clickInfo.event.start,
                        end: clickInfo.event.end,
                        allDay: clickInfo.event.allDay,
                    }).then(() => {
                        clickInfo.event.remove();
                    });
                }
            });
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

.fc-col-header-cell-cushion {
    color: #1F2123;
}

.fc-event-main {
    background-color: #2b2d31;
}


.fc-daygrid-event-harness {
    background-color: black;
}

.fc-today-button {
    margin-top: -10px;
}


.fc-col-header-cell-cushion {
    color: #5965f3;
}

.fc-daygrid-day-number {
    color: #b6b8cf;
}

#fc-dom-95.fc-daygrid-day-number {
    color: #FFFFFF;
}

.fc .fc-button-primary:disabled {
    margin-top: -2px;
    margin-right: 5px;
}

.fc .fc-button-primary:hover {
    margin-top: -2px;
}

.fc-today-button fc-button fc-button-primary {
    margin-top: -2px
}

.fc .fc-button:disabled {
    margin-top: -2px;
}

.fc .fc-button-primary {
    margin-top: -1px;
    background-color: transparent;
    border-color: transparent;
    margin-right: 5px;
}

.fc .fc-toolbar.fc-header-toolbar {
    margin-bottom: 13px;
    margin-top: 10px;
}

fc-daygrid-more-link fc-more-link {
    color: #F23F42;
}

fc-event-main {
    background-color: grey;
}

.fc-h-event .fc-event-main {
    background-color: #23A559;
}

a {

    text-decoration: none;
}

.fc .fc-more-popover .fc-popover-body {
    background-color: #FFFFFF;
}

.fc-theme-standard .fc-popover-header {
    background-color: #5965f3;

}

.fc-daygrid-event-harness {
    background-color: transparent;
}

.fc-h-event .fc-event-main {
    text-align: center;
}

fc-h-event {
    background-color: yellow;


}

:root {
    --fc-event-border-color: black;
}

.fc-h-event .fc-event-main {
    background-color: transparent;
}

</style>


