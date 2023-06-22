import {defineStore} from "pinia";
import {computed, ref} from "vue";

//id 고유아이디
//state 상태 (현재코드에선 초기상태 0으로 리턴해줌)
export const useLobbyStore = defineStore("lobbyStore", () => {
    const count = ref(0);
    const name = ref("wodus331");
    const doubleCount = computed(() => count.value * 2); //getter

    function increment() {
        count.value++
    }
    return {count, name, doubleCount, increment}
});