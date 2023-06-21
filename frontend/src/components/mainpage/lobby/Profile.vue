<template>
    <form>
        <fieldset>
            <div>
                <strong>닉네임</strong>
                <input type="text" name="name" value="닉네임 입력">
            </div>
            <div>
                <strong>내 정보</strong>
                <label for="text"></label>
                <br/>
                <textarea rows="5" cols="70" name="text" id="text"></textarea>
                <br/>
                <div>
                    <input type="submit" value="제출">
                </div>
            </div>
        </fieldset>
    </form>

    <div style="padding:30px;">
        <input ref="image" type="file" accept="image/*" @change="fileChange" multiple="multiple"/>
        <input type="text" ref="imageName"/>
        <div v-if="image"
             class="w-full h-full flex items-center">
            <img :src="image" alt="image">
        </div>
        <div>

        </div>
        <div>
            <input type="submit" value="저장하기">
        </div>
    </div>

</template>
<script>
import axios from "axios";
import authHeader from "../../../../script/authHeader";
import loginService from "../../../../script/LoginService";
import router from "../../../../script/router";

export default {
    name: 'Profile',
    data: ()=>({
        image: '',

    }),
    methods: {
        fileChange: function (e) {
            let file = e.target.files[0];
            let form = new FormData()
            form.append('image',file)
            axios.post(process.env.VUE_APP_BASEURL_V1 + "/api/v1/home/changeProfileImage", form,
                {headers: authHeader()})
                .then(({data}) => {
                    console.log(data)
                })
                .catch(() => {
                    alert("이메일을 입력해주세요1")
                })
        }
    }
}

</script>
<style>

</style>