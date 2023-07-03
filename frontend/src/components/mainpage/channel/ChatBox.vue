<script>
export default {
  name:'ChatBox',
  data() {
    return {
      roomId: '',
      sender: '',
    }
  },
  props:{
    messages : Object
  },
  created() {
    this.formattedDate();
  },
  methods: {
    formattedDate() {
      let date;
      if (typeof this.messages.sendDate === 'number') {
        date = new Date(this.messages.sendDate);
      } else {
        date = new Date(this.messages.sendDate);
      }
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');

      const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      return formattedDate;
    }
  }
}
</script>

<template>

    <div class="UserName">
      <img src="/img/channel/userIcon.png">
    </div>
    <div class="UserMessage">
      <div>
        <div>{{ messages.sender }}</div>
        <div>{{ formattedDate() }}</div>
      </div>
      <div>{{ messages.message }}</div>
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