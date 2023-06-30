<template>
    <div class="card mb-3">
        <img src="/img/sidebar/DM_icon.png" class="card-img-top" alt="Post Image">
        <div class="card-body">
            <h5 class="card-title">{{ post.username }}</h5>
            <h4 class="card-subtitle mb-2">{{ post.title }}</h4>
            <p class="card-text">{{ post.content }}</p>
            <p class="card-text"><small class="text-body-secondary">Last updated {{ post.uploadDate }}</small></p>
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
