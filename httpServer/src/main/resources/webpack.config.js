var path = require('path');
var webpack = require('webpack');

const VueLoaderPlugin = require('vue-loader/lib/plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin');
const uglifyjs = require('uglifyjs-webpack-plugin');

module.exports = {
  plugins: [
     new VueLoaderPlugin(),
     new webpack.ProvidePlugin({
         $: 'jquery',
         jQuery: 'jquery',
         'windows.jQuery': 'jquery'
     }),
     new HtmlWebpackPlugin({
                 template: 'src/index.html',
             }),
      new uglifyjs()
  ],
  entry: {
     index: "./src/main.js"
  },
  output: {
      path: __dirname + "/templates",
      filename: "main.js"
    },
  devServer:{
      host: '0.0.0.0',
      headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, PATCH, OPTIONS',
          'Access-Control-Allow-Headers': 'X-Requested-With, content-type, Authorization'
      },
      port: 8888,
      disableHostCheck: true,
      proxy: {
          '/': {
              target: 'http://localhost:8080/',
              changeOrigin: true,
              pathRewrite: {
                  '^/api': ''
              }
          }
      }
  },
  resolve: {
    alias: {
    'vue': 'vue/dist/vue.js'
      }
   },
  module: {
      rules: [
            { test: /\.js$/, loader:"babel-loader", exclude: /node_modules/ },
            { test: /\.vue$/, use: 'vue-loader' },
            { test: /\.css$/, use: ['style-loader', 'css-loader', 'postcss-loader']},
            { test: /\.(png|jpg|jpeg|gif|eot|ttf|woff|woff2|svg|svgz)(\?.+)?$/, use: [{loader: 'url-loader',options: {limit: 10000} }]}
          ]
    },
}
