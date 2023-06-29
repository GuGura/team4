<script>
export default {
    name:'post',
    props:{
        post : Object
    }
}

</script>

<template>
    <a href="#" class="list-group-item list-group-item-action">
        <div class="content-wrapper">
            <div class="content">
                <h5 class="mb-1">{{post.username}}</h5>
                <div class="mb-1">{{post.writtenDate}}</div>
                <h4 class="mb-1">{{post.title}}</h4>
                <div class="mb-1">{{post.context}}</div>
            </div>
            <div class="image">
                <img src="대충 이미지 넣기3" alt="Post Image">
            </div>
        </div>
    </a>
</template>

<style scoped>

</style>