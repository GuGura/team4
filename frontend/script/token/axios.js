import axios from "axios";
import refreshHeader from "./refreshHeader";
import notToken from "../notTokenAxios";

const api = axios.create({
    baseURL: 'http://localhost:8090/api/v1'
});

api.interceptors.request.use(
    function (config) {
        const token = JSON.parse(localStorage.getItem('token'))
        if (!token.accessJwt) {
            console.log("토큰이 없습니다.")
        }
    //    console.log("1: " + token.accessJwt)
        if (config.headers && token.accessJwt) {
            config.headers.accessJwt = token.accessJwt
        }

    //    console.log("request start", config);
        return config;
    },
    function (error) {
    //    console.log("request error", error);
        return Promise.reject(error)
    }
)
api.interceptors.response.use(
    function (response) {
      //  console.log("get response", response);
        return response;
    },
    async (error) => {
        console.log("Error object:", JSON.stringify(error));
        const {
            config,
            response: {status}
        } = error;
        if (status === 401) {
            if (error.response.data.message === "TokenExpired") {
                const originalRequest = config;
         //       console.log(originalRequest)
                await notToken.post( "/api/v1/check-refreshToken", null, {headers: refreshHeader()})
                    .then((res) => {
                        const accessJwt = res.headers.get('accessJwt');
                        const refreshJwt = res.headers.get('refreshJwt')
             //           console.log(accessJwt)
             //           console.log(refreshJwt)
                        const token = {accessJwt: accessJwt, refreshJwt: refreshJwt}
                        localStorage.setItem(`token`, JSON.stringify(token)) //토큰 저장까지 확인
                    })
                    .catch(() => {
                        localStorage.removeItem('token');
                    })
                return api(originalRequest);
            }
        }
    //    console.log("response error", error);
        return Promise.reject(error);
    }
)

export default api