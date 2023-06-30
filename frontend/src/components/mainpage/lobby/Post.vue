<template>
    <div class="card mb-3">
            <div v-if="post.isImgIn"><img alt="Post Image" :src="'data:image/png;base64,'+post.contentIMG" class="img-fluid"></div>
        <div class="card-body">
            <div class="row">
            <div class="col-4" style="width: 100px;">
                <img :src="post.userIcon">
            </div>
            <div class="col-8">
            <h4 class="card-subtitle mb-2">{{ post.title }}</h4>
            <h6 class="card-title">{{ post.username }}</h6>
            <p class="card-text"><small class="text-body-secondary">{{ post.uploadDate }}</small></p>
            </div>
            </div>
            <p class="card-text">{{ post.content }}</p>
            <div class="btnDelete" @click="toggleDelete">
                <span class="material-symbols-outlined" :class="{ active: showDelete }">close</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'post',
    props: {
        post: Object
    },
    data() {
        return {
            showDelete: false
        }
    },
    methods: {
        toggleDelete() {
            this.showDelete = !this.showDelete;
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
    margin-left: 690px;
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
</style>
