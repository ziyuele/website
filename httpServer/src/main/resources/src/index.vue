<template>
<div>
    <i>{{name}}</i>
    <el-tag>test</el-tag>
</div>
</template>

<script>
  export default {
      created() {
          $.ajax({
              url: "/v1/version",
              success: (response) => {
                  this.name = JSON.stringify(response);
              },
              error: (response) => {
                  this.name = JSON.stringify(response);
              }
          });
          this.name = "adfasfa";
      },
      data() {
          return {
              name: "kangjian"
          }
      }
  }
</script>
