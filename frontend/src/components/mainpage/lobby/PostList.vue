<script setup>
import {onMounted, reactive, ref} from "vue";
import Post from "@/components/mainpage/lobby/Post.vue";
import api from "../../../../script/token/axios";

function initPosts() {
    api.post(process.env.VUE_APP_BASEURL_V1 + "/content/listByPage", {
        lastPosting : lastPosting
    }).then(({data}) => {
        postList.posts = data;
        lastPosting=postList.posts.at(-1).id
        })
}
function morePost(){
    api.post(process.env.VUE_APP_BASEURL_V1 + "/content/listByPage", {
        lastPosting : lastPosting
    }).then(({data}) => {
        for(const item of data){
            postList.posts.push(item)
        }
        lastPosting=postList.posts.at(-1).id
    })
}

onMounted(() => {
    initPosts()
})

let postList = reactive({
    posts: [
        {
            username: '',
            userIcon: '',
            uploadDate:'',
            title: '',
            content: '',
            id: '',
            contentIMG: ''
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
            <button type="button" class="btn btn-sm btn-primary" id="boardMoreButton" @click="morePost">더 보기 ({{pagingInfo}})</button>
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
