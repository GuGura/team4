<template>
    <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action">
            <div class="content-wrapper">
                <div class="content">
                    <h5 class="mb-1">작성자</h5>
                    <div class="mb-1">작성 날짜</div>
                    <h4 class="mb-1">게시물 제목</h4>
                    <div class="mb-1">게시물 내용</div>
                </div>
                <div class="image">
                    <img src="대충 이미지 넣기" alt="Post Image">
                </div>
            </div>
        </a>
        <a href="#" class="list-group-item list-group-item-action">
            <div class="content-wrapper">
                <div class="content">
                    <h5 class="mb-1">작성자</h5>
                    <div class="mb-1">작성 날짜</div>
                    <h4 class="mb-1">게시물 제목</h4>
                    <div class="mb-1">게시물 내용</div>
                </div>
                <div class="image">
                    <img src="대충 이미지 넣기2" alt="Post Image">
                </div>
            </div>
        </a>
        <a href="#" class="list-group-item list-group-item-action">
            <div class="content-wrapper">
                <div class="content">
                    <h5 class="mb-1">작성자</h5>
                    <div class="mb-1">작성 날짜</div>
                    <h4 class="mb-1">게시물 제목</h4>
                    <div class="mb-1">게시물 내용</div>
                </div>
                <div class="image">
                    <img src="대충 이미지 넣기3" alt="Post Image">
                </div>
            </div>
        </a>
        <div class="row my-2 mx-auto">
            <button type="button" class="btn btn-sm btn-primary" id="boardMoreButton" @click="boardPaging()">더 보기 ({{pagingInfo}})</button>
        </div>
    </div>
</template>

<script>
import api from "../../../../script/token/axios";
import router from "../../../../script/routes/router";

export default {
    name: "PostList",
    data(){
        return{
            lastPosting: 0
        }
    },
    methods:{
        boardPaging(){
            api.post("/content/listByPage",this.lastPosting
            ).then(({data})=>{
                this.lastPosting = data;
            })

        }
    }
};
</script>

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
