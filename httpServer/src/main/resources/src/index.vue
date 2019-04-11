<template>
<div>
    <el-header>
        <div style="font-size:22px;color:white;font-family: Helvetica, Tahoma, Arial;">
            <span>
            <span type="text" style="float: left">ZIYUELE</span>
             <el-dropdown trigger="click" @command="handleCommands" style="float: right">
                <span class="el-dropdown-link el-button--text">{{user_name}}<i class="el-icon-arrow-down el-icon--right"></i></span>
                 <el-dropdown-menu slot="dropdown">
                     <el-dropdown-item command="userInfo">user info</el-dropdown-item>
                     <el-dropdown-item command="userExit">exit</el-dropdown-item>
                 </el-dropdown-menu>
              </el-dropdown>
        </span>
        </div>
    </el-header>
    <div>
        <el-row>
            <el-col :span="6">
                <div id="menu" style="margin-top: 20px; margin-right: 15px">
                    <template>
                        <!-- add child page here default is fff -->
                        <el-menu router background-color="#E4E7ED" text-color="#525C64" active-text-color="#326DE6" class="el-menu-vertical-demo">
                            <el-menu-item index="fff" :route="{path:'/'}">首页</el-menu-item>
                            <el-menu-item index="fff" :route="{path:'/v1/pdfs'}">pdfs</el-menu-item>
                            <el-menu-item index="ggg" :route="{path:'/v1/files'}">files</el-menu-item>
                            <el-menu-item index="hhh" :route="{path:'/app1/'}">three</el-menu-item>
                        </el-menu>
                    </template>
                </div>
            </el-col>
            <el-col :span="18">
                <div style="margin-top: 20px;margin-right: 0px ">
                    <router-view v-if="routerEnable"/>
                </div>
            </el-col>
        </el-row>
    </div>
</div>
</template>

<script>
  export default {
      created() {
          $.ajax({
              url: "/version",
              success: (response) => {
                  this.name = JSON.stringify(response);
              },
              error: (response) => {
                  this.name = JSON.stringify(response);
              }
          });
      },
      data() {
          return {
              routerEnable: true,
              name: "test",
              user_name : "kangjian"
          }
      },
      computed: {

      },
      methods: {
          handleCommands(command) {
              if (command === "userInfo") {
                  this.$alert(command);
              } else if (command === "userExit") {
                  this.$alert(command);
              } else {
                  this.$alert("error input");
              }
          }
      }
          
  }
</script>

<style>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }
    body > .el-container {
        margin-bottom: 40px;
    }
    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }
    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
