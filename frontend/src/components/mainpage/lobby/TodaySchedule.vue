<script setup>
import {onMounted, reactive} from "vue";
import api from "../../../../script/token/axios";


function addEvent(){
    let title = prompt('추가할 일정명을 입력해주세요.')
    if (title) {
        api.post("/home/saveEvent",{
            title,
            start: today.setDate(today.getDate()),
            end: today.setDate(today.getDate()+1),
            allDay: true
        }).then(() => {
            today.setDate(today.getDate()-1),
            initEvents()
        })
    }
}
function initEvents() {
    api.post("/event/listByDate", {
        year: Dates.year,
        month: Dates.month,
        date: Dates.date
    }).then(({data}) => {
        eventList.events = data
    })
}

function prevDate(){
    eventList.events = null
    today.setDate(today.getDate()-1)
    dateCalculating()
    initEvents();
}

function nextDate(){
    eventList.events = null
    today.setDate(today.getDate()+1)
    dateCalculating()
    initEvents();
}

onMounted(() => {
})

let today = new Date();

let Dates = reactive({
    year:today.getFullYear(),
    month: today.getMonth() + 1,
    date: today.getDate()
})

function dateCalculating(){
    Dates.year = today.getFullYear();
    Dates.month = today.getMonth()+1;
    Dates.date = today.getDate();
}


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

initEvents()
</script>

<template>
  <div class="container">
      <div class="card" id="wholeCard">
          <div class="card-header">
              <h4>
                  <button class="btnDate" @click="prevDate">&lt;</button>
                     {{Dates.year}}.{{Dates.month}}.{{Dates.date}}
                  <button class="btnDate" @click="nextDate">&gt;</button>
              </h4>

          </div>
          <div class="card-body">
              <ul class="list-group">
                  <li v-for="(events) in eventList.events" :key="events.title" class="card">
                      <h5>
                          {{events.title}}
                      </h5>
                      <h2 class="fw-light">
                          By {{events.groupName}}
                      </h2>
                  </li>
              </ul>
              <button id="btnAdd" @click="addEvent">Add+</button>
          </div>
      </div>

  </div>

</template>

<style scoped>

.btnDate{
    border-radius: 10px;
    border: none;
    padding: 5px;
    background: #36373D;
    color: white;
}
.btnDate:hover{
    background: #41434A;
}

.card-header{
    text-align: center;
    background-color: #36373D;
    padding: 20px;
    border-radius: 10px;
}
#btnAdd{
    margin:auto;
    display:block;
    border-radius: 10px;
    border: none;
    padding: 10px;
    background-color: #36373D;
    color: white;
}

#btnAdd:hover{
    background: #41434A;
}
.card{
    background: #36373D;
    margin: 0px;
    padding: 20px;
    color: white;
    border:none;
}


.card:hover{
    background-color: #41434A;
}


#wholeCard{
    height: 600px;
    border: none;
    background: #36373D;
}

</style>