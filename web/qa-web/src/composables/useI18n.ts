import { computed } from 'vue';
import { useI18n as useVueI18n } from 'vue-i18n';

export interface LanguageOption {
  value: string;
  label: string;
}

export const useI18n = () => {
  const { locale, t } = useVueI18n();

  // 可用的语言选项
  const languageOptions: LanguageOption[] = [
    { value: 'zh-CN', label: '中文' },
    { value: 'en-US', label: 'English' },
  ];

  // 当前语言
  const currentLocale = computed(() => locale.value);

  // 当前语言的显示名称
  const currentLanguageLabel = computed(() => {
    const option = languageOptions.find(opt => opt.value === locale.value);
    return option?.label || '中文';
  });

  // 切换语言
  const changeLanguage = (newLocale: string) => {
    locale.value = newLocale;
    localStorage.setItem('locale', newLocale);
  };

  return {
    t,
    locale,
    currentLocale,
    currentLanguageLabel,
    languageOptions,
    changeLanguage,
  };
};