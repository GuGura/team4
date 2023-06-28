import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

const socketUrl = process.env.VUE_APP_BASEURL+'/ws';

export const connectSocket = (roomId)