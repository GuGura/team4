<script setup>
import ServerIcon from "@/components/channellist/ChannelIcon.vue";

import {onMounted} from "vue";
import {useServerListStore} from "@/stores/serverlist";
import AddServerModel from "@/components/modal/AddChannelModel.vue";
import {useModalStore} from "@/stores/modal";

const modalStore = useModalStore();
const serverListStore = useServerListStore();


onMounted(() => {
  serverListStore.updateBtn()
})



</script>

<template>
  <div id="serverList">
    <form name="serverList">
      <ServerIcon v-for="button in serverListStore.buttons" :key="button" :buttonData="button"/>
    </form>
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
  position: fixed;
  min-width: 74px;
  height: 100%;
  padding: 10px 12px;
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

</style>