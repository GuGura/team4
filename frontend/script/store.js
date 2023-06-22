// stores/counter.js
import { defineStore } from 'pinia'

export const pinia = defineStore('counter', {
    state: () => {
        return { count: 0 }
    },
    // 다음과 같이 정의할 수도 있음:
    // state: () => ({ count: 0 })

    actions: {
        increment() {
            this.count++
        },
    },
})