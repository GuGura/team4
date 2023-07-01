import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import authHeader from "./token/authHeader";
import api from "./token/axios";

//소켓에 연결하는 기능
export function connectSocket() {
    const serverURL = process.env.VUE_APP_BASEURL + '/ws';
    let socket = new SockJS(serverURL);
    const ws = Stomp.over(socket);
    console.log('serverURL: ' + serverURL);

    return new Promise((resolve, reject) => {
        ws.connect(
            {
                accessJwt: authHeader(),
            },
            frame => {
                console.log('소켓 연결 성공', frame);
                resolve(ws);
            },
            error => {
                console.log('소켓 연결 실패 ', error);
                reject(error);
            }
        );
    });
}

//구독하는 기능
let subscription;

export function subscribeToRoom(ws, roomId, sender, messageList) {
    return new Promise((resolve, reject) => {
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
            reject(new Error('구독에 실패했습니다.'));
        }
    });
}




//메세지 전송 기능
export function sendMessage(ws, roomId, sender, message) {
    const msg = {
        type: 'TALK',
        roomId: roomId,
        sender: sender,
        message: message,
        sendDate: new Date(),
    };
    ws.send('/pub/chat/message', JSON.stringify(msg), {});
}

//메세지 수신 기능
export function receiveMessage(recv, messageList) {
    if (recv.type !== 'ENTER') {
        // 'ENTER' 타입인 경우에는 출력하지 않음
        messageList.messages.push({
            type: recv.type,
            sender: recv.sender,
            message: recv.message,
            sendDate: recv.sendDate,
        });
    }
}

//채팅방 내역 조회
export function findRoomMessage(roomId) {
    return new Promise((resolve, reject) => {
        console.log("Start findRoomMessage");
        api.get(process.env.VUE_APP_BASEURL_V1 +`/chat/room/enter/${roomId}`)  // URL 수정
            .then((response) => {
                resolve(response.data);
                console.log("findRoomMessage Data is " + response.data);
            })
            .catch((error) => {
                reject(error);
            });
    });
}






