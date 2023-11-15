const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath:'/',
  devServer: {
    proxy: 'http://localhost:8084'
  },
  transpileDependencies: true
})
