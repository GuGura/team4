<script>
export default {
  name:'LobbySidebar'
}
</script>

<template>
  <div id="side_contents">
    <div id="side_content_info">
      <form name="list">
        <div id="btn_DMList" class="btnList">
          <div style="width: 20px;">
            <img src="/img/sidebar/DM_icon.png" style="height: 20px;">
          </div>
          <input type="checkbox" name="dm" hidden checked>
          <div>DM</div>
        </div>
        <div id="btn_FriendList" class="btnList">
          <div style="width: 20px;">
            <img src="/img/sidebar/friend_icon.png" style="height: 20px;">
          </div>
          <input type="checkbox" name="friend" hidden>
          <div>친구</div>
        </div>
      </form>

      <div id="searchBox">
        <input name="searchbox" placeholder="친구 찾기">
      </div>
      <div style="color: #fff;margin-top: 10px; padding: 0 5px;">리스트</div>
      <!---->
      <div class="btnList">
        <!---->
        <div style="width: 35px;">
          <img src="/img/sidebar/userIcon.png">
        </div>
        <div class="MyMember_Info">
          <div class="MyMember_Name">
            재연
          </div>
          <div class="MyMember_exit">
            <img src="/img/sidebar/exit.png">
          </div>
        </div>
        <!---->
      </div>
      <!---->
      <div class="btnList">
        <div style="width: 35px;">
          <img src="/img/sidebar/userIcon.png">
        </div>
        <div class="MyMember_Info">
          <div class="MyMember_Name">
            재연
          </div>
          <div class="MyMember_exit">
            <img src="/img/sidebar/exit.png">
          </div>
        </div>
      </div>

    </div>

    <div id="side_content_myStatus">
      <div style="display: flex;gap: 15px;">
        <div id="MyIcon">
          <img src="/img/sidebar/userIcon.png" style="height: 40px;" alt="헬로">
        </div>
        <div id="MyInfo">
          <div id="MyName">
            meatTeam<!-- 글자제한필요-->
          </div>
          <div id="MyStatus">
            meatTeam#1234
          </div>
        </div>
      </div>
      <div style="display: flex;">
        <div id="Mike">
          <img src="/img/sidebar/mike.png" alt="마이크">
        </div>
        <div id="headset">
          <img src="/img/sidebar/headset.png" alt="헤드셋">
        </div>
        <div id="btnHome">
          <img src="/img/sidebar/btnHome.png" alt="홈버튼">
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#side_contents{
  display: flex;
  flex-direction: column;
  min-width: 240px;
  background: #2b2d31;
  border-radius: 10px 0 0 0;
}
#side_content_info{
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 20px 10px;
  gap: 1px;
}
form[name=list]{
  display: flex;
  flex-direction: column;
}
.btnList{
  display: flex;
  height: 45px;
  gap: 20px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
}
.btnList:hover{
  background: #36373D;
}
.btnList:active{
  background: #3B3D44;
}
.btnList > div:nth-of-type(1){
  display: flex;
  color:#fff;
  width: 30px;
}
.btnList > div:nth-of-type(2){
  display: flex;
  font-size: 15px;
  color:#fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}
#searchBox{
  display: flex;
}
#searchBox > input[name=searchbox]{
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
.MyMember_exit{
  display: flex;
  height: 8px;
  width: 8px;
}

/** fixed */
#side_content_myStatus{
  display: flex;
  position: fixed;
  left: 74px;
  bottom: 0;
  height: 60px;
  min-width: 240px;
  background: #232428;
  align-items: center;
  gap: 5px;
  padding: 0 0 0 5px;
}
#MyInfo{
  display: flex;
  flex-direction: column;
  height: 40px;
  line-height: 1;
  color:#fff;
  justify-content: space-evenly;
}
#MyIcon{
  display: flex;
}

#MyName{
  font-weight: bold;
  font-size: 18px;
}
#MyStatus{
  font-size: 9px;
}
#Mike,#btnHome,#headset{
  display: flex;
  height: 30px;
  padding: 7px;
}
#Mike:hover,#btnHome:hover,#headset:hover{
  background: #383940;
}
img{
  width: 100%;
}
</style>