<script setup>
import {defineProps} from 'vue'


const props = defineProps({
  buttonData: Object
})


</script>

<template>
  <div class="server_Icon">
    <div class="colorBlue" style="width: 100%;height: 100%;text-align: center;" v-if="props.buttonData.channel_icon_url === null">
      <div class="title">{{ props.buttonData.channel_title }}</div>
    </div>
    <img class="img" v-else-if="props.buttonData.channel_title" :src="props.buttonData.channel_icon_url" alt=""
         :class=" {colorGreen : props.buttonData.channel_title === ('addServer' || 'public'),colorBlue:(props.buttonData.channel_title === 'lobby')}">
  </div>
</template>

<style scoped>
.server_Icon {
  width: 50px;
  height: 50px;
  background: #313338;
  border-radius: 50%;
  overflow: hidden;
  position: relative;
}

.server_Icon:hover {
  border-radius: 30%;
}

.colorBlue:hover {
  background: #5865F2;
}

.colorGreen:hover {
  background: #23A559;
}

.title {
  color: #fff;
  cursor: pointer;
  position: absolute;
  display: flex;
  width: 100%;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  font-size: 12px;
}

.img {
  width: 100%;
  height: 100%;
  box-sizing: inherit;
  padding: 12px;
  cursor: pointer;
}
</style>