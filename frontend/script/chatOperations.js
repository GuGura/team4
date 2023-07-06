import api from "/script/token/axios.js";
import router from "../script/routes/router";
import {computed, reactive} from 'vue';
import { useLobbyStore } from "../script/stores/lobby";
import { useServerListStore } from "../script/stores/serverlist";

const lobbyStore = useLobbyStore();
const serverListStore = useServerListStore();

const updateUsername = computed(() => {
    return lobbyStore.user.username
});
const updatechannel_id = computed(() => {
    return serverListStore.getEndPoint;
});

export async function findAllRoom(channel_id,textChatRooms, voiceChatRooms) {
    voiceChatRooms.splice(0);
    textChatRooms.splice(0);

    await api
        .get('/chat/rooms', {
            params: {
                channel_id: channel_id
            },
        })
        .then(({ data }) => {
            data.forEach(item => {
                if (item['roomType'] === true) voiceChatRooms.unshift(item);
                else if (item['roomType'] === false) textChatRooms.unshift(item);
            });
        })
        .catch(err => {
            console.log("err" + err);
        });
}

export function createRoom(channel_id, roomInfo) {
    console.log(roomInfo.name)
    if (!roomInfo || "" === roomInfo.name) {
        alert("방 제목을 입력해 주십시요.");
    } else if (updatechannel_id.value !== "lobby"){
        roomInfo.channel_id = channel_id;
        console.log(roomInfo.channel_id);
        return api.post(process.env.VUE_APP_BASEURL_V1 + '/chat/room', roomInfo)
            .then(({data}) => {
                console.log(data)
                roomInfo.roomId = data.roomId;
                if (data.roomType === false) textChatRooms.unshift(data)
                else if (data.roomType === true) voiceChatRooms.unshift(data)
                return data;
            })
            .catch(() => {
            });
    }
}


export function enterRoom(roomId, name) {
    let sender = updateUsername.value;
    let channel_id = updatechannel_id.value;
    let roomName = name || "Text Chatting Room"; // name이 undefined인 경우 기본 값을 설정

    localStorage.setItem('wschat.roomId', roomId);
    localStorage.setItem('wschat.channel_id', channel_id);
    localStorage.setItem('wschat.roomName', roomName);
    console.log("rrrrrrrrrrrrrr : " + localStorage.getItem('wschat.roomName'));


    router.push(`/channel/${channel_id}/chat/room/enter/${roomId}`);
}

export function roomName(){
    let userRoomName = reactive;
    userRoomName = localStorage.getItem('wschat.roomName');
}

