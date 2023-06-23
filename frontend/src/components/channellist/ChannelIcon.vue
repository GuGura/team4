<script setup>
import {defineProps} from 'vue'



const props = defineProps({
  buttonData:Object
})


</script>

<template>
  <div class="server_Icon" >
    <div class="img"></div>
  </div>
</template>

<style scoped>
.server_Icon {
  width: 50px;
  height: 50px;
  background: #313338;
  border-radius: 50%;
  overflow: hidden;
}

.server_Icon:hover {
  border-radius: 30%;
}

div:not(:nth-last-of-type(1),:nth-last-of-type(2)):hover > div {
  background: #5865F2;
}

.img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
</style>