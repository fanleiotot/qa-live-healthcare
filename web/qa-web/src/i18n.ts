import { createI18n } from 'vue-i18n'
import zhCN from './locales/zh-cn.json'
import enUS from './locales/en-us.json'

// 获取浏览器语言设置
const getDefaultLocale = (): 'zh-cn' | 'en-us' => {
  const stored = localStorage.getItem('locale')
  if (stored && ['zh-cn', 'en-us'].includes(stored)) {
    return stored as 'zh-cn' | 'en-us'
  }
  
  const browserLang = navigator.language.toLowerCase()
  if (browserLang.startsWith('zh')) {
    return 'zh-cn'
  }
  return 'en-us'
}

export const i18n = createI18n({
  legacy: false,
  locale: getDefaultLocale(),
  fallbackLocale: 'zh-cn',
  globalInjection: true,
  messages: {
    'zh-cn': zhCN,
    'en-us': enUS
  }
})

// 切换语言的辅助函数
export const switchLanguage = (locale: string) => {
  const supportedLocales = ['zh-cn', 'en-us']
  if (supportedLocales.includes(locale)) {
    i18n.global.locale.value = locale as 'zh-cn' | 'en-us'
    localStorage.setItem('locale', locale)
  }
}

export default i18n