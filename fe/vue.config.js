const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/user': {
                target: 'http://localhost:8080/user',
                changeOrigin: true,
                pathRewrite: {'^/user': "/"}
            }, '/apply': {
                target: 'http://localhost:8080/apply',
                changeOrigin: true,
                pathRewrite: {'^/apply': "/"}
            }, '/goods': {
                target: 'http://localhost:8080/goods',
                changeOrigin: true,
                pathRewrite: {'^/goods': "/"}
            },
        }
    }
}