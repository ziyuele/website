<!--
  - Copyright (c) 2019. website http://ziyuele.com
  - any questions you can  send mail 2429362606@qq.com
  -->

<template>
   <div>
       <div>
           <el-input v-model="input" placeholder="fileName"></el-input>
       </div>
       <div>
           <mavon-editor v-model="value" @save=saveAction />
       </div>
   </div>
</template>


<script>
    export default {
        created() {
        },
        data() {
         return {
             headers: {"Content-Type": "application/json", "charset": "UTF-8"},
             input: "",
             value: "",
         }
        },
        methods :{
            saveAction(value) {
                //alert("is going to save file")
                if (this.input === "") {
                    this.$alert("FileName should be null");
                    return
                } else {
                    $.ajax({
                        url: "/v1/markdowns",
                        type: 'POST',
                        headers: this.headers,
                        data: JSON.stringify({
                            title: this.input,
                            documents: value,
                            author: "kangjian"
                        }),
                        success: (data) => {
                            this.$alert(data.message);
                            this.$alert(value);
                            this.$message("save ok");
                        },
                        error: (data) => {
                            this.$alert(data);
                        }
                    });

                }
            }
        }
    }
</script>
