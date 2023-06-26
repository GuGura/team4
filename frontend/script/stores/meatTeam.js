import {defineStore} from "pinia";
import {reactive} from "vue";

export const meatTeamStore = defineStore('meatteamStore',()=>{

    let channel = reactive({

    })


    return{
        channel
    }
})