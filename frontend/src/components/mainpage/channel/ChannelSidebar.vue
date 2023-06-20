<script>

</script>

<template>
  <div id="side_contents">
    <div id="chatRooms_Header">Test</div>
    <div id="side_content_info">
      <div id="chatRooms">
        <div class="chatRoom">
          <div class="roomName">
            <div>room1</div>
          </div>
          <div class="btnRooms">
            <div class="btnRoom">
              <div>
                <img src="/img/channel/chat.png">
              </div>
              <div class="MyMember_Info">
                <div class="MyMember_Name">
                  회의록
                </div>
              </div>
            </div>
          </div>

          <div class="btnRooms">
            <div class="btnRoom">
              <div>
                <img src="/img/channel/chat.png">
              </div>
              <div class="MyMember_Info">
                <div class="MyMember_Name">
                  메모장
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="chatRoom">
          <div class="roomName">
            <div>room2</div>
          </div>

          <div class="btnRooms">
            <div class="btnRoom">
              <div>
                <img src="/img/channel/speak.png">
              </div>
              <div class="MyMember_Info">
                <div class="MyMember_Name">
                  음성채팅
                </div>
              </div>
            </div>
            <div>
              <div class="btnRoomMember">
                <div>
                  <img src="/img/channel/userIcon.png" >
                </div>
                <div class="MyMember_Info">
                  <div class="MyMember_Name">
                    박재연
                  </div>
                </div>
              </div>
              <div class="btnRoomMember">
                <div>
                  <img src="/img/channel/userIcon.png" >
                </div>
                <div class="MyMember_Info">
                  <div class="MyMember_Name">
                    박재연
                  </div>
                </div>
              </div>
              <div class="btnRoomMember">
                <div>
                  <img src="/img/channel/userIcon.png" >
                </div>
                <div class="MyMember_Info">
                  <div class="MyMember_Name">
                    박재연
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="btnRooms">
            <div class="btnRoom">
              <div>
                <img src="/img/channel/speak.png">
              </div>
              <div class="MyMember_Info">
                <div class="roomName">
                  예야
                </div>
              </div>
            </div>
            <div>
              <div class="btnRoomMember">
                <div>
                  <img src="/img/channel/userIcon.png" >
                </div>
                <div class="MyMember_Info">
                  <div class="MyMember_Name">
                    박재연
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
    <div id="side_content_mystatus">
      <div style="display: flex;gap: 15px;">
        <div id="MyIcon">
          <img src="/img/channel/userIcon.png" style="width: 40px;height: 40px;" alt="헬로">
        </div>
        <div id="MyInfo">
          <div id="MyName" >
            meatTeam<!-- 글자제한필요-->
          </div>
          <div id="MyStatus">
            meatTeam#1234
          </div>
        </div>
      </div>
      <div style="display: flex;">
        <div id="Mike">
          <img src="/img/channel/mike.png" alt="마이크">
        </div>
        <div id="headset">
          <img src="/img/channel/headset.png" alt="헤드셋">
        </div>
        <div id="btnHome">
          <img src="/img/channel/btnHome.png" alt="홈버튼">
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>


/**Add**/
#chatRooms_Header{
  display: flex;
  min-width: 240px;
  font-size: 18px;
  font-weight: bold;
  align-items: center;
  padding-left: 15px;
  background:#2B2D31;
  cursor: pointer;
  color: #fff;
  height: 50px;
  top: 0;
  left: 0;
  position: absolute;
  border-bottom: 1px solid #1F2123;
  border-radius: 10px 0 0 0;
}
#chatRooms_Header:hover{
  background:#36373D;
}

img{
  width: 100%;
}
/**  side_contents */
#side_contents{
  display: flex;
  flex-direction: column;
  min-width: 240px;
  background: #2b2d31;
  border-radius: 10px 0 0 0;
  z-index: 10;
  position: relative;
}
#side_content_info{
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 50px 10px;
  gap: 1px;
}

#chatRooms{
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.chatRoom{
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.roomName{
  display: flex;
}
.roomName > div:nth-of-type(1){
  color: #949BA4;
  font-size: 13px;
  font-weight: bold;
  margin-top: 10px;
  padding: 0 5px;
}
.roomName > div:nth-of-type(1):hover{
  color: #fff;
  cursor: pointer;
  display: flex;
}
.btnRoom{
  display: flex;
  height: 30px;
  gap: 20px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
}
.btnRoom:hover{
  background: #36373D;
}
.btnRoom:active{
  background: #3B3D44;
}
.btnRoom > div:nth-of-type(1){
  display: flex;
  color:#fff;
  width: 30px;
}
.btnRoom > div:nth-of-type(2){
  display: flex;
  font-size: 15px;
  color:#fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}
.btnRoom > div:nth-of-type(1) >img:nth-of-type(1){
  padding: 8px;
}
.btnRooms{
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.btnRooms > div:nth-of-type(2){
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 5px;
}
.btnRoomMember{
  display: flex;
  height: 30px;
  gap: 5px;
  border-radius: 5px;
  padding: 0px 15px;
  align-items: center;
  cursor: pointer;
  width: 90%;
}
.btnRoomMember:hover{
  background: #36373D;
}
.btnRoomMember:active{
  background: #3B3D44;
}
.btnRoomMember > div:nth-of-type(1){
  display: flex;
  color: #fff;
  width: 40px;
}
.btnRoomMember > div:nth-of-type(1) > img:nth-of-type(1){
  padding: 8px;
}
.MyMember_Info{
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

/** fixed */
#side_content_mystatus{
  display: flex;
  position: absolute;
  left: 0;
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

input[name=message]{
  display: flex;
  width: 70%;
  height: 70%;
  margin-left: 30px;
  padding-left: 15px;
  background: #383A40;
  outline: none;
  border: none;
  color: #fff;
  border-radius: 15px;
}
</style>