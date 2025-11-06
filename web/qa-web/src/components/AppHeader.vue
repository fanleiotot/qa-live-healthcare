<template>
  <a-layout-header class="header">
    <div class="header-content">
      <div class="logo" @click="navigateTo('/')">
        <img src="https://images.pexels.com/photos/40568/medical-appointment-doctor-healthcare-40568.jpeg?auto=compress&cs=tinysrgb&w=100" alt="QA Live Healthcare" />
        <span>{{ $t('header.logo') }}</span>
      </div>
      
      <!-- 桌面端菜单 -->
      <a-menu v-model:selectedKeys="selectedKeys" mode="horizontal" class="nav-menu desktop-menu">
        <a-menu-item key="home" @click="navigateTo('/')">
          <HomeOutlined />
          {{ $t('header.home') }}
        </a-menu-item>
        <a-menu-item key="consultation" @click="navigateTo('/consultation')">
          <MessageOutlined />
          {{ $t('header.consultation') }}
        </a-menu-item>
        <a-menu-item key="doctors" @click="navigateTo('/doctors')">
          <TeamOutlined />
          {{ $t('header.doctors') }}
        </a-menu-item>
        <a-menu-item key="about" @click="navigateTo('/about')">
          <InfoCircleOutlined />
          {{ $t('header.about') }}
        </a-menu-item>
      </a-menu>
      
      <!-- 桌面端右侧区域 -->
      <div class="header-right desktop-right">
        <!-- 多语言切换 -->
        <a-dropdown :trigger="['click']" placement="bottomRight">
          <a-button type="text" class="language-btn">
            <GlobalOutlined />
            {{ $t('header.language') }}
            <DownOutlined />
          </a-button>
          <template #overlay>
            <a-menu @click="handleLanguageChange">
              <a-menu-item key="zh-cn" :class="{ active: currentLocale === 'zh-cn' }">
                {{ $t('languages.zh-cn') }}
              </a-menu-item>
              <a-menu-item key="en-us" :class="{ active: currentLocale === 'en-us' }">
                {{ $t('languages.en-us') }}
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
        
        <a-button type="primary" class="login-btn" @click="navigateTo('/doctor/login')">
          <UserOutlined />
          {{ $t('header.doctorLogin') }}
        </a-button>
      </div>

      <!-- 移动端汉堡包菜单按钮 -->
      <a-button type="text" class="mobile-menu-btn" @click="toggleMobileMenu">
        <MenuOutlined />
      </a-button>
    </div>

    <!-- 移动端菜单抽屉 -->
    <div v-if="mobileMenuVisible" class="mobile-menu-overlay" @click="closeMobileMenu">
      <div class="mobile-menu-drawer" @click.stop>
        <div class="mobile-menu-header">
          <span>{{ $t('header.menu') }}</span>
          <a-button type="text" class="close-btn" @click="closeMobileMenu">
            <CloseOutlined />
          </a-button>
        </div>
        <div class="mobile-menu-content">
          <div class="mobile-menu-item" @click="navigateToMobile('/')">
            <HomeOutlined />
            <span>{{ $t('header.home') }}</span>
          </div>
          <div class="mobile-menu-item" @click="navigateToMobile('/consultation')">
            <MessageOutlined />
            <span>{{ $t('header.consultation') }}</span>
          </div>
          <div class="mobile-menu-item" @click="navigateToMobile('/doctors')">
            <TeamOutlined />
            <span>{{ $t('header.doctors') }}</span>
          </div>
          <div class="mobile-menu-item" @click="navigateToMobile('/about')">
            <InfoCircleOutlined />
            <span>{{ $t('header.about') }}</span>
          </div>
          
          <!-- 移动端语言切换 -->
          <div class="mobile-language-section">
            <div class="mobile-language-title">
              <GlobalOutlined />
              {{ $t('header.language') }}
            </div>
            <div class="mobile-language-options">
              <div 
                class="mobile-language-item" 
                :class="{ active: currentLocale === 'zh-cn' }"
                @click="handleLanguageChange({ key: 'zh-cn' })"
              >
                {{ $t('languages.zh-cn') }}
              </div>
              <div 
                class="mobile-language-item" 
                :class="{ active: currentLocale === 'en-us' }"
                @click="handleLanguageChange({ key: 'en-us' })"
              >
                {{ $t('languages.en-us') }}
              </div>
            </div>
          </div>
          
          <a-button type="primary" class="mobile-login-btn" @click="navigateToMobile('/doctor/login')">
            <UserOutlined />
            {{ $t('header.doctorLogin') }}
          </a-button>
        </div>
      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref, watch, computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { switchLanguage } from '../i18n';
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

