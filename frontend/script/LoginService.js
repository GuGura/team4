import axios from "axios";
import authHeader from "./authHeader";
import router from "./router";

import refreshHeader from "./refreshHeader";
import store from "./store";

class LoginService {
    async reissueRefreshJwt() {
        return await axios.post(process.env.VUE_APP_BASEURL_V1 + "/check-refreshToken", null, {headers: refreshHeader()})
            .then((res) => {
                const accessJwt = res.headers.get('accessJwt');
                const refreshJwt = res.headers.get('refreshJwt')
                const token = {accessJwt: accessJwt, refreshJwt: refreshJwt}
                localStorage.setItem(`token`, JSON.stringify(token)) //토큰 저장까지 확인
                store.commit('setAccessJwt', accessJwt);
                store.commit('setRefreshJwt', refreshJwt);
                return true;
            }).catch(() => {
                localStorage.removeItem('token');
                store.commit('setAccessJwt', null)
                store.commit('setRefreshJwt', null)
                return false;
            })
    }

    async initCheck() {
        return await axios.post(process.env.VUE_APP_BASEURL_V1 + "/check-token", null, {headers: authHeader()})
            .then(() => {
                return true;
            }).catch(() => {
                return this.reissueRefreshJwt()
            })
    }

    async initTokenCheck() {
        const result = await this.initCheck();
        if (result) {
            axios.post(process.env.VUE_APP_BASEURL_V1 + "/myInfo/init", null, {headers: authHeader()})
                .then(({data}) => {
                    store.commit('setUser', data.result)
                })
            router.push("/").then()
        } else {
            router.push("/login").then()
        }
    }

    async methodTokenCheck() {
        return await this.initCheck();
    }

}

export default new LoginService();