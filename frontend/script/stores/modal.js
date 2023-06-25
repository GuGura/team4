import {defineStore} from "pinia";
import {reactive} from "vue";

export const useModalStore = defineStore("modalStore",()=>{
    const modal = reactive({
        addServer : false
    })

    function terminate(index){
        modal.addServer =  !(index === 'addServer')
    }
    function operate(index){
        modal.addServer =  (index === 'addServer')
    }

    return{
        modal,
        terminate,
        operate
    }
})