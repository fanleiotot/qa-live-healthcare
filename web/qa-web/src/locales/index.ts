import { createI18n } from 'vue-i18n';
import zhCN from './zh-cn.json';
import enUS from './en-us.json';

const messages = {
  'zh-CN': zhCN,
  'en-US': enUS,
};

// 获取浏览器语言或从本地存储获取用户选择的语言
const getDefaultLocale = (): string => {
  const savedLocale = localStorage.getItem('locale');
  if (savedLocale && messages[savedLocale as keyof typeof messages]) {
    return savedLocale;
  }
  
  // 默认使用中文
  return 'zh-CN';
};

export const i18n = createI18n({
  legacy: false,
  locale: getDefaultLocale(),
  fallbackLocale: 'zh-CN',
  messages,
});

export default i18n;