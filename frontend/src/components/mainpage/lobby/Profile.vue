<template>
    <form>
        <fieldset>
            <div>
                <strong>닉네임</strong>
                <input type="text" name="name" value="닉네임 입력">
            </div>
            <div>
                <strong>내 정보</strong>
                <label for="text"></label>
                <br/>
                <textarea rows="5" cols="70" name="text" id="text"></textarea>
                <br/>
                <div>
                    <input type="submit" value="제출">
                </div>
            </div>
        </fieldset>
    </form>

    <div style="padding:30px;">
        <input type="file" accept="image/*" @change="fileChange"/>
        <div>
            upload 이미지 :{{ file.name }} ({{ file.size }}) / {{ file.type }}
        </div>
        <div>
            <input type="submit" value="저장하기">
        </div>
    </div>

</template>
<script>
export default {
    name: 'Profile',
    data() {
        return {
            file: '',
        }
    },
    methods: {
        fileChange: function (e) {
            this.file = e.target.files[0];

        }
    }
}

</script>
<style>

</style>