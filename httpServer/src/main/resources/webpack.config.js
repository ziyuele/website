var path = require('path');
var webpack = require('webpack');

module.exports = {
  entry: {
     index: "./src/main.js"
  },
  output: {
      path: __dirname + "/templates",
      publicPath: "./templates",
      filename: "web-site.js"
    },
  devServer:{
      contentBase: "./src"
  },
 resolve: {
    alias: {
    'vue': 'vue/dist/vue.js'
      }
   }
}
