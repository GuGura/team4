<template>
  <div id="body" :style="{backgroundImage: `url(/img/sign/LoginBackground.png)`}">
    <div id="container">
      <div id="content">
        <div id="header">
          <div id="headerText1">계정 만들기</div>
        </div>
        <form @submit.prevent="SignUp">
          <div class="inputBox">
            <div class="text">
              <div id="idInfo">이메일</div>
              <div></div>
            </div>
            <div style="display: flex;flex-direction: column;gap: 10px;width: 100%;">
              <div style="display: flex;gap: 20px;width: 95%;">
                <input class="textBox" type="email" name="email" placeholder="example@gmail.com"
                       v-model="isInfoTrue.form.isEmail" :readonly="joinInfo.email" required>
                <input id="btnCheck" type="button" value="인증하기" @click="AuthenticatedEmail()"
                       :disabled="joinInfo.email">
              </div>
              <div>
                <input class="textBox" type="text" name="auth" placeholder="인증번호 8자리를 입력해주세요" required
                       @blur="checkMail()"
                       :disabled="!joinInfo.email||isInfoTrue.isSame === 'T'"
                       v-model="isInfoTrue.form.isEmailAuthCode">
                <div style="font-size: 14px;color: green" v-if="isInfoTrue.isSame === 'T'">인증번호가 일치합니다.</div>
                <div style="font-size: 14px;color:red" v-else-if="isInfoTrue.isSame === 'F'">인증번호가 불일치 합니다. 다시
                  확인해주세요!.
                </div>
              </div>
            </div>
          </div>
          <TextBox subject="사용자명"
                   notice="- 4글자 이상"
                   notice-color="color: #B5BAB8"
                   box-type="text"
                   box-name="userId"
                   pattern="\w{4,}"
                   v-model="joinInfo.username"/>
          <TextBox subject="비밀번호"
                   notice="- 10글자 이상"
                   notice-color="color: #B5BAB8"
                   box-type="password"
                   box-name="passwd"
                   pattern="\w{10,}"
                   v-model="joinInfo.password"/>
          <SubmitBtn text="회원가입"/>
          <div class="hrefText">
            <a href="#" onclick="history.back();">이미 계정이 있으신가요?</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import axios from "axios";
import router from "../../script/router";
import SubmitBtn from "@/components/sign/SubmitBtn.vue";
import TextBox from "@/components/sign/TextBox.vue";
import loginService from "../../script/LoginService";

const joinInfo = reactive({
  email: null,
  emailAuthCode: null,
  username: null,
  password: null
});
const isInfoTrue = reactive({
  form: {
    isEmail: '',
    isEmailAuthCode: null,
  },
  isSame: 'null'
});
const AuthenticatedEmail = () => {
  console.log(isInfoTrue.form.isEmail)
  if (isInfoTrue.form.isEmail === '') {
    alert("이메일을 입력해주세요")
    return;
  }

  axios.post(process.env.VUE_APP_BASEURL_V1 + "/join/sendMail", isInfoTrue.form)
      .then(({data}) => {
        console.log("sendMail: " + JSON.stringify(data))
        if (data.status) {
          alert(data.message)
          joinInfo.email = data.result.email
          joinInfo.emailAuthCode = data.result.auth
        } else {
          alert(data.message)
        }
      })
      .catch(() => {
        alert("이메일을 입력해주세요1")
      })
}
const checkMail = () => {
  if (isInfoTrue.form.isEmailAuthCode === null) {
    return
  }
  axios.post(process.env.VUE_APP_BASEURL_V1 + "/join/checkMail", isInfoTrue.form)
      .then(({data}) => {
        console.log(data)
        if (data) {
          isInfoTrue.isSame = 'T'
          joinInfo.emailAuthCode = isInfoTrue.form.isEmailAuthCode
        } else {
          isInfoTrue.isSame = 'F'
        }
      })
      .catch(() => {
        alert("인증번호를 입력해주세요")
      })
}

const SignUp = () => {
  axios.put(process.env.VUE_APP_BASEURL_V1 + "/join/signUp", joinInfo)
      .then(({data}) => {
        if (data.status) {
          alert(data.message)
          router.push({name: 'Login'})
        } else {
          alert(data.message)
        }
      })
      .catch((err) => {
        console.log(err)
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

#header {
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

#headerText1 {
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

/** #header 관련 css 끝 **/
/** #Form 관련 css **/
Form {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0;
  gap: 10px;
}

.inputBox {
  /* Auto layout */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0;
  gap: 8px;

  width: 100%;
}

.textBox {
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
.text {
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