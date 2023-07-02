import {defineStore} from "pinia";
import {computed, reactive, ref} from "vue";
import api from "../token/axios";
import {useRouter} from "vue-router";

export const useServerListStore = defineStore("serverListStore", () => {

    const buttons = reactive([
        {
            channel_UID: 0,
            member_UID: 3,
            channel_title: "lobby",
            channel_icon_url: "/img/serverlist/discord_Icon.png",
            channel_type: 'lobby'
        },
    ])
    const router = useRouter();

    const getPathEndPoint = (computed(()=> {
        const path = router.currentRoute.value.path
        let triumphant = path.substring('/channel/'.length);
        if (!(triumphant === 'lobby'|| triumphant === 'public' || triumphant === 'addServer'))
            triumphant = Number(triumphant);
        return triumphant;
    } ))

    const getEndPoint = computed(() => {
        const pathSegments = router.currentRoute.value.path.split('/');
        const endPoint = pathSegments[2]; // 첫 번째 경로 세그먼트 추출
        return endPoint;
    });


    let btnResult = ref({
        endPoint: 'lobby',
    })

    async function initBtn() {
        await api.get("/myInfo/channelList")
            .then(({data}) => {
                const resultArray = data.result;
                resultArray.forEach(btn => {
                    buttons.push(btn)
                })
            })
    }

    async function updateBtn(btn) {
        this.buttons.splice(1, 0, btn)
    }

    return {
        buttons,
        btnResult,
        getPathEndPoint,
        getEndPoint,
        router,
        initBtn,
        updateBtn,
    }
})