const router = useRouter();
const route = useRoute();
const { locale } = useI18n();
const selectedKeys = ref<string[]>(['home']);
const mobileMenuVisible = ref(false);

const currentLocale = computed(() => locale.value);

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

const navigateToMobile = (path: string) => {
  router.push(path);
  closeMobileMenu();
};

const toggleMobileMenu = () => {
  mobileMenuVisible.value = !mobileMenuVisible.value;
};

const closeMobileMenu = () => {
  mobileMenuVisible.value = false;
};

const handleLanguageChange = ({ key }: { key: string }) => {
  switchLanguage(key);
  closeMobileMenu();
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

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

.language-btn {
  color: #666;
  font-size: 14px;
  padding: 4px 12px;
  height: 32px;
  border-radius: 6px;
  transition: all 0.2s;
}

.language-btn:hover {
  background-color: #f5f5f5;
  color: #1890ff;
}

.language-btn .anticon {
  font-size: 16px;
}

/* 语言下拉菜单样式 */
:deep(.ant-dropdown-menu) {
  .ant-dropdown-menu-item.active {
    background-color: #e6f7ff;
    color: #1890ff;
    font-weight: 500;
  }
}

.login-btn {
  background: #52c41a;
  border-color: #52c41a;
}

.login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}

/* 移动端样式 */
.mobile-menu-btn {
  display: none;
  font-size: 20px;
  padding: 0;
  width: 40px;
  height: 40px;
}

.mobile-menu-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1100;
  display: flex;
  justify-content: flex-end;
}

.mobile-menu-drawer {
  background: #fff;
  width: 280px;
  height: 100vh;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.15);
  animation: slideInRight 0.3s ease-out;
}

@keyframes slideInRight {
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
}

.mobile-menu-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 20px;
  border-bottom: 1px solid #f0f0f0;
  font-size: 18px;
  font-weight: 600;
}

.close-btn {
  font-size: 16px;
  padding: 4px;
}

.mobile-menu-content {
  padding: 20px 0;
}

.mobile-menu-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px 20px;
  cursor: pointer;
  transition: background-color 0.2s;
  font-size: 16px;
}

.mobile-menu-item:hover {
  background-color: #f5f5f5;
}

.mobile-menu-item .anticon {
  font-size: 18px;
  color: #1890ff;
}

.mobile-language-section {
  padding: 16px 20px;
  border-top: 1px solid #f0f0f0;
  border-bottom: 1px solid #f0f0f0;
  margin: 16px 0;
}

.mobile-language-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 16px;
  font-weight: 600;
  color: #333;
  margin-bottom: 12px;
}

.mobile-language-title .anticon {
  font-size: 18px;
  color: #1890ff;
}

.mobile-language-options {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.mobile-language-item {
  padding: 8px 12px;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 14px;
}

.mobile-language-item:hover {
  background-color: #f5f5f5;
}

.mobile-language-item.active {
  background-color: #e6f7ff;
  color: #1890ff;
  font-weight: 500;
}

.mobile-login-btn {
  margin: 20px 20px 0;
  width: calc(100% - 40px);
  height: 44px;
  background: #52c41a;
  border-color: #52c41a;
  font-size: 16px;
}

.mobile-login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}

/* 响应式断点 */
@media (max-width: 1024px) {
  /* iPad及以下设备 */
  .desktop-menu,
  .desktop-right {
    display: none !important;
  }
  
  .mobile-menu-btn {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .header-content {
    padding: 0 16px;
  }
}

@media (max-width: 768px) {
  /* 手机设备 */
  .logo span {
    font-size: 18px;
  }
  
  .logo img {
    height: 36px;
    width: 36px;
  }
  
  .mobile-menu-drawer {
    width: 100%;
    max-width: 320px;
  }
}

@media (max-width: 480px) {
  /* 小屏手机 */
  .header-content {
    padding: 0 12px;
  }
  
  .logo span {
    display: none;
  }
  
  .mobile-menu-drawer {
    width: 100%;
  }
}
</style>
