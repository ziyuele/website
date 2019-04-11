<!--
  - Copyright (c) 2019. website http://ziyuele.com
  - any questions you can  send mail 2429362606@qq.com
  -->

<template>
        <div>
            <el-upload action="/files" drag multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
        </div>
</template>

<script>
    export default {
        name: "file.vue"
    }
</script>

<style scoped>

</style>