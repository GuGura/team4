=<template>
    <div class="w-32 h-32 border-2 border-dotted border-blue-500">
        <div v-if="images"
             class="w-full h-full flex items-center">
            <img :src="images" alt="image">
        </div>
        <!--        // 아이콘 클릭시 clickInputTag 메소드 실행됨-->
        <div v-else
             class="w-full h-full flex items-center justify-center cursor-pointer hover:bg-pink-100"
             @click="clickInputTag()">
            <input ref="image" id="input"
                   type="file" name="image" accept="image/*" multiple="multiple"
                   class="hidden"
                   @change="uploadImage()">
            <svg class="w-8 h-8" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
        </div>

    </div>
</template>
<script>
import notToken from "../../../../script/notTokenAxios";
export default {
    name:'UploadImage',
    data: ()=>({
        images: ''
    }),
    methods: {
// Input tag가 열리고 이미지를 선택하면 값이 변화된 것을 감지(change event)하여,
// (change event에서 uploadImage를 바로 실행하도록 함) uploadImage 메소드 실행됨.
        uploadImage: function() {
            let form = new FormData()
            let image = this.$refs['image'].files[0]
            //uploadImage 메소드를 통해 이미지 업로드
            //메소드가 실행되면 ref 속성을 통해 값에 접근하고, files값을 new Form 인스턴스에 추가해줘서
            //form data를 생성함.(post 방식으로 서버에 전송)
            form.append('image', image)

            notToken.post('/upload', form, {  // 체크!
                header: { 'Content-Type': 'multipart/form-data' }
            }).then( ({data}) => {
                this.images = data
                //서버를 통해 업로드에 성공하게 되면 vue가 알아서 img의 값이 변했으니, 자동으로 감지해서
                //이미지를 뿅하고 띄워줌.
            })
                .catch( err => console.log(err))
        },
        clickInputTag: function() {
            this.$refs['image'].click()
        }
    }
}
</script>
<style>

</style>