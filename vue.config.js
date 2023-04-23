const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
    css: {
        loaderOptions: {
            less: {
                lessOptions: {
                    modifyVars: {
                        // 在这里添加全局 Less 变量
                        'a': '3.75vw'
                    },
                    javascriptEnabled: true
                }
            }
        }
    },
    // devServer: {
    //     // 代理配置
    //     proxy: 'https://app.cashviolin.xyz'
    // },
    configureWebpack: {
        resolve: { extensions: [".ts", ".tsx", ".js", ".json"] },
        module: {
            rules: [{
                test: /\.tsx?$/,
                loader: 'ts-loader',
                exclude: /node_modules/,
                options: {
                    appendTsSuffixTo: [/\.vue$/],
                }
            }]
        }
    },
});