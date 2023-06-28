<script setup>
import {reactive, ref} from "vue";
import Post from "@/components/mainpage/lobby/Post.vue";
import api from "../../../../script/token/axios";

function initPosts() {
    alert(lastPosting)
    api.post(process.env.VUE_APP_BASEURL_V1 + "/content/listByPage", {
        lastPosting : lastPosting
    }).then(() => {
        postList.posts += {
            username: "원식킴2",
            userIcon: '',
            writtenDate: "2023.05.21 오후 10:41",
            title: "안녕하세요2",
            context: '',
            contentUID: ''
        }
    })
}

let postList = reactive({
    posts: [
        {
            username: "원식킴",
            userIcon: '',
            writtenDate: "2023.05.21 오후 10:41",
            title: "안녕하세요",
            context: '',
            contentUID: ''
        }

    ]
})

let lastPosting = 0


</script>

<template>
    <div class="list-group">
        <div class="Box" v-for="(post,idx) in postList.posts" :key="idx">
            <post :post="post"/>
        </div>
        <div class="row my-2 mx-auto">
            <button type="button" class="btn btn-sm btn-primary" id="boardMoreButton" @click="initPosts">더 보기 ({{pagingInfo}})</button>
        </div>
    </div>
</template>


<style>
.content-wrapper {
    display: flex;
    align-items: center;
}

.content {
    flex: 1;
}

.image {
    margin-left: 20px;
}

img {
    max-width: 100%;
}
</style>
