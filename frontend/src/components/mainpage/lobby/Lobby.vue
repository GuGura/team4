<script setup>
import {reactive} from "vue";

const state = reactive({
  isActive: 'P'
})

</script>

<template>
  <div id="main_contents">
    <div id="main_content_header">
      <div id="icon_URL">
        <img src="/img/sidebar/userIcon.png" alt="헬로" style="width: 200px;">
      </div>
      <div id="userName">
        <div>meatTeam</div>
        <div>수정</div>
      </div>
    </div>
    <div id="main_content_body">
      <div id="main_content1">
        <div>
          <div style="display: flex; height: 30px;gap: 5px">
            {{state.isActive}}
            <button @click="state.isActive === 'P' ">프로필</button>
            <button @click="state.isActive === 'W' ">글쓰기</button>
            <button @click="state.isActive === 'C' ">달력 보기</button>
          </div>
          <div>
            <textarea v-if="state.isActive === 'P'" style="width: 700px;height: 400px;outline: none" readonly>안녕하세요</textarea>
            <textarea v-else-if="state.isActive === 'W'" style="width: 700px;height: 400px;outline: none" readonly>안녕하세요</textarea>
            <textarea v-else-if="state.isActive === 'C'" style="width: 700px;height: 400px;outline: none" readonly>안녕하세요</textarea>
          </div>
        </div>
      </div>
      <div id="main_content2"></div>
    </div>
  </div>
</template>

<style scoped>
#main_contents {
  display: flex;
  flex: 1;
  flex-direction: column;
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none
}

#main_content_header {
  display: flex;
  height: 35%;
  align-items: flex-end;
  padding: 0 70px;
  gap: 35px;
}

#icon_URL {
  display: flex;
  width: 13em;
  height: 13em;
}

#userName {
  display: flex;
  flex-direction: column;
  color: #fff;
  height: 70%;
  justify-content: space-around;
}

#userName > div:nth-of-type(1) {
  font-size: 3em;
}

#userName > div:nth-of-type(2) {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  height: 30px;
  background: #41434A;
  border-radius: 10px;
  cursor: pointer;
}

#userName > div:nth-of-type(2):hover {
  background: #36373D;
}

#userName > div:nth-of-type(2):active {
  background: #3B3D44;
}

#main_content_body {
  display: flex;
  flex: 1;
  padding: 0 0 0 30px;
  gap: 10px;
}

#main_content1 {
  display: flex;
  background: #36393F;
  width: 73%;
  height: 98%;
}

#main_content2 {
  display: flex;
  background: #36393F;
  width: 25%;
  height: 60%; /* 임시로 */
}
</style>