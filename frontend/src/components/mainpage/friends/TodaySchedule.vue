<script setup>
import {onMounted, reactive} from "vue";
import api from "../../../../script/token/axios";
import {useFriendStore} from "../../../../script/stores/friend";


const days = ["일", "월", "화", "수", "목", "금", "토"];

const friendStore=useFriendStore();

function initEvents() {
    api.post(process.env.VUE_APP_BASEURL_V1 + "/event/listByDate", {
        year: Dates.year,
        month: Dates.month,
        date: Dates.date,
        id: friendStore.user.id
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
    date: today.getDate(),
    days: '',
    months: ''

})

function dateCalculating(){
    Dates.year = today.getFullYear();
    Dates.month = today.getMonth()+1;
    Dates.date = today.getDate();
    Dates.days = '';
    Dates.months = '';
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
                    {{ Dates.month }}월 {{ Dates.date }}일 {{ days[Dates.days] }}
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
    background-color: transparent;
    padding: 20px;
    border-bottom: transparent;
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
    background: #41434A;
    margin: 0px;
    padding: 20px;
    color: white;
    border:none;
    margin-bottom: 10px;
}
.card:hover{
    background-color: #2b2d31;
}

#wholeCard{
    height: 600px;
    border: none;
    background: #36373D;
}

</style>