<script setup>
import {defineProps} from 'vue'
const props = defineProps({
  name: String
})
</script>

<template>
  <div class="chatSidebarInfo">
    <div>
      <img src="/img/channel/userIcon.png">
    </div>
    <div class="MyMember_Info">
      <div class="MyMember_Name1">
        {{props.name}}
      </div>
    </div>
  </div>
</template>

<style scoped>
.chatSidebarInfo {
  display: flex;
  height: 45px;
  gap: 10px;
  border-radius: 5px;
  margin: 0px 15px;
  align-items: center;
  cursor: pointer;
}

.chatSidebarInfo:hover {
  background: #36373D;
}

.chatSidebarInfo:active {
  background: #3B3D44;
}

.chatSidebarInfo > div:nth-of-type(1) {
  display: flex;
  color: #fff;
  width: 40px;
}
.MyMember_Info {
  display: flex;
  font-size: 15px;
  color: #fff;
  flex: 1;
  justify-content: space-between;
  align-items: center;
}

.MyMember_exit {
  display: flex;
  height: 8px;
  width: 8px;
}

.chatSidebarInfo > div:nth-of-type(1) > img:nth-of-type(1) {
  padding: 2px;
}

</style>