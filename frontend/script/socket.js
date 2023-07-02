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
        return messageList;
    })
    function connectSocket() {
        const serverURL = process.env.VUE_APP_BASEURL + '/ws';
        let socket = new SockJS(serverURL);
        console.log(socket)
        ws = Stomp.over(socket);
        console.log(ws)
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
    function subscribeToRoom(roomId, sender) {
        ws.subscribe(
            '/sub/chat/room/' + roomId,
            message => {
                console.log('구독으로 받은 메시지입니다.', message.body);
                const recv = JSON.parse(message.body);
                receiveMessage(recv);
            },
            {id: 'room-subscription'}
        );
        if (ws && ws.connected) {
            console.log('Start ws.connect Channel.vue ');
            const msg = {
                type: 'ENTER',
                roomId: roomId,
                sender: sender,
            };
            console.log("ddddddddddddddddddddddddddddddddddddddddd", msg)
            ws.send('/pub/chat/message', JSON.stringify(msg), {});
        } else {
            throw new Error('소켓 연결이 실패했습니다.');
        }

    }

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


    function receiveMessage(recv) {
        console.log(recv)
        if (recv.type !== 'ENTER') {
            messageList.push(recv);
        }
    }


    function findRoomMessage(roomId) {
        console.log('-------------------------------Start findRoomMessage---------------------', roomId);
        api.get(process.env.VUE_APP_BASEURL_V1 + `/chat/room/enter/${roomId}`)
            .then(res => {
                console.log('---------------------------------------------findRoomMessage Data is : ', res);
                return res.data;
            })
            .catch(err => {
                throw err
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






