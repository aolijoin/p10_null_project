const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/api': {
                target: 'http://localhost:8080/api',
                changeOrigin: true,
                pathRewrite: {'^/api': "/"}
            }, '/com': {
                target: 'http://localhost:8080/com',
                changeOrigin: true,
                pathRewrite: {'^/com': "/"}
            }, '/clue': {
                target: 'http://localhost:8080/clue',
                changeOrigin: true,
                pathRewrite: {'^/clue': "/"}
            }, '/notify': {
                target: 'http://localhost:8080/notify',
                changeOrigin: true,
                pathRewrite: {'^/notify': "/"}
            }
        }
    }
}