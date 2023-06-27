<script>
import api from "../../../../script/token/axios";



export default {
    name: 'Profile',
    data: ()=>({
        profileForm: {
            imgPreview: '',
            nickName: '',
            user_description: '',
        }

    }),
    methods: {
        imgChange: function (e) {

            const img = e.target.files[0];
            const fileData = (data) => {
                this.profileForm.imgPreview = data
            }
            if (img instanceof Blob) {
                const reader = new FileReader()
                reader.readAsDataURL(img)
                reader.addEventListener("load", function () {
                    fileData(reader.result)
                }, false);

            }


        },
        uploadProfile:function (){
            api.post(process.env.VUE_APP_BASEURL + "/api/v1/home/updateProfile",this.profileForm
            ).then(()=>{
                alert("성공")
            })

        },



    }
}

</script>
<template>
        <fieldset>
            <div>
                <strong>닉네임</strong>
                <input type="text" name="name" id="nickName" v-model="profileForm.nickName">
            </div>
            <div>
                <strong>내 정보</strong>
                <br/>
                <textarea rows="5" cols="70" name="text" id="description" v-model="profileForm.user_description"></textarea>
                <br/>
                <div>
                    <button @click="uploadProfile">제출</button>
                </div>
            </div>
            <div>
                <img :src="profileForm.imgPreview" alt="image" id="icon">
            </div>
        </fieldset>

    <div style="padding:30px;">
        <input ref="image" class="file-input" type="file" tabindex="0" accept=".jpg,.jpeg,.png,.gif" @change="imgChange" multiple="multiple"/>
        <div>
        </div>
    </div>

</template>

<style>

</style>