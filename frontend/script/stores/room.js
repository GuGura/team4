import {defineStore} from "pinia";
import {computed, reactive} from "vue";
import {useServerListStore} from "./serverlist";
import api from "../token/axios";

export const useRoomListStore = defineStore("roomListStore", () => {
    const serverListStore = useServerListStore();
    const updateChannelId = computed(() => {
        return serverListStore.getEndPoint;
    });

    const textChatRooms = reactive([]);
    const voiceChatRooms = reactive([]);

    async function findReactiveRoom() {
        voiceChatRooms.splice(0);
        textChatRooms.splice(0);

        const channel_id = updateChannelId.value;

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

    async function updateTextChatRooms(newRooms) {
        textChatRooms.splice(0, textChatRooms.length, ...newRooms);
    }

    async function updateVoiceChatRooms(newRooms) {
        voiceChatRooms.splice(0, voiceChatRooms.length, ...newRooms);
    }


    return {
        textChatRooms,
        voiceChatRooms,
        findReactiveRoom
    };
});
