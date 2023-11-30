const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/goods': {
                target: 'http://localhost:8080/goods',
                changeOrigin: true,
                pathRewrite: {'^/goods': "/"}
            }, '/cate': {
                target: 'http://localhost:8080/cate',
                changeOrigin: true,
                pathRewrite: {'^/cate': "/"}
            }, '/cart': {
                target: 'http://localhost:8080/cart',
                changeOrigin: true,
                pathRewrite: {'^/cart': "/"}
            },'/order': {
                target: 'http://localhost:8080/order',
                changeOrigin: true,
                pathRewrite: {'^/order': "/"}
            },
        }
    }
}