<script setup>
const props = defineProps( {
      channel_title: String
});
</script>

<template>
  <div id="chatRooms_Header" @click="modalStore.open('RoomModal')">
    <div style="font-size: 18px;font-weight: bold; color: #fff;">
      {{ props. channel_title }}
    </div>
    <div style="display: flex; height: 25px">
      <img src="/img/sidebar/down.png" style="width: 100%;height: 100%">
    </div>
  </div>
</template>

<style scoped>
#chatRooms_Header {
  display: flex;
  min-width: 240px;
  align-items: center;
  padding: 0 15px;
  cursor: pointer;
  height: 50px;
  top: 0;
  left: 0;
  position: absolute;
  border-bottom: 1px solid #1F2123;
  border-radius: 10px 0 0 0;
  justify-content: space-between;
}

#chatRooms_Header:hover {
  background: #36373D;
}

img {
  width: 100%;
  height: 100%;
}
</style>