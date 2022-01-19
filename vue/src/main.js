import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import "@/assets/css/global.css"
import * as Icons from '@element-plus/icons'

const app = createApp(App)
app.use(store).use(router).use(ElementPlus, {locale: zhCn, size: "mini"}).mount('#app')
// axios.defaults.baseURL = window.gurl.SERVICE_CONTEXT_PATH;
Object.keys(Icons).forEach(key => {
    app.component(key, Icons[key])
})

