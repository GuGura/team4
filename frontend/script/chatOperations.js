import api from "/script/token/axios.js";
import router from "../script/routes/router";
import { computed } from 'vue';
import { useLobbyStore } from "../script/stores/lobby";
import { useServerListStore } from "../script/stores/serverlist";

const lobbyStore = useLobbyStore();
const serverListStore = useServerListStore();

const updateUsername = computed(() => {
    return lobbyStore.user.username
});
const updateChannelId = computed(() => {
    return serverListStore.getEndPoint;
});


export async function findAllRoom(channel_id,textChatRooms, voiceChatRooms) {
    voiceChatRooms.splice(0);
    textChatRooms.splice(0);

    await api.get(`/chat/rooms/${channel_id}`)
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
    if (!roomInfo || "" === roomInfo.name) {
        alert("방 제목을 입력해 주십시요.");
    } else if (updateChannelId.value !== "lobby"){
        roomInfo.channel_id = channel_id;
        await api.post(process.env.VUE_APP_BASEURL_V1 + '/chat/room', roomInfo)
            .then(({data}) => {
                console.log(data)
                if (data.roomType === false) textChatRooms.push(data)
                else if (data.roomType === true) voiceChatRooms.push(data)
                roomInfo.name = '';
            })
            .catch(() => {
            });
    }
}
export function enterRoom(roomId) {
    let channel_id = updateChannelId.value
    router.push(`/channel/${channel_id}/chat/room/enter/${roomId}`)
}