<script setup>
import {onMounted, reactive} from "vue";
import Post from "@/components/mainpage/lobby/Post.vue";
import api from "../../../../script/token/axios";

let postList = reactive({
  posts: [
    {
      username: '',
      userIcon: '',
      uploadDate: '',
      title: '',
      content: '',
      id: '',
      contentIMG: '',
      isImgIn: '',
      visible: '',
        sharingCode:''
    }

  ]
})
function initPosts() {
  api.post("/content/listByPage", {
    lastPosting: lastPosting
  }).then(({data}) => {
      if(data && data.length) {
          for (const item of data) {
              if(item.userIcon && item.userIcon.length) {
                  item.userIcon = "data:image/png;base64," + item.userIcon
              }else {
                  item.userIcon = "data:image/png;base64,null"
              }
          }
          postList.posts = data
          lastPosting = postList.posts.at(-1).id
      }
  })
}

function morePost() {
  api.post("/content/listByPage", {
    lastPosting: lastPosting
  }).then(({data}) => {
      if(data && data.length) {
          for (const item of data) {
              item.userIcon = "data:image/png;base64,"+item.userIcon
              postList.posts.push(item)
          }
          lastPosting = postList.posts.at(-1).id
      }
  })
}

onMounted(() => {
  initPosts()
    console.log(postList.posts)
})



let lastPosting = 0


</script>

<template>
  <div class="list-group">
    <div class="Box" v-for="(post,idx) in postList.posts" :key="idx">
      <post :post="post"/>
    </div>
    <div class="row my-2 mx-auto">
      <button type="button" class="btn btn-sm btn-primary" id="boardMoreButton" @click="morePost"><span
          class="material-symbols-outlined">more_horiz</span>
      </button>
    </div>
  </div>
</template>


<style>

img {
  max-width: 100%;
}

#boardMoreButton {
  width: 50px;
  height: 35px;
  border-radius: 10px;
  background-color: #1E1F22;
  --bs-btn-border-color: #1E1F22;
}


</style>
