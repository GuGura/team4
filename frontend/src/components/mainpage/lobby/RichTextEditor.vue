<template>
    <div className="quill-editor-container">
        <div className="quill-editor-content">
            <QuillEditor v-model="content" :options="quillOptions"/>
        </div>
        <div id="custom-toolbar" className="quill-editor-toolbar">
            <!-- Your toolbar content here -->
        </div>
    </div>
</template>

<script>
import {ref, defineComponent} from 'vue'
import {QuillEditor} from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

export default defineComponent({
    name: 'RichTextEditor',
    components: {
        QuillEditor,
    },
    setup() {
        const content = ref({})

        content.value = {
            ops: [
                {insert: 'consectetur adipiscing elit. '},
                {attributes: {bold: true}, insert: 'Nunc ultrices ligula'},
                {insert: ' eu eros pulvinar, eu consequat nulla consectetur. Cras ut purus felis. Nunc placerat risus a augue sodales, at ultricies diam tristique. Donec venenatis auctor mauris,'},
                {attributes: {italic: true}, insert: ' at molestie enim euismo'},
                {insert: 'd ac. Mauris viverra, leo id porttitor maximus, diam magna blandit nibh, ac vehicula nulla diam in eros. Nullam mi risus, blandit a elit quis, aliquam porttitor diam. In mauris nunc, fringilla at auctor in, sodales eu diam. In convallis gravida urna, ut gravida massa euismod quis.\nProin rutrum tortor at augue eleifend finibus. '},
                {attributes: {underline: true}, insert: 'Quisque non tincidunt dolor.'},
                {insert: ' Aenean ullamcorper, diam ac vehicula imperdiet, arcu erat sodales sem, vitae lobortis dolor urna dapibus nisi. Nulla lacus urna, vehicula quis rutrum sit amet, '},
                {attributes: {link: 'http://localhost'}, insert: 'porttitor eget ligula'},
                {insert: '. Nam eget ante ornare, egestas nulla dapibus, tempus nisi. Sed vel odio augue. Fusce vulputate, risus sit amet venenatis tristique, ex ex pulvinar orci, vitae lobortis massa enim ac ante. Nulla sodales mauris ligula, a tempus felis vulputate ut. Sed scelerisque dolor at leo hendrerit vehicula.\n'},
            ],
        }

        const quillOptions = {
            theme: 'snow',
            modules: {
                toolbar: [
                    ['bold', 'italic', 'underline', 'strike'],
                    [{list: 'ordered'}, {list: 'bullet'}],
                    [{indent: '-1'}, {indent: '+1'}],
                    [{size: ['small', false, 'large', 'huge']}],
                    [{header: [1, 2, 3, 4, 5, 6, false]}],
                    [{color: []}, {background: []}],
                    [{font: []}],
                    [{align: []}],
                    // ['clean'],
                    ['link', 'image', 'video'],
                ],
            },
        }

        return {
            content,
            quillOptions,
        }
    },
})
</script>

<style>
.quill-editor-container {
    display: flex;
    flex-direction: column;
    width: 95%;
}

.quill-editor-content {
    flex: 1;
}

.quill-editor-toolbar {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
