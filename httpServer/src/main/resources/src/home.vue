<!--
  - Copyright (c) 2019. website http://ziyuele.com
  - any questions you can  send mail 2429362606@qq.com
  -->

<template>
    <div>
    <div class="block" style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
        <el-carousel height="300px" style="border-radius: 2px">
            <el-carousel-item v-for="item in 2" :key="item">
                <img src="/v1/files/12" height="300px" width="100%">
            </el-carousel-item>
        </el-carousel>
    </div>
    <div style="height: 20px"/>
    <div>
        <template>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>recent news</span>
                </div>
                <div v-for="o in 4" :key="o" class="text item">
                    {{'列表内容 ' + o }}
                </div>
            </el-card>
        </template>
    </div>
        <div style="height: 20px"/>
        <div>
            <template>
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>recent news</span>
                    </div>
                    <div v-for="o in 4" :key="o" class="text item">
                        {{'列表内容 ' + o }}
                    </div>
                </el-card>
            </template>
        </div>
    </div>
</template>

<script>

export default  {
    created() {

    },
    data() {
       return {
           image_item : getPic()
       }
    },
    methods: {
        getPic() {
            $.ajax({
                type: 'GET',
                url:'/files/',
                success
            });
        }
    }
}

</script>