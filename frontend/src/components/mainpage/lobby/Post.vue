<template>
    <div class="card mb-3">

        <div v-if="post.visible" class="card-body" :id="post.id">
            <div class="row">
                <div class="col-4" style="width: 120px;" v-if="post.userIcon!=='data:image/png;base64,null'">
                    <img class="iconIMG" :src="'data:image/png;base64,'+post.userIcon">
                </div>
                <div class="col-4" style="width: 120px;" v-else>
                    <img class="iconIMG" src="public/img/serverlist/user_icon.png">
                </div>
                <div class="col-7">
                    <h4 class="card-subtitle mb-2">{{ post.title }}</h4>
                    <h6 class="card-title">{{ post.username }}</h6>
                    <p class="card-text"><small class="card-text fw-lighter">{{ post.uploadDate }}</small></p>
                </div>
                <div class="col-1 btnDelete" @click="toggleDelete">
                    <span class="material-symbols-outlined" :class="{ active: showDelete }">close</span>
                </div>
            </div>
            <div v-if="post.isImgIn"><img alt="Post Image" :src="'data:image/png;base64,'+post.contentIMG"
                                          class="img-fluid"></div>
            <p class="card-text contents fw-light">{{ post.content }}</p>
        </div>
        <div v-else>
            <p class="card-text contents fw-light">포스트가 존재하지않습니다.</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'post',
    props: {
        post: Object
    },
    methods: {
        toggleDelete() {

        }
    }
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
    margin-left: 140px;
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
