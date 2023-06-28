<script>
export default {
    name:'post',
    props:{
        post : Object
    },

}

</script>

<template>
    <a href="#" class="list-group-item list-group-item-action">
        <div class="content-wrapper">
            <div class="content">
                <h5 class="mb-1">{{post.username}}</h5>
                <div class="mb-1">{{post.uploadDate}}</div>
                <h4 class="mb-1">{{post.title}}</h4>
                <div class="mb-1">{{post.content}}</div>
            </div>
            <div class="image">
                <img alt="Post Image" :src="'data:image/png;base64,'+post.contentIMG">
            </div>
        </div>
    </a>
</template>

<style scoped>

</style>