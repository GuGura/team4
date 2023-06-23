<script setup>
import ServerIcon from "@/components/channellist/ChannelIcon.vue";
import AddServerModel from "@/components/channellist/AddChannelModel.vue";
import {onMounted, reactive} from "vue";
import {useServerListStore} from "@/stores/serverlist";

const store = useServerListStore();

onMounted(()=>{
  store.updateBtn()
})
// function choiceServer(index) {
//   if (index === 'addServer') {
//     this.status.addServer = true
//   }
//   console.log(index)
// }

function addServer(event) {
  this.status.addServer = event;
}

const status = reactive({
  lobby: true,
  addServer: false,
  publicServer: false,
  myServerList: []
})

</script>

<template>
  <div id="serverList">
    <form name="serverList">
      <ServerIcon v-for="button in store.buttons" :key="button" :buttonData="button"/>
    </form>
    <AddServerModel
        :is-model-active="status.addServer"
        @update:isModelActive="addServer(event)"
    />

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