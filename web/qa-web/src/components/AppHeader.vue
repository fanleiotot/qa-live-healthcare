<template>
  <a-layout-header class="header">
    <div class="header-content">
      <div class="logo">
        <img src="https://images.pexels.com/photos/40568/medical-appointment-doctor-healthcare-40568.jpeg?auto=compress&cs=tinysrgb&w=100" alt="QA Live Healthcare" />
        <span>QA Live Healthcare</span>
      </div>
      <div class="desktop-menu">
        <a-menu v-model:selectedKeys="selectedKeys" mode="horizontal" class="nav-menu">
          <a-menu-item key="home" @click="navigateTo('/')">
            <HomeOutlined />
            {{ t('header.home') }}
          </a-menu-item>
          <a-menu-item key="consultation" @click="navigateTo('/consultation')">
            <MessageOutlined />
            {{ t('header.consultation') }}
          </a-menu-item>
          <a-menu-item key="doctors" @click="navigateTo('/doctors')">
            <TeamOutlined />
            {{ t('header.doctors') }}
          </a-menu-item>
          <a-menu-item key="about" @click="navigateTo('/about')">
            <InfoCircleOutlined />
            {{ t('header.about') }}
          </a-menu-item>
        </a-menu>
        <a-select
          v-model:value="currentLocale"
          style="width: 120px; margin-right: 16px"
          @change="changeLocale"
        >
          <a-select-option value="zh-cn">中文</a-select-option>
          <a-select-option value="en-us">English</a-select-option>
        </a-select>
        <a-button type="primary" class="login-btn" @click="navigateTo('/doctor/login')">
          <UserOutlined />
          医生登录
        </a-button>
      </div>
      <div class="mobile-menu">
        <a-button class="hamburger-btn" @click="toggleMenu">
          <MenuOutlined />
        </a-button>
        <a-drawer
          :visible="menuVisible"
          placement="right"
          :closable="false"
          @close="toggleMenu"
          :width="300"
          class="mobile-drawer"
        >
          <div class="drawer-header">
            <a-button type="text" @click="toggleMenu" class="close-btn">
              <CloseOutlined />
            </a-button>
          </div>
          <a-menu v-model:selectedKeys="selectedKeys" mode="vertical" class="mobile-nav-menu">
            <a-menu-item key="home" @click="navigateTo('/')">
              <HomeOutlined />
              首页
            </a-menu-item>
            <a-menu-item key="consultation" @click="navigateTo('/consultation')">
              <MessageOutlined />
              问诊
            </a-menu-item>
            <a-menu-item key="doctors" @click="navigateTo('/doctors')">
              <TeamOutlined />
              医生
            </a-menu-item>
            <a-menu-item key="about" @click="navigateTo('/about')">
              <InfoCircleOutlined />
              关于
            </a-menu-item>
            <a-menu-item key="login" @click="navigateTo('/doctor/login')" class="mobile-login-btn">
              <UserOutlined />
              医生登录
            </a-menu-item>
          </a-menu>
        </a-drawer>
      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { HomeOutlined, MessageOutlined, TeamOutlined, InfoCircleOutlined, UserOutlined, MenuOutlined, CloseOutlined } from '@ant-design/icons-vue';

const router = useRouter();
const route = useRoute();
const { t, locale } = useI18n();
const selectedKeys = ref<string[]>(['home']);
const menuVisible = ref(false);
const currentLocale = ref('zh-cn');

const changeLocale = (value: string) => {
  locale.value = value;
  currentLocale.value = value;
};

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
  menuVisible.value = false;
};

const toggleMenu = () => {
  menuVisible.value = !menuVisible.value;
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

.desktop-menu {
  display: flex;
  align-items: center;
}

.nav-menu {
  flex: 1;
  border: none;
  margin: 0 40px;
  line-height: 64px;
  min-width: 500px;
}

.login-btn {
  background: #52c41a;
  border-color: #52c41a;
}

.login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}

.mobile-menu {
  display: none;
}

.hamburger-btn {
  background: transparent;
  border: none;
  font-size: 20px;
}

.mobile-drawer {
  z-index: 1001;
}

.drawer-header {
  display: flex;
  justify-content: flex-end;
  padding: 16px;
}

.close-btn {
  font-size: 20px;
}

.mobile-nav-menu {
  border: none;
}

.mobile-login-btn {
  background: #52c41a;
  border-color: #52c41a;
  color: #fff;
  margin-top: 16px;
}

.mobile-login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}

@media (max-width: 992px) {
  .desktop-menu {
    display: none;
  }

  .mobile-menu {
    display: block;
  }
}

@media (min-width: 993px) {
  .desktop-menu {
    display: flex;
  }

  .mobile-menu {
    display: none;
  }
}
</style>
