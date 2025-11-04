import { createApp } from 'vue';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import './style.css';
import App from './App.vue';
import router from './router';
import { createI18n } from 'vue-i18n';
import zhCN from './locales/zh-cn.json';
import enUS from './locales/en-us.json';

const i18n = createI18n({
  legacy: false,
  locale: 'zh-cn',
  fallbackLocale: 'en-us',
  messages: {
    'zh-cn': zhCN,
    'en-us': enUS
  },
  globalInjection: true
});

const app = createApp(App);

app.use(Antd);
app.use(router);
app.use(i18n as any);
app.mount('#app');
