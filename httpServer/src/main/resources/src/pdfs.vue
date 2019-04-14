<template>
    <div>
        <div>
            <!-- using plugin : https://github.com/lfyfly/vue-waterfall-easy/blob/master/README-CN.md -->
            <vue-waterfall-easy :imgsArr="imgsArr" @scrollReachBottom="getData">this is a test</vue-waterfall-easy>
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
                imgsArr : []
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
