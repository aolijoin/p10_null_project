const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/api': {
                target: 'http://localhost:8080/api',
                changeOrigin: true,
                pathRewrite: {'^/api': "/"}
            },'/user': {
                target: 'http://localhost:8080/user',
                changeOrigin: true,
                pathRewrite: {'^/user': "/"}
            },'/dormitory': {
                target: 'http://localhost:8080/dormitory',
                changeOrigin: true,
                pathRewrite: {'^/dormitory': "/"}
            },
        }
    }
}