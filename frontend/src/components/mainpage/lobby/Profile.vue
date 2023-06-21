<script>
import axios from "axios";
import authHeader from "../../../../script/authHeader";
import {reactive} from "vue";

let dataForm = reactive({
    fileURL: '',
})

export default {
    name: 'Profile',
    data: ()=>({
        image: '',

    }),
    methods: {
        fileChange: function (e) {
            const img = e.target.files[0];
            if (img instanceof Blob) {
                const fileReader = new FileReader();
                fileReader.readAsDataURL(img)
                fileReader.onload = function (e) {
                    dataForm.fileURL = e.target.result;
                }
            }
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
        <div :style="{backgroundImage: `url(${dataForm.fileURL})`}"
             class="w-full h-full flex items-center">

        </div>
        <div>

        </div>
        <div>
            <input type="submit" value="저장하기">
        </div>
    </div>

</template>

<style>

</style>