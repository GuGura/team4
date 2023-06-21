<script>
import axios from "axios";
import authHeader from "../../../../script/authHeader";



export default {
    name: 'Profile',
    data: ()=>({
        imgPreview: '',
        nickName: '',
        user_description: '',
        imgTest: '',

    }),
    methods: {
        imgChange: function (e) {
            const img = e.target.files[0];
            const fileData = (data) => {
                this.imgPreview = data
            }
            const reader = new FileReader()
            reader.readAsDataURL(img)
            reader.addEventListener("load", function () {
                fileData(reader.result)
            }, false);
        },
        uploadProfile:function (){
            let data = {
                nickName: this.nickName,
                user_description: this.user_description
            }
            axios.put(process.env.VUE_APP_BASEURL + "/api/v1/home/updateProfile",data,{headers:authHeader()}
            ).then(()=>{
                alert("저장 완료")
            })

        },
        uploadIcon: function (){
            axios.post(process.env.VUE_APP_BASEURL + "/api/v1/home/updateIcon",this.imgPreview,{headers:authHeader()}
            ).then(()=>{

            })
        },


    }
}

</script>
<template>
        <fieldset>
            <div>
                <strong>닉네임</strong>
                <input type="text" name="name" id="nickName" v-model="nickName">
            </div>
            <div>
                <strong>내 정보</strong>
                <br/>
                <textarea rows="5" cols="70" name="text" id="description" v-model="user_description"></textarea>
                <br/>
                <div>
                    <button @click="uploadProfile">제출</button>
                </div>
            </div>
            <div>
                <img :src="imgPreview" alt="image" id="icon">
            </div>
            <div>
                <img :src="imgTest" alt="image" id="icon">
            </div>
        </fieldset>

    <div style="padding:30px;">
        <input ref="image" class="file-input" type="file" tabindex="0" accept=".jpg,.jpeg,.png,.gif" @change="imgChange" multiple="multiple"/>
        <div>
        </div>
        <div>
            <button @click="uploadIcon">저장</button>
        </div>
    </div>

</template>

<style>

</style>