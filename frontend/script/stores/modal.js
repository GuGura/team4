import {defineStore} from "pinia";
import {reactive} from "vue";

export const useModalStore = defineStore("modalStore",()=>{
    const modal = reactive({
        addServer : false,
        attendChannel: false,
        RoomModal: false,
    })

    function terminate(index){
        modal[index] =  false
    }
    function open(index){
        modal[index] = true
    }
    function openClose(index){
        modal[index]? terminate(index):open(index)
    }
    return{
        modal,
        open,
        terminate,
        openClose,
    }
})