import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

const socketUrl = process.env.VUE_APP_BASEURL + '/ws';

let stompClient = null;
export const connectSocket = () => {
    return new Promise((resolve, reject) => {
        if (stompClient) {
            resolve(stompClient);
        } else {
            const socket = new SockJS(socketUrl);
            stompClient = Stomp.over(socket);

            stompClient.connect(
                {},
                (frame) => {
                    console.log('소켓 연결 성공', frame);
                    resolve(stompClient);
                },
                (error) => {
                    console.log('소켓 연결 실패', error);
                    reject(error);
                }
            );
        }
    });
};