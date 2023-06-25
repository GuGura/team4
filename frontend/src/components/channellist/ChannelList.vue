<script setup>
import ChannelIcon from "@/components/channellist/ChannelIcon.vue";

import {onMounted} from "vue";
import {useServerListStore} from "../../../script/stores/serverlist";
import AddServerModel from "@/components/modal/AddChannelModel.vue";
import {useModalStore} from "../../../script/stores/modal";

const modalStore = useModalStore();
const serverListStore = useServerListStore();

onMounted(() => {
  serverListStore.updateBtn()
})
</script>

<template>
  <div id="serverList">
    <div class="box1">
      <form name="serverList">
        <ChannelIcon v-for="button in serverListStore.buttons" :key="button" :buttonData="button"/>
      </form>
    </div>
    <AddServerModel
        v-if="modalStore.modal.addServer"/>
  </div>
</template>

<style scoped>

/** #serverlist */
/** fixed */
#serverList {
  display: flex;
  flex-direction: column;
  position: relative;
  top: 0;
  left: 0;
  min-width: 74px;
  height: 100%;
  background: #1E1F22;
  z-index: 12;
}

img {
  width: 100%;
  height: 100%;
}

form[name=serverList] {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

#serverList > form[name=serverList] > div > img {
  width: 100%;
  height: 100%;
  box-sizing: inherit;
  padding: 12px;
  cursor: pointer;
}
.box1{
  padding: 10px 12px;
  width: 100%;
  height: 100%;
  position: absolute;
  overflow-y: scroll;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.box1::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>