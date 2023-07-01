import api from "/script/token/axios.js";
import router from "../../../../script/routes/router";
import { computed } from 'vue';
import { useLobbyStore } from "../../../../script/stores/lobby";
import { useServerListStore } from "../../../../script/stores/serverlist";

const lobbyStore = useLobbyStore();
const serverListStore = useServerListStore();

const updateUsername = computed(() => {
    return lobbyStore.user.username
});
const updatechannel_id = computed(() => {
    return serverListStore.getEndPoint;
});

export async function findAllRoom(textChatRooms, voiceChatRooms) {
    voiceChatRooms.splice(0);
    textChatRooms.splice(0);

    await api
        .get(process.env.VUE_APP_BASEURL_V1 + '/chat/rooms', {
            params: {
                channel_id: channel_id
            },
        })
        .then(({ data }) => {
            data.forEach(item => {
                if (item['roomType'] === true) voiceChatRooms.push(item);
                else if (item['roomType'] === false) textChatRooms.push(item);
            });
        })
        .catch(err => {
            console.log("err" + err);
        });
}

export async function createRoom(channel_id, roomInfo, textChatRooms, voiceChatRooms) {
    if ("" === roomInfo.name) {
        alert("방 제목을 입력해 주십시요.");
    } else if (updatechannel_id.value !== "lobby"){
        await api.post(process.env.VUE_APP_BASEURL_V1 + '/chat/room', roomInfo)
            .then(({data}) => {
                console.log(data)
                if (data.roomType === false) textChatRooms.push(data)
                else if (data.roomType === true) voiceChatRooms.push(data)
                roomInfo.name = '';
                roomInfo.channel_id = channel_id;
            })
            .catch(() => {
            });
    }
}

export function enterRoom(roomId) {
    console.log("Start EnterRoom in ChannelSideBar.vue")
    let sender = updateUsername.value
    let channel_id = channel_id
    localStorage.setItem('wschat.roomId', roomId);
    localStorage.setItem('wschat.channel_id', channel_id);

    router.push(`/channel/${channel_id}/chat/room/enter/${roomId}`);
}