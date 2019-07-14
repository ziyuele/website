<!--
  - Copyright (c) 2019. website http://ziyuele.com
  - any questions you can  send mail 2429362606@qq.com
  -->

<template>
        <div>
            <div>
                <template>
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span><strong>File Manager</strong></span>
                        </div>
                        <div style="display: inline">
                            <el-row :gutter="20" style="text-align: center">
                                <el-col :span="18">
                                    <el-input v-model="input" placeholder="Search files what you want ^v^">
                                    <el-button slot="append" icon="el-icon-search"></el-button></el-input>
                                </el-col>
                                <el-col :span="6">
                                    <el-button round>Upload
                                        files<i class="el-icon-upload el-icon--right"></i></el-button>
                                </el-col>
                            </el-row>
                        </div>
                    </el-card>
                </template>
            </div>
            <div style="height: 20px">
                <el-divider></el-divider>
            </div>
            <div>
               <template>
                  <el-table :data="dataItems" border>
                      <el-table-column prop="id" label="Id"></el-table-column>
                      <el-table-column prop="name" label="Name" ></el-table-column>
                      <el-table-column prop="size" label="Size" ></el-table-column>
                      <el-table-column prop="creationTime" label="CreationTime"></el-table-column>
                      <el-table-column prop="comments" label="Comments"></el-table-column>
                      <el-table-column prop="owner" label="Owner"></el-table-column>
                      <el-table-column prop="operation" label="Operation"></el-table-column>
                  </el-table>
               </template>
            </div>
            <!--div>
            <el-upload action="/v1/files" drag multiple :on-success="message_alert">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
            </div-->
        </div>
</template>



<script>
    export default {
        name: "file.vue",
        created() {},
        data: {
            dataItems: []
        },
        methods: {
           message_alert() {
               alert("this is ok");
            }
        }
    }
</script>

<style scoped>

</style>