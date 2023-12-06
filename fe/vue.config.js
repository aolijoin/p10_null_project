const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/clue': {
                target: 'http://localhost:8080/clue',
                changeOrigin: true,
                pathRewrite: {'^/clue': "/"}
            },
            '/commons': {
                target: 'http://localhost:8080/commons',
                changeOrigin: true,
                pathRewrite: {'^/commons': "/"}
            },
            '/notify': {
                target: 'http://localhost:8080/notify',
                changeOrigin: true,
                pathRewrite: {'^/notify': "/"}
            },
        }
    }
}