import {defineStore} from "pinia";
import {reactive, ref} from "vue";
import api from "../token/axios";

export const useServerListStore = defineStore("serverListStore", () => {

    const buttons = reactive([
        {
            channel_UID: 0,
            member_UID: 3,
            channel_title: "lobby",
            channel_icon_url: "/img/serverlist/discord_Icon.png",
            channel_type: 'lobby'
        },
    ])

    let btnResult = ref({
        endPoint: 'lobby',
        isActive: 'lobby',
    })

    async function initBtn() {
        await api.get(process.env.VUE_APP_BASEURL_V1 + "/myInfo/channelList")
            .then(({data}) => {
                const resultArray = data.result;
                resultArray.forEach(btn => {
                    buttons.push(btn)
                })
            })
    }

    async function updateBtn(btn) {
        this.buttons.splice(1, 0, btn[0])
    }

    return {
        buttons,
        btnResult,
        initBtn,
        updateBtn,
    }
})