import {defineStore} from "pinia";
import {reactive} from "vue";

export const useModalStore = defineStore("modalStore",()=>{
    const modal = reactive({
        addServer : false,
        attendChannel: false,
        RoomModal: false,
    })

    function terminate(index){
        modal[index] =  !(index === index)
    }
    function open(index){
        modal[index] = (index === index)
    }
    return{
        modal,
        open,
        terminate,
    }
})