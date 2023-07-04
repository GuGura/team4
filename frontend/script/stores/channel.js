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
        console.log(JSON.parse(localStorage.getItem('user')).username)
        console.log(channelInfo.channel_title)
    }

    const chatRoomInfo = reactive({

    })
    return{
        channelInfo,
        chatRoomInfo,
        init,
    }
})