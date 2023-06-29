<script setup>
import {onMounted, reactive} from "vue";
import api from "../../../../script/token/axios";

function addEvent(){
    let title = prompt('추가할 일정명을 입력해주세요.')
    if (title) {
        let id;
        api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/saveEvent",{
            title,
            start: today.setDate(today.getDate()-1),
            end: today.setDate(today.getDate()+1),
            allDay: true
        }).then(() => {
            initEvents()
        })
    }
}
function initEvents() {
    api.post(process.env.VUE_APP_BASEURL_V1 + "/event/listByDate", {
        year: year,
        month: month,
        date: date
    }).then(({data}) => {
        eventList.events = data
    })
}

onMounted(() => {
    initEvents()
    console.log(eventList.events)
})


let today = new Date();
let year = today.getFullYear();
let month = today.getMonth() + 1;
let date = today.getDate();



let eventList = reactive({
    events: [
        {
            id: '',
            start: '',
            end: '',
            title: '',
            groupName: '',
            memberId: ''
        }

    ]
})

</script>

<template>
  <div class="container">
      <div class="card">
          <div class="card-header">
              <h3>{{year}}.{{month}}.{{date}}</h3>
          </div>
          <div class="card-body">
              <ul class="list-group list-group-flush">
                  <li v-for="(events) in eventList.events" :key="events.title" class="list-group-item mb-3">
                      <h5>
                          {{events.title}}
                      </h5>
                      <h2 class="fw-light">
                          By {{events.groupName}}
                      </h2>
                  </li>
              </ul>
              <button class="btn btn-secondary" id="btnAdd" @click="addEvent">Add+</button>
          </div>
      </div>

  </div>

</template>

<style scoped>
.card-header{
    text-align: center;
}
#btnAdd{
    margin:auto;
    display:block;
}
.list-group-item:hover{
    background-color: lightgray;
}

.card{
    height: 500px;
}

</style>