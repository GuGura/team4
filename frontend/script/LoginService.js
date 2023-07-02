import axios from "axios";
import authHeader from "./token/authHeader";
import router from "./routes/router";

import refreshHeader from "./token/refreshHeader";
import api from "./token/axios";

class LoginService {
    async reissueRefreshJwt() {
        return await api.post("/check-refreshToken", null, {headers: refreshHeader()})
            .then((res) => {
                const accessJwt = res.headers.get('accessJwt');
                const refreshJwt = res.headers.get('refreshJwt')
                const token = {accessJwt: accessJwt, refreshJwt: refreshJwt}
                localStorage.setItem(`token`, JSON.stringify(token)) //토큰 저장까지 확인
                return true;
            }).catch(() => {
                localStorage.removeItem('token');
                return false;
            })
    }

    async initCheck() {
        return await api.post("/check-token", null, {headers: authHeader()})
            .then(() => {
                return true;
            }).catch(() => {
                return this.reissueRefreshJwt()
            })
    }

    async initTokenCheck() {
        const result = await this.initCheck();
        if (result) {
            api.post("/myInfo/init", null, {headers: authHeader()})
                .then(({data}) => {
                    console.log(data)
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