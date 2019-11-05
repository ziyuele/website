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
             input: "",
             value: "",
         }
        },
        methods :{
            saveAction(value, input) {
                //alert("is going to save file")
                if (this.input === "") {
                    this.$alert("FileName should be null");
                    return
                } else {
                    $.ajax({
                        url: "/v1/markdowns",
                        type: 'POST',
                        data: JSON.stringify({
                            title: input,
                            documents: value,
                            author: ''
                        }),
                        success: (data) => {
                            this.alert(JSON.stringify(data.message));
                            this.$message("save ok");
                        },
                        error: (data) => {
                            this.$alert(JSON.parse(data.responseText).message);
                        }
                    })
                }
            }
        }
    }
</script>
