import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

import api from "./token/axios";
import { defineStore } from "pinia";
import { computed, reactive, ref } from "vue";
import { useLobbyStore } from "./stores/lobby";

//소켓에 연결하는 기능
export const useSocketStore = defineStore("socketStore", () => {
    let ws = reactive(null);
    let wsConnected = ref(false); // WebSocket 연결 상태 추가
    const messageList = reactive([]);
    const onlineUsers = reactive([]);
    const offlineUsers = reactive([]);
    const lobbyStore = useLobbyStore();
    const updateUsername = computed(() => {
        return lobbyStore.user.username;
    });
    const updateUserEmail = computed(() => {
        return lobbyStore.user.email;
    });
    let subscription;

    //소켓 연결
    function connectSocket() {
        const serverURL = "http://localhost:8090/ws";
        let socket = new SockJS(serverURL);
        ws = Stomp.over(socket);
        const headers = {
            username: updateUserEmail.value,
        };
        ws.connect(headers, connectSuccess, connectFail);
    }

    function connectSuccess(frame) {
        console.log("호출성공", frame);
        wsConnected.value = true;
    }

    function connectFail() {
        alert("호출 실패");
        wsConnected.value = false;
    }

    //구독 하는 기능
    function subscribeToRoom(roomId) {
        subscription = ws.subscribe(
            "/sub/chat/room/" + roomId,
            (message) => {
                console.log("구독으로 받은 메시지입니다.", message.body);
                const recv = JSON.parse(message.body);
                receiveMessage(recv);
            }
        );
    }

    function unSubscribeToRoom(roomId) {
        if (subscription) {
            console.log("구독 취소");
            subscription.unsubscribe(roomId);
        }
    }

    //메세지 보내기
    function sendMessage(roomId, sender, message) {
        const msg = {
            type: "TALK",
            roomId: roomId,
            sender: sender,
            message: message,
            sendDate: new Date(),
        };
        ws.send("/pub/chat/message", JSON.stringify(msg), {});
    }

    //메세지 받기
    function receiveMessage(recv) {
        if (recv.type === "TALK") {
            messageList.push({
                type: recv.type,
                sender: recv.sender,
                message: recv.message,
                sendDate: recv.sendDate,
            });
        }
    }

    function clearMessageList() {
        messageList.splice(0, messageList.length); // 모든 메시지를 삭제합니다.
    }

    //메세지 찾기
    async function findRoomMessage(roomId) {
        try {
            const response = await api.get(`/chat/room/enter/${roomId}`);
            return response.data;
        } catch (error) {
            console.error(error);
            throw error;
        }
    }

    //유저리스트 확인
    async function UserList() {
        try {
            onlineUsers.splice(0);
            offlineUsers.splice(0);
            const response = await api.get(`/chat/room/list/UserList`);
            // console.log(`UserList -------------- ${JSON.stringify(response.data)}`);
            response.data.forEach((user) => {
                // console.log("updateChannelId.value:", localStorage.getItem('wschat.channel_id'));
                // console.log("user.channel_UID:", user.channelUID);
                if (user.message === "ENTER" && user.channelUID === localStorage.getItem('wschat.channel_id')) {
                    onlineUsers.push({
                        name: user.userName,
                    });
                } else if (user.message === "QUIT" && user.channelUID === localStorage.getItem('wschat.channel_id')) {
                    offlineUsers.push({
                        name: user.userName,
                    });
                }
            });
            return response.data;
        } catch (error) {
            console.error(error);
            throw error;
        }
    }

    return {
        ws,
        messageList,
        wsConnected,
        connectSocket,
        connectFail,
        connectSuccess,
        unSubscribeToRoom,
        UserList,
        subscribeToRoom,
        clearMessageList,
        sendMessage,
        receiveMessage,
        findRoomMessage,
        onlineUsers,
        offlineUsers,
    };
});
