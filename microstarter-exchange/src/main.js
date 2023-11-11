import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
// import bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import 'prismjs/components.js'
import 'prismjs/prism.js'
import 'prismjs/themes/prism.min.css'




import "./assets/dashboard.css";
import "./assets/dashboard.js";
import "./assets/clients/WSClient.js";
import "./assets/clients/EndpointClient.js";




import "feather-icons/dist/feather.min.js";
import EndpointClient from "@/assets/clients/EndpointClient";
// import './assets/main.css'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
