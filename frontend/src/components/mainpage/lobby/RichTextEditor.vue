<script setup>

import {useLobbyStore} from "../../../../script/stores/lobby";
import {reactive} from "vue";
import api from "../../../../script/token/axios";
import router from "../../../../script/routes/router";

const lobbyStore = useLobbyStore();


let contentsForm = reactive({
    username: lobbyStore.user.username,
    writtenDate: new Date(),
    title: "",
    context: '',
    contentUID: '',
    contentIMG: '',
    isImgIn : false
})

function fnAddProc() {
    if(!contentsForm.title) {
        alert("제목을 입력해 주세요");
        return;
    }
    if(!contentsForm.context) {
        alert("내용을 입력해 주세요");
        return;
    }
    contentsForm.writtenDate = new Date()
    if(!contentsForm.contentIMG) {
        contentsForm.isImgIn = false
    }else {
        contentsForm.isImgIn = true
    }
    api.post(process.env.VUE_APP_BASEURL_V1 + "/content/saveContent",contentsForm
    ).then(()=>{
        router.go(0)
    })

}

function imgChange(e){
    const img = e.target.files[0];
    const fileData = (data) => {
        contentsForm.contentIMG = data
        document.getElementById("icon").setAttribute('src',data)
    }
    if (img instanceof Blob) {
        const reader = new FileReader()
        reader.readAsDataURL(img)
        reader.addEventListener("load", function () {
            fileData(reader.result)
        }, false);

    }

}


</script>
<template>
    <div>
        <h1>게시글 작성</h1>

        <div class="AddWrap">
            <form>
                <table class="tbAdd">
                    <colgroup>
                        <col width="15%" />
                        <col width="*" />
                    </colgroup>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" v-model="contentsForm.title" ref="subject" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea v-model="contentsForm.context" ref="cont"></textarea></td>
                    </tr>

                </table>
            </form>
        </div>
        <div>
            <input ref="image" class="file-input" type="file" tabindex="0" accept=".jpg,.jpeg,.png,.gif" @change="imgChange" multiple="multiple"/>
    </div>
    </div>
    <div>
        <img alt="image" id="icon" :src="contentsForm.contentIMG">
    </div>
        <div class="btnWrap">
            <div href="javascript:;" @click="fnAddProc" class="btnAdd btn">등록</div>
        </div>
</template>


<style scoped>
.h1, h1 {font-size: 2rem; text-align: center;}
.tbAdd {border-top:1px solid #888; width: 100%;}
.tbAdd th, .tbAdd td {border-bottom:1px solid #eee; padding:5px 0;}
.tbAdd td {padding:10px 10px; box-sizing:border-box;}
.tbAdd td input {width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbAdd td textarea {width:100%; min-height:300px; padding:10px; box-sizing:border-box;}
.btnWrap {text-align:center; margin:20px 0 0 0;}
.btnWrap a {margin:0 10px;}
.btn {background: lightgray; color: #000;}
.btnWrap .btn {margin: 0 5px;}
</style>
