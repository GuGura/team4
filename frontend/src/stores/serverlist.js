import {defineStore} from "pinia";
import {reactive} from "vue";

export const useServerListStore = defineStore("serverListStore", () => {

    const buttons = reactive([
        {id: 1, name: 'lobby', value: true},
        {id: 2, name: 'addServer', value: false},
        {id: 3, name: 'public', value: false},
    ])

    function activeBtn(btn) {
        for (const button of Object.values(buttons)) {
            button.value = (button.id === btn.id)
        }
        console.log("btn :" + btn);
    }

    return {
        buttons,
        activeBtn
    }
})