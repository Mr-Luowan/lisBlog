import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/index.css'
import http from './axiosdir/index.js'
import state from './store/index.js'

const app = createApp(App)

app.use(router)
app.use(ElementUI)
app.use(state)

app.mount('#app')
app.config.globalProperties.$axios = http