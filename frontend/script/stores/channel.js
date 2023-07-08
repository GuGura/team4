import {defineStore} from "pinia";
import {reactive} from "vue";

export const useChannelStore = defineStore('channelStore',()=>{

    const channelInfo = reactive({
        channel_title: localStorage.getItem('selectChannel'),
        channel_invite_code: localStorage.getItem('inviteCode'),
        channel_member:[]
    })

    function init(){
        channelInfo.channel_title = localStorage.getItem('selectChannel')
        channelInfo.channel_invite_code = localStorage.getItem('inviteCode')
    }

    const chatRoomInfo = reactive({

    })
    return{
        channelInfo,
        chatRoomInfo,
        init,
    }
})