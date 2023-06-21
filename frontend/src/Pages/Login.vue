<template>
  <div id="body" :style="{backgroundImage: `url(/img/sign/LoginBackground.png)`}">
    <div id="container">
      <div id="content">
        <div id="header">
          <div id="headerText1">돌아오신 것을 환영해요!</div>
          <div id="headerText2">다시 만나다니 너무 반가워요!</div>
        </div>
        <div id="Form">
          <form @submit.prevent="login()">
            <TextBox subject="이메일 또는 전화번호"
                     box-type="text"
                     box-name="email"
                     v-model="result.username"/>
            <TextBox subject="비밀번호"
                     box-type="password"
                     box-name="passwd"
                     v-model="result.password"/>
            <div class="hrefText" style="height: 32px;">
              <a href="#" @click="forgotPassword()">비밀번호를 잊으셨나요?</a>
            </div>
            <SubmitBtn text="로그인"/>
            <div class="hrefText">
              <div>계정이 필요하신가요?</div>
              <router-link to="/join">가입하기</router-link>
            </div>
          </form>
        </div>
      </div>
      <div style="display:flex;width: 40px;"></div>
      <div id="Logo">
        <div><img src="/img/sign/Logo.png" alt=""></div>
        <div style="color: #fff;">소셜 계정으로 간편 로그인</div>
        <div id="OtherLogin" style="display: flex">
          <div id="btnGoogle"><img src="/img/sign/Frame%207.png" alt=""></div>
          <div><img src="/img/sign/kakao@3x.png" alt=""></div>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import {reactive} from "vue";
import axios from "axios";
import SubmitBtn from "@/components/sign/SubmitBtn.vue";
import TextBox from "@/components/sign/TextBox.vue";
import router from "../../script/router";
import {useStore} from "vuex";

const store = useStore();

const result = reactive({
  username: '',
  password: ''
});

function forgotPassword(){
  if (result.username === '') {
    alert("이메일을 입력해주세요")
    return
  }
  console.log("이메일 입력완료")
}
function login(){
  axios.post(process.env.VUE_APP_BASEURL + "/login", JSON.stringify(result))
      .then((res) => {
        if (res.data.status) {
          const accessJwt = res.headers.get('accessJwt');
          const refreshJwt = res.headers.get('refreshJwt')
          const token = {accessJwt: accessJwt, refreshJwt: refreshJwt}
          localStorage.setItem(`token`, JSON.stringify(token)) //토큰 저장까지 확인
          store.commit('setAccessJwt', accessJwt);
          store.commit('setRefreshJwt', refreshJwt);
          store.commit('setUser',result.username);

          router.push("/").then(()=>console.log("로그인 성공"))
        } else {
          alert(res.data.message)
        }
      })
}
</script>

<style scoped>

#body {
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;

  position: fixed;
  width: 100%;
  height: 100%;
  z-index: 1;
  background-size: cover;
  background-repeat: no-repeat;
}

#container {
  display: flex;
  justify-content: center;
  padding: 32px;
  gap: 10px;

  background: #313338;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  border-radius: 5px;

  /* Inside auto layout */
  z-index: 2;
}

#content {
  /* Auto layout */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0;
  gap: 20px;

  width: 420px;
}

#header, #Form, #OtherLogin {
  width: 100%;
}

/** #header 관련 css **/
#header {
  /* Auto layout */
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
}

#headerText1, #headerText2 {
  font-family: 'Noto Sans';
  font-style: normal;
  display: flex;
  align-items: center;
  text-align: center;
}

#headerText1 {
  /* 돌아오신 것을 환영해요! */
  font-weight: 600;
  font-size: 24px;
  line-height: 30px;
  /* identical to box height, or 136% */
  color: #F2F3F5;
}

#headerText2 {
  /* 다시 만나다니 너무 반가워요! */
  font-weight: 400;
  font-size: 16px;
  line-height: 20px;
  /* identical to box height, or 133% */
  color: #B5BAC1;
}

/** #header 관련 css 끝 **/
/** #Form 관련 css **/
Form {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0;
  gap: 10px;
}

.inputBox { /* ////////////////////////////////////////////////////////////////////////////////////////////// */
  /* Auto layout */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0;
  gap: 8px;

  width: 100%;
}

.textBox { /* ////////////////////////////////////////////////////////////////////////////////////////////// */
  width: 95%;
  height: 40px;
  background: #1E1F22;
  border-radius: 3px;
  color: white;
  outline: none;
  border: none;
  padding: 0 10px;
  box-sizing: border-box;
}

/* 공통 text 줄맞춤 **/
.text { /* ////////////////////////////////////////////////////////////////////////////////////////////// */
  display: flex;
  gap: 8px;
}

.text > div:nth-of-type(1), .text > div:nth-of-type(2) {
  font-family: 'Noto Sans';
  font-weight: 700;
  font-style: normal;
  line-height: 16px;
  display: flex;
  align-items: center;
  text-transform: uppercase;
}

.text > div:nth-of-type(1) {
  font-size: 12px;
  /* identical to box height, or 145% */
  color: #B5BAC1;
}

.text > div:nth-of-type(2) {
  font-size: 10px;
  /* identical to box height, or 160% */
  color: #F23F42;
}

.hrefText {
  /* Auto layout */
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 0;
  gap: 10px;
}

.hrefText > a:nth-of-type(1) {
  text-decoration: none;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  display: flex;
  align-items: center;
  text-align: center;

  color: #00A8FC;
}

.hrefText > div:nth-of-type(1) {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 16px;
  /* identical to box height, or 123% */
  display: flex;
  align-items: center;
  color: #949BA4;
}

/** 공통 text 줄맞춤 끝 **/


/** #Form 관련 css 끝**/
/** #OtherLogin에 관련된 css **/
#OtherLogin {
  display: flex;
  align-self: stretch;
  gap: 50px;
  justify-content: center;
}

#OtherLogin > div {
  width: 60px;
  height: 60px;
  border-radius: 15px;
  display: flex;
}

#OtherLogin > div > img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}

#Logo {
  display: flex;
  width: 230px;
  justify-content: space-evenly;
  align-items: center;
  flex-direction: column;
}

#Logo > div:nth-of-type(1) {
  display: flex;
  width: 170px;
  height: 170px;
  border-radius: 15px;
  overflow: hidden;
}
</style>