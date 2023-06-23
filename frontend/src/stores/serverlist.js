import {defineStore} from "pinia";
import {reactive} from "vue";
import api from "../../script/axios";

export const useServerListStore = defineStore("serverListStore", () => {

    const buttons = reactive([])

    function updateBtn() {
        api.get(process.env.VUE_APP_BASEURL_V1 + "/myInfo/channelList")
            .then(({data}) => {
                const resultArray = data.result;
                resultArray.forEach(btn =>{
                    buttons.push(btn)
                })
            })
    }

    return {
        buttons,
        updateBtn
    }
})