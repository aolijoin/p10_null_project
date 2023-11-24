const {defineConfig} = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/student': {
                target: 'http://localhost:8080/student',
                changeOrigin: true,
                pathRewrite: {'^/student': "/"}
            },'/major': {
                target: 'http://localhost:8080/major',
                changeOrigin: true,
                pathRewrite: {'^/major': "/"}
            },
        }
    }
}