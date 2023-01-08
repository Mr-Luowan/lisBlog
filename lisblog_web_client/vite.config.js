import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
const { resolve } = require('path');
import vue from '@vitejs/plugin-vue';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  //配置跨域
  //这里的 '/api' 指的是你想代理的请求，而target则是你所想请求的后端地址。 
  //新手最容易出现的问题就是localhost:3000去请求localhost:8080，那么在target处应该填写8080.
  //最后，在实际请求方法中，你可以写成如下代码  axois.post('/api/xxx', data)
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080/',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
