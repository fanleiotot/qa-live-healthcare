<template>
  <a-layout-header class="header">
    <div class="header-content">
      <div class="logo" @click="navigateTo('/')">
        <img src="https://images.pexels.com/photos/40568/medical-appointment-doctor-healthcare-40568.jpeg?auto=compress&cs=tinysrgb&w=100" alt="QA Live Healthcare" />
        <span>QA Live Healthcare</span>
      </div>
      
      <!-- 桌面端菜单 -->
      <a-menu v-model:selectedKeys="selectedKeys" mode="horizontal" class="nav-menu desktop-menu">
        <a-menu-item key="home" @click="navigateTo('/')">
          <HomeOutlined />
          {{ t('nav.home') }}
        </a-menu-item>
        <a-menu-item key="consultation" @click="navigateTo('/consultation')">
          <MessageOutlined />
          {{ t('nav.consultation') }}
        </a-menu-item>
        <a-menu-item key="doctors" @click="navigateTo('/doctors')">
          <TeamOutlined />
          {{ t('nav.doctors') }}
        </a-menu-item>
        <a-menu-item key="about" @click="navigateTo('/about')">
          <InfoCircleOutlined />
          {{ t('nav.about') }}
        </a-menu-item>
      </a-menu>
      
      <!-- 桌面端语言切换和登录按钮 -->
      <div class="desktop-actions">
        <a-dropdown placement="bottomRight">
          <a-button type="text" class="language-btn">
            <GlobalOutlined />
            {{ currentLanguageLabel }}
            <DownOutlined />
          </a-button>
          <template #overlay>
            <a-menu @click="handleLanguageChange">
              <a-menu-item 
                v-for="option in languageOptions" 
                :key="option.value"
                :class="{ 'active-language': currentLocale === option.value }"
              >
                {{ option.label }}
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
        <a-button type="primary" class="login-btn desktop-login" @click="navigateTo('/doctor/login')">
          <UserOutlined />
          {{ t('nav.doctorLogin') }}
        </a-button>
      </div>
      
      <!-- 移动端汉堡包菜单按钮 -->
      <a-button type="text" class="mobile-menu-btn" @click="toggleMobileMenu">
        <MenuOutlined />
      </a-button>
    </div>
    
    <!-- 移动端汉堡包菜单 -->
    <div v-if="mobileMenuVisible" class="mobile-menu-overlay" @click="closeMobileMenu">
      <div class="mobile-menu" @click.stop>
        <div class="mobile-menu-header">
          <span>{{ t('nav.menu') }}</span>
          <a-button type="text" class="close-btn" @click="closeMobileMenu">
            <CloseOutlined />
          </a-button>
        </div>
        <div class="mobile-menu-content">
          <a class="mobile-menu-item" @click="handleMobileNavigation('/')">
            <HomeOutlined />
            <span>{{ t('nav.home') }}</span>
          </a>
          <a class="mobile-menu-item" @click="handleMobileNavigation('/consultation')">
            <MessageOutlined />
            <span>{{ t('nav.consultation') }}</span>
          </a>
          <a class="mobile-menu-item" @click="handleMobileNavigation('/doctors')">
            <TeamOutlined />
            <span>{{ t('nav.doctors') }}</span>
          </a>
          <a class="mobile-menu-item" @click="handleMobileNavigation('/about')">
            <InfoCircleOutlined />
            <span>{{ t('nav.about') }}</span>
          </a>
          
          <!-- 移动端语言切换 -->
          <div class="mobile-language-section">
            <div class="mobile-language-title">
              <GlobalOutlined />
              <span>{{ currentLanguageLabel }}</span>
            </div>
            <div class="mobile-language-options">
              <a 
                v-for="option in languageOptions" 
                :key="option.value"
                class="mobile-language-item"
                :class="{ 'active-language': currentLocale === option.value }"
                @click="handleLanguageChange({ key: option.value })"
              >
                {{ option.label }}
              </a>
            </div>
          </div>
          
          <a-button type="primary" class="mobile-login-btn" @click="handleMobileNavigation('/doctor/login')">
            <UserOutlined />
            {{ t('nav.doctorLogin') }}
          </a-button>
        </div>
      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { 
  HomeOutlined, 
  MessageOutlined, 
  TeamOutlined, 
  InfoCircleOutlined, 
  UserOutlined,
  MenuOutlined,
  CloseOutlined,
  GlobalOutlined,
  DownOutlined
} from '@ant-design/icons-vue';
import { useI18n } from '../composables/useI18n';

const router = useRouter();
const route = useRoute();
const selectedKeys = ref<string[]>(['home']);
const mobileMenuVisible = ref(false);

