<template>
    <div class="card mb-3">
        <div v-if="props.post.visible" class="card-body" :id="props.post.id">
            <div class="row">
                <div class="col-4" style="width: 120px;" v-if="props.post.userIcon === 'data:image/png;base64,null'">
                    <img class="iconIMG" src="/img/serverlist/user_icon.png">
                </div>
                <div class="col-4" style="width: 120px;" v-else>
                    <img class="iconIMG"  :src="props.post.userIcon" >
                </div>
                <div class="col-8">
                    <h4 class="card-subtitle mb-2">{{ props.post.title }}</h4>
                    <h6 class="card-title">{{ props.post.username }}</h6>
                    <p class="card-text"><small class="card-text fw-lighter">{{ props.post.uploadDate }}</small></p>
                </div>
                <div class="btnDelete col-1" >
                </div>
                <div class="btnDelete col-1" @click="getFriendPost">
                    <span class="material-symbols-outlined">content_copy</span>
                </div>

            </div>
            <div v-if="props.post.isImgIn"><img alt="Post Image" :src="'data:image/png;base64,'+props.post.contentIMG"
                                                class="img-fluid"></div>
            <p class="card-text contents fw-light">{{ props.post.content }}</p>
        </div>
        <div v-else>
            <p class="card-text contents fw-light">포스트가 존재하지않습니다.</p>
        </div>
    </div>
</template>

<script setup>

import {defineProps} from "vue";
import api from "../../../../script/token/axios";

const props = defineProps({
    post: Object,
})

function viewCode(){
    // eslint-disable-next-line no-undef
    Swal.fire(props.post.sharingCode);
}

function getFriendPost(){
    Swal.fire({
        title: `포스트를 스크랩하시겠습니까?`,
        confirmButtonText: "스크랩",
    }).then((result) => {
        if (result.isConfirmed) {
            api.post("/content/getFriendPost/", {id:props.post.id}
            ).then(
                Swal.fire("공유 완료!")
            )
        }
    })



}

</script>

<style scoped>
.content {
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
}

.card-img-top {
    width: 100%;
    height: 15vw;
    object-fit: cover;
}

.btnDelete {
    display: flex;
}

.btnDelete:focus {
    outline: none;
}

.material-symbols-outlined {
    transition: color 0.2s; /* Added transition property */
}

.material-symbols-outlined.active {
    color: red;
}

.iconIMG {
    border-radius: 30px;
    height: 90px;
    width: 110px;
    margin-left: 10px;
    margin-bottom: 10px;
}

.img-fluid {
    border-radius: 10px;
}

.contents {
    margin: 20px;
}

.card {
    background-color: #36373D;
    color: white;
}

.card-text {
    color: white;
}
</style>
