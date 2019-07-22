<template>
    <div>
        <div>
        <template>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span><strong>PDFS Manager</strong></span>
                </div>
                <div style="display: inline">
                    <el-row :gutter="20" style="text-align: center">
                        <el-col :span="18">
                            <el-input v-model="input" placeholder="Search files what you want ^v^">
                                <el-button slot="append" icon="el-icon-search"></el-button></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-button round>Upload
                                PDF Files<i class="el-icon-upload el-icon--right"></i></el-button>
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
                <el-row :gutter="16">
                    <template v-for="blog in blogs">
                        <el-col span="12" style="margin-bottom: 10px">
                            <el-card class="box-card" shadow="hover" style="height: 350px">
                                <div slot="header" class="clearfix">{{blog}}</div>
                                <div>this is tite</div>
                            </el-card>
                        </el-col>
                    </template>
                </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        created() {
            this.getData();
        },
        data () {
            return {
                imgsArr : [],
                blogs: [1,3,4,5,]
            }
        },
        methods: {
          getData()  {
              var res = {
                  src: "/v1/files/12",
                  href: "www.baidu.com",
                  info: "this is info"
              };
              for (var x in 10) {
                 this.imgsArr.concat(res);
              }
          }
       }
    }

</script>
