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
    let prevData = [];

    async function UserList() {
        try {
            const response = await api.get(`/chat/room/list/UserList`);
            const currentData = response.data.filter(user =>
                user.channelUID === localStorage.getItem('wschat.channel_id') &&
                (user.message === "ENTER" || user.message === "QUIT")
            );

            // Find new online users
            const newOnlineUsers = currentData.filter(user =>
                !prevData.some(prevUser =>
                    prevUser.userName === user.userName && prevUser.message === user.message
                ) && user.message === "ENTER"
            );

            // Add new online users to onlineUsers and remove from offlineUsers if present
            newOnlineUsers.forEach(user => {
                onlineUsers.push({ name: user.userName });

                // If user was previously offline, remove from offlineUsers
                const indexOffline = offlineUsers.findIndex(offlineUser => offlineUser.name === user.userName);
                if (indexOffline !== -1) {
                    offlineUsers.splice(indexOffline, 1);
                }
            });

            // Find users who quit
            const quitUsers = currentData.filter(user => user.message === "QUIT");

            // Process quit users
            quitUsers.forEach(user => {
                // If user was previously online, remove from onlineUsers
                const indexOnline = onlineUsers.findIndex(onlineUser => onlineUser.name === user.userName);
                if (indexOnline !== -1) {
                    onlineUsers.splice(indexOnline, 1);
                }

                // Check if user is already marked as offline, if not, add to offlineUsers
                const indexOffline = offlineUsers.findIndex(offlineUser => offlineUser.name === user.userName);
                if (indexOffline === -1) {
                    offlineUsers.push({ name: user.userName });
                }
            });


            // Find users who were in the previous data but are no longer present
            const missingUsers = prevData.filter(prevUser =>
                !currentData.some(user =>
                    user.userName === prevUser.userName
                )
            );

            // Remove missing users from both lists
            missingUsers.forEach(user => {
                const indexOnline = onlineUsers.findIndex(onlineUser => onlineUser.name === user.userName);
                if (indexOnline !== -1) {
                    onlineUsers.splice(indexOnline, 1);
                }

                const indexOffline = offlineUsers.findIndex(offlineUser => offlineUser.name === user.userName);
                if (indexOffline !== -1) {
                    offlineUsers.splice(indexOffline, 1);
                }
            });

            // Store the current data as the previous data for the next comparison
            prevData = [...currentData];

            return response.data;
        } catch (error) {
            console.error(error);
            throw error;
        }
    }

    setInterval(UserList, 3000);






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
