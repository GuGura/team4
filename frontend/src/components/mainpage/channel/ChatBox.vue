<script>
export default {
  name:'ChatBox',
  props:{
    message : Object
  }
}
</script>

<template>

    <div class="UserName">
      <img src="/img/channel/userIcon.png">
    </div>
    <div class="UserMessage">
      <div>
        <div>{{ message.user }}</div>
        <div>{{message.sendDate}}</div>
      </div>
      <div>{{message.message}}</div>
    </div>

</template>

<style scoped>

.UserName{
  display: flex;
  width: 40px;
  height: 40px;
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none
}
.UserMessage{
  display: flex;
  flex-direction: column;
}
.UserMessage > div:nth-of-type(1){
  display: flex;
  gap: 10px;
  align-items: center;
}
.UserMessage > div:nth-of-type(1) > div:nth-of-type(1){
  color: #F2F3F5;
}
.UserMessage > div:nth-of-type(1) > div:nth-of-type(2){
  color: #949BA4;
  font-size: 12px;
}
.UserMessage > div:nth-of-type(2) {
  color: #D1D4D7;
}
</style>