<script>
export default {
    name:'post',
    props:{
        post : Object
    },

}

</script>

<template>
    <div class="card mb-3">
        <img src="/img/sidebar/DM_icon.png" class="card-img-top" alt="Post Image">
        <div class="card-body">
            <h5 class="card-title">{{ post.username }}</h5>
            <h4 class="card-subtitle mb-2">{{ post.title }}</h4>
            <p class="card-text">{{ post.content }}</p>
            <p class="card-text"><small class="text-body-secondary">Last updated {{ post.uploadDate }}</small></p>
        </div>
    </div>
</template>

<style scoped>
.content{ text-overflow:ellipsis; white-space: nowrap; overflow: hidden;-webkit-box-orient: vertical; -webkit-line-clamp: 2;}
.card-img-top {
    width: 100%;
    height: 15vw;
    object-fit: cover;
}
</style>