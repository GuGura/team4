<script>

import { defineComponent } from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import axios from "axios";
import loginService from "../../../../script/LoginService";
import router from "../../../../script/router";
import authHeader from "../../../../script/authHeader";
import api from "../../../../script/axios";

export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name:"Calendar",
    components: {
        FullCalendar,
    },
    data() {
        return {
            calendarOptions: {
                customButtons: {
                    prev: { // this overrides the prev button
                        text: "PREV",
                        click: () => {
                            const isTest=loginService.methodTokenCheck()
                            if(isTest){
                                this.getApi().prev();
                                this.getApi().removeAllEvents();
                                console.log(this.getApi().getDate()+": 프리브")
                                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthlyBtn",{
                                    date: this.getApi().getDate()
                                }).then(({data})=>{
                                    for(const i in data){
                                        this.getApi().addEvent({
                                            id: data[i].id,
                                            title: data[i].title,
                                            start: data[i].start,
                                            end: data[i].end,
                                            allDay: data[i].allDay
                                        })
                                    }
                                })

                            }else {
                                router.push("/login").then()
                            }


                        }
                    },
                    next: { // this overrides the next button
                        text: "NEXT",
                        click: () => {
                            const isTest=loginService.methodTokenCheck()
                            if(isTest){
                                this.getApi().next();
                                this.getApi().removeAllEvents();
                                console.log(this.getApi().getDate()+": 넥스트")
                                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthlyBtn",{
                                    date: this.getApi().getDate()
                                }).then(({data})=>{
                                    for(const i in data){
                                        this.getApi().addEvent({
                                            id: data[i].id,
                                            title: data[i].title,
                                            start: data[i].start,
                                            end: data[i].end,
                                            allDay: data[i].allDay
                                        })
                                    }
                                })

                            }else{
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
        initCalendar(){
            const isTest=loginService.methodTokenCheck()
            if(isTest){
                console.log("캘린더로드")
                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/listMonthly",{
                    date: this.getApi().getDate()
                }).then(({data})=>{
                    for(const i in data){
                        this.getApi().addEvent({
                            id: data[i].id,
                            title: data[i].title,
                            start: data[i].start,
                            end: data[i].end,
                            allDay: data[i].allDay
                        })
                    }
                })
            }else {
                router.push("/login").then()
            }

        },
        handleWeekendsToggle() {
            this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
        },
        handleDateSelect(selectInfo) {
            let title = prompt('Please enter a new title for your event')
            let calendarApi = selectInfo.view.calendar

            calendarApi.unselect() // clear date selection

            if (title) {
                let id;
                api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/saveEvent",{
                    title,
                    start: selectInfo.startStr,
                    end: selectInfo.endStr,
                    allDay: selectInfo.allDay
                }).then(({data}) => {
                    id = data;
                })
                calendarApi.addEvent({
                    id: id,
                    title,
                    start: selectInfo.startStr,
                    end: selectInfo.endStr,
                    allDay: selectInfo.allDay
                })


            }
        },
        handleEventClick(clickInfo) {
            if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
                clickInfo.event.remove()
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
.demo-app-calendar{

    width: 70%;

}




</style>


