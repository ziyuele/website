<template>
<div>
    <i>{{name}}</i>
    <el-tag>test</el-tag>

    <el-container>
        <el-menu router>
            <el-menu-item index="version" :route="{path: '/test'}" @click="test">
                <span slot="title">&nbsp首页</span>
            </el-menu-item>
            <el-menu-item index="version" :route="{path: '/test'}" @click="test">
                <span slot="title">&nbsp测试</span>
            </el-menu-item>
        </el-menu>
    </el-container>
    <el-main>
        <router-view v-if="true"/>
    </el-main>

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
              name: "test"
          }
      },
      computed: {

      },
      methods: {
         test() {
             this.$alert(this.$route.path);
             return "Module";
         }
      }
          
  }
</script>
