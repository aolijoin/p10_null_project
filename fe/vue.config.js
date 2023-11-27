const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/user': {
                target: 'http://localhost:8080/user',
                ws: true,
                changeOrigin: true,
                pathRewrite: {'^/user': "/"}
            }, '/book': {
                target: 'http://localhost:8080/book',
                changeOrigin: true,
                pathRewrite: {'^/book': "/"}
            }, '/publishing': {
                target: 'http://localhost:8080/publishing',
                changeOrigin: true,
                pathRewrite: {'^/publishing': "/"}
            }, '/borrow': {
                target: 'http://localhost:8080/borrow',
                changeOrigin: true,
                pathRewrite: {'^/borrow': "/"}
            },
        }
    }
}