// 多语言相关
const { t, currentLocale, currentLanguageLabel, languageOptions, changeLanguage } = useI18n();

watch(() => route.path, (newPath) => {
  if (newPath === '/') {
    selectedKeys.value = ['home'];
  } else if (newPath.startsWith('/consultation')) {
    selectedKeys.value = ['consultation'];
  } else if (newPath.startsWith('/doctors')) {
    selectedKeys.value = ['doctors'];
  } else if (newPath.startsWith('/about')) {
    selectedKeys.value = ['about'];
  }
}, { immediate: true });

const navigateTo = (path: string) => {
  router.push(path);
};

const toggleMobileMenu = () => {
  mobileMenuVisible.value = !mobileMenuVisible.value;
};

const closeMobileMenu = () => {
  mobileMenuVisible.value = false;
};

const handleMobileNavigation = (path: string) => {
  navigateTo(path);
  closeMobileMenu();
};

// 语言切换处理
const handleLanguageChange = ({ key }: { key: string }) => {
  changeLanguage(key);
};
</script>

<style scoped>
.header {
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  padding: 0;
  height: 64px;
  line-height: 64px;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
  padding: 0 24px;
}

.logo {
  display: flex;
  align-items: center;
  gap: 12px;
  cursor: pointer;
}

.logo img {
  height: 40px;
  width: 40px;
  border-radius: 8px;
  object-fit: cover;
}

.logo span {
  font-size: 20px;
  font-weight: 600;
  color: #1890ff;
}

.nav-menu {
  flex: 1;
  border: none;
  margin: 0 40px;
  line-height: 64px;
}

/* 桌面端操作区域 */
.desktop-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.language-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #666;
  font-size: 14px;
}

.language-btn:hover {
  color: #1890ff;
}

.active-language {
  background: #e6f7ff;
  color: #1890ff;
}

.login-btn {
  background: #52c41a;
  border-color: #52c41a;
}

.login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}

/* 移动端汉堡包菜单按钮 */
.mobile-menu-btn {
  display: none;
  font-size: 18px;
  padding: 4px 8px;
}

/* 移动端汉堡包菜单 */
.mobile-menu-overlay {
  position: fixed;
  top: 64px;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 1001;
  display: none;
}

.mobile-menu {
  position: absolute;
  top: 0;
  right: 0;
  width: 280px;
  height: 100vh;
  background: #fff;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
}

.mobile-menu-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 20px;
  border-bottom: 1px solid #f0f0f0;
  font-size: 16px;
  font-weight: 600;
}

.close-btn {
  font-size: 16px;
  padding: 4px;
}

.mobile-menu-content {
  flex: 1;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.mobile-menu-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  border-radius: 8px;
  color: #333;
  text-decoration: none;
  font-size: 16px;
  transition: all 0.3s;
}

.mobile-menu-item:hover {
  background: #f5f5f5;
  color: #1890ff;
}

.mobile-menu-item span {
  font-weight: 500;
}

.mobile-login-btn {
  margin-top: 20px;
  background: #52c41a;
  border-color: #52c41a;
  height: 44px;
  font-size: 16px;
  font-weight: 500;
}

.mobile-login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}

/* 移动端语言切换 */
.mobile-language-section {
  padding: 16px 0;
  border-top: 1px solid #f0f0f0;
  border-bottom: 1px solid #f0f0f0;
  margin: 16px 0;
}

.mobile-language-title {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 16px;
  font-weight: 500;
  color: #333;
  margin-bottom: 12px;
}

.mobile-language-options {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-left: 24px;
}

.mobile-language-item {
  padding: 8px 12px;
  border-radius: 6px;
  color: #666;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.3s;
  cursor: pointer;
}

.mobile-language-item:hover {
  background: #f5f5f5;
  color: #1890ff;
}

.mobile-language-item.active-language {
  background: #e6f7ff;
  color: #1890ff;
  font-weight: 500;
}

/* 响应式设计 */
@media (max-width: 1024px) {
  /* iPad 及以下设备 */
  .desktop-menu,
  .desktop-actions {
    display: none !important;
  }
  
  .mobile-menu-btn {
    display: inline-flex !important;
  }
  
  .mobile-menu-overlay {
    display: block !important;
  }
}

@media (max-width: 768px) {
  /* 手机端 */
  .header-content {
    padding: 0 16px;
  }
  
  .logo span {
    font-size: 18px;
  }
  
  .mobile-menu {
    width: 100%;
    max-width: 320px;
  }
}

@media (max-width: 480px) {
  /* 小屏手机 */
  .logo span {
    display: none;
  }
  
  .mobile-menu {
    width: 100%;
  }
}
</style>
