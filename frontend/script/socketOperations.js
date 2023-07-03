import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

import api from "./token/axios";
import {defineStore} from "pinia";
import {computed, reactive, ref} from "vue";

//소켓에 연결하는 기능
export const useSocketStore = defineStore("socketStore", () => {

    let ws = reactive(null)
    let messageList = reactive([])
    let wsConnected = ref(false);  // WebSocket 연결 상태 추가

    const getter = computed(() => {
        console.log("getter-----------" ,messageList)
        return messageList;
    })
    //소켓 연결
    function connectSocket() {
        const serverURL = 'http://localhost:8090/ws';
        let socket = new SockJS(serverURL);
        console.log(socket);
        ws = Stomp.over(socket);
        console.log(ws);
        console.log('serverURL: ' + serverURL);
        ws.connect({}, connectSuccess, connectFail);
    }

    function connectSuccess(frame) {
        console.log("호출성공", frame)
        wsConnected = true
    }

    function connectFail() {
        alert("호출 실패")
        wsConnected = false
    }

    //구독 하는 기능
    let subscription;
    function subscribeToRoom(roomId, sender) {
        if (subscription) {
            console.log('구독 취소');
            subscription.unsubscribe();
        }
        subscription = ws.subscribe(
            '/sub/chat/room/' + roomId,
            message => {
                console.log('구독으로 받은 메시지입니다.', message.body);
                const recv = JSON.parse(message.body);
                receiveMessage(recv, messageList); // messageList 객체 전달
            }
        );
        if (ws && ws.connected) {
            const msg = {
                type: 'ENTER',
                roomId: roomId,
                sender: sender,
            };
        } else {
            Error('구독에 실패했습니다.');
        }
    }

    //메세지 보내기
    function sendMessage( roomId, sender, message) {
        const msg = {
            type: 'TALK',
            roomId: roomId,
            sender: sender,
            message: message,
            sendDate: new Date(),
        };
        ws.send('/pub/chat/message', JSON.stringify(msg), {});
    }

    //메세지 받기
    function receiveMessage(recv) {
        if (recv.type !== 'ENTER') {
            messageList.push(recv);
        }
    }

    //메세지 찾기
    async function findRoomMessage(roomId) {
        console.log("쓰이긴해?")
         return new Promise((resolve, reject) => {
            api.get( `/chat/room/enter/${roomId}`)  // URL 수정
                .then((response) => {
                    console.log("findRoomMessage 예야---------------")
                    resolve(response.data);
                })
                .catch((error) => {
                    reject(error);
                });
        });
    }

    return {
        ws,
        messageList,
        wsConnected,
        connectSocket,
        getter,
        connectFail,
        connectSuccess,
        subscribeToRoom,
        sendMessage,
        receiveMessage,
        findRoomMessage
    }
});






