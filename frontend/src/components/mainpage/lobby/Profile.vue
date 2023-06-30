

<script setup>
import {useLobbyStore} from "../../../../script/stores/lobby";
import {onMounted, reactive} from "vue";
import api from "../../../../script/token/axios";
import router from "../../../../script/routes/router";

onMounted(() => {

})


const lobbyStore = useLobbyStore();

let profileForm = reactive({
    username: lobbyStore.user.username,
    user_icon_url: lobbyStore.user.user_icon_url,
    user_description: lobbyStore.user.user_description
})


function imgChange(e) {
    const img = e.target.files[0];
    const fileData = (data) => {
        profileForm.user_icon_url = data
        document.getElementById("icon").setAttribute('src', data)
    }
    if (img instanceof Blob) {
        const reader = new FileReader()
        reader.readAsDataURL(img)
        reader.addEventListener("load", function () {
            fileData(reader.result)
        }, false);

    }
}

function uploadProfile() {
    alert(profileForm.user_description)
    api.post(process.env.VUE_APP_BASEURL_V1 + "/profile/updateProfile", profileForm
    ).then(() => {
        router.go(0)
    })
}


</script>



<template>
    <div id="box">
        <div id="background"></div>
        <div id="modal">
            <div>
                <strong>닉네임</strong>
                <input type="text" id="nickName" v-model="profileForm.username">
            </div>
            <div>
                <strong>내 정보</strong>
                <br/>
                <textarea rows="5" cols="70" name="text" id="description"
                          v-model="profileForm.user_description"></textarea>
                <br/>
                <div>
                    <button @click="uploadProfile()">제출</button>
                </div>
            </div>
            <div style="padding:30px;">
                <input ref="image" class="file-input" type="file" tabindex="0" accept=".jpg,.jpeg,.png,.gif"
                       @change="imgChange"
                       multiple="multiple"/>
                <div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <img alt="image" id="icon" :src="profileForm.user_icon_url">
    </div>

</template>

<style scoped>
#box{
    position: relative;
    width: 100%;
    height: 100%;
    display: flex;
}
#background {
    z-index: 1000;
    position: fixed;
    width: 100%;
    height: 100%;
    background: black;
    filter: opacity(0.5);

}

#modal {
    position: fixed;
    background-color: #fff;
    left: 0;
    top: 0;
    display: flex;
    width: 1000px;
    height: 300px;
    border: 1px solid red;
    z-index: 12;
}
</style>