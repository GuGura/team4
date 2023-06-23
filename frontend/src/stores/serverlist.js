import {defineStore} from "pinia";
import {reactive} from "vue";
import api from "../../script/axios";

export const useServerListStore = defineStore("serverListStore", () => {

    const buttons = reactive([])

    let btnResult = reactive({
        isLobby: true,
    })

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
        btnResult,
        updateBtn
    }
})