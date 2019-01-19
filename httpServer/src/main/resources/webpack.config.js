var path = require('path');
var webpack = require('webpack');

const VueLoaderPlugin = require('vue-loader/lib/plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  plugins: [
     new VueLoaderPlugin(),
     new HtmlWebpackPlugin({
                 template: 'src/index.html',
             })
  ],
  entry: {
     index: "./src/main.js"
  },
  output: {
      path: __dirname + "/templates",
      filename: "main.js"
    },
  devServer:{

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
