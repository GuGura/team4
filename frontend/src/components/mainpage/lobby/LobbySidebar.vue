<script setup>
import SidebarMyInfo from "@/components/sidebar/SidebarMyInfo.vue";
import {reactive, onMounted} from "vue";
 import notToken from "../../../../script/notTokenAxios";
import api from "../../../../script/token/axios";
const friends = reactive([]);
const friend_SENDER = 'jhon';
const props = reactive({
  type: 'friend',
})
//이코드를 작성했을 때 jhon이라는 friend_SENDER를 기준으로 친구 목록을 나타내기 위해 작성하였다.
//null 값을 함께 보내는 이유는 데이터를 전송하지 않고
// friend_SENDER라는  매개변수를 서버로 전달하기 위해서 작성함

const find = async()=> {
    try {
        const response = await
            api.post("/findFriend", null, {params: { friend_SENDER }})
            // notToken.post("/findFriend", {params: {friend_SENDER}})

        // axios.post(notToken+ "/findFriend", null, { params: {friend_SENDER}} )
        const data = response.data;
         friends.push(...data);
       console.log(data);
    }catch (error) {
        console.log(error);

    }
};

const decline = async() => {
      try {
        const response = await
           api.post("deleteFriend/", )
          const data = response.data;
        friends.push(...data);
        console.log(data);
      }catch (error) {
          console.log(error);
      }

    }






onMounted(()=> {
     find();
})

</script>

<template>
  <div id="side_contents">
    <div id="side_content_info">

      <label id="btn_DMList" class="btnList">
        <div style="width: 20px;">
          <img src="/img/sidebar/DM_icon.png" style="height: 20px;">
        </div>
        <div>DM</div>
          <input type="radio" v-model="props.type" value="DM" name="lobbySidebarType">
      </label>

      <label id="btn_FriendList" class="btnList" >
        <div style="width: 20px;">
          <img src="/img/sidebar/friend_icon.png" style="height: 20px;">
        </div>
        <div>친구</div>
        <input type="radio" name="lobbySidebarType" v-model="props.type" value="friend" checked>
      </label>

      <div id="searchBox">
        <input name="searchbox" placeholder="친구 찾기">
      </div>

      <div style="color: #fff;margin-top: 10px; padding: 0 5px;">리스트</div>
      <!----><!---->
<!--    반복문 시작     -->
        <div class="repeat" v-for="friend in friends" :key="friend.id">
        <a href=""></a>
      <div class="btnList">
        <div style="width: 35px;">
          <img src="/img/sidebar/userIcon.png">
        </div>
        <div class="MyMember_Info">
          <div class="MyMember_Name">
              {{ friend.friend_RECEIVER }}
          </div>
          <div class="MyMember_exit" @click="decline()">
            <img src="/img/sidebar/exit.png">
          </div>
        </div>
      </div>
        </div>

      <!---->
      <!---->
        <div v-for="friend in friends" :key="friend.id">
      <div class="btnList">
        <div style="width: 35px;">
          <img src="/img/sidebar/userIcon.png">
        </div>
        <div class="MyMember_Info">
          <div class="MyMember_Name">
              <label> 민화</label>
          </div>
          <div class="MyMember_exit">
            <img src="/img/sidebar/exit.png">
          </div>
        </div>
      </div>
        </div>
    </div>
    <SidebarMyInfo/>
  </div>
</template>

<style scoped>
#side_contents {
  display: flex;
  flex-direction: column;
  min-width: 240px;
  background: #2b2d31;
  border-radius: 10px 0 0 0;
  z-index: 10;
  position: relative;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none
}

#side_content_info {
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 20px 10px;
  gap: 1px;
}

form[name=list] {
  display: flex;
  flex-direction: column;
}

.btnList {
  display: flex;
  height: 45px;
  gap: 20px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
}

.btnList:hover {
  background: #36373D;
}

.btnList:active {
  background: #3B3D44;
}

.btnList > div:nth-of-type(1) {
  display: flex;
  color: #fff;
  width: 30px;
}

.btnList > div:nth-of-type(2) {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

#searchBox {
  display: flex;
}

#searchBox > input[name=searchbox] {
  display: flex;
  background-color: #1E1F22;
  outline: none;
  border-inline: none;
  border-bottom: none;
  color: #fff;
  padding: 0 5px;
  height: 35px;
  width: 100%;
  margin-top: 10px;
}

.MyMember_exit {
  display: flex;
  height: 8px;
  width: 8px;
}


img {
  width: 100%;
}
</style>