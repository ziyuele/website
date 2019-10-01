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
                                    <el-button round onclick="window.location.href='/#/v1/writeMarkDown'">Write-new
                                        -Blog</el-button>
                                </el-col>
                            </el-row>
                        </div>
                    </el-card>
                </template>

        </div>
        <div style="height: 10px">
            <el-divider></el-divider>
        </div>
        <div style="height: 20px">
            <el-divider></el-divider>
        </div>
        <el-row :gutter="16">
        <template v-for="blog in blogs">
                <el-col span="6" style="margin-bottom: 10px">
                <el-card class="box-card" shadow="hover" style="height: 350px">
                    <div slot="header" class="clearfix">{{blog}}</div>
                    <div>this is tite</div>
                </el-card>
            </el-col>
        </template>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "blogs.vue",
        created() {},
        data() {
            return {
            dataItems: [],
            blogs: ["test", "res", 3, 4,4,3,2,3,4,2,2]
            }
        },
        methods: {
            message_alert() {
                alert("this is ok");
            }
        }
    }

</script>