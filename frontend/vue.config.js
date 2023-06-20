const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave : false,
    pluginOptions: {
        proxy: {
            '/': {
                target: 'http://localhost:8090',
                changeOrigin: true
            }
        }
    }
})
