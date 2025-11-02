<template>
  <a-layout-header class="header">
    <div class="header-content">
      <div class="logo">
        <img src="https://images.pexels.com/photos/40568/medical-appointment-doctor-healthcare-40568.jpeg?auto=compress&cs=tinysrgb&w=100" alt="QA Live Healthcare" />
        <span>QA Live Healthcare</span>
      </div>
      <div class="menu-container">
        <a-menu v-model:selectedKeys="selectedKeys" mode="horizontal" class="nav-menu desktop-menu">
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
        </a-menu>
        <a-button type="primary" class="login-btn" @click="navigateTo('/doctor/login')">
          <UserOutlined />
          医生登录
        </a-button>
      </div>
      <a-button class="hamburger-btn" @click="toggleMenu">
        <MenuOutlined />
      </a-button>
    </div>
    <a-drawer
      placement="right"
      :closable="false"
      :visible="drawerVisible"
      @close="toggleMenu"
      width="200"
    >
      <a-menu v-model:selectedKeys="selectedKeys" mode="vertical" class="mobile-menu">
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
        <a-menu-item key="login" @click="navigateTo('/doctor/login')">
          <UserOutlined />
          医生登录
        </a-menu-item>
      </a-menu>
    </a-drawer>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { HomeOutlined, MessageOutlined, TeamOutlined, InfoCircleOutlined, UserOutlined, MenuOutlined } from '@ant-design/icons-vue';

const router = useRouter();
const route = useRoute();
const selectedKeys = ref<string[]>(['home']);
const drawerVisible = ref<boolean>(false);

const toggleMenu = () => {
  drawerVisible.value = !drawerVisible.value;
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
  drawerVisible.value = false;
};
</script>

<style scoped>
.header {
  background: #fff;
  padding: 0;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.logo {
  display: flex;
  align-items: center;
}

.logo img {
  height: 40px;
  margin-right: 10px;
}

.menu-container {
  display: flex;
  align-items: center;
  flex-grow: 1;
  justify-content: flex-end;
}

.nav-menu {
  border-bottom: none;
  flex-grow: 1;
  justify-content: flex-end;
}

.desktop-menu {
  display: flex;
  flex-wrap: nowrap;
  overflow: visible;
}

.mobile-menu {
  display: none;
}

.login-btn {
  margin-left: 20px;
}

.hamburger-btn {
  display: none;
  background: transparent;
  border: none;
  font-size: 18px;
}

@media (max-width: 768px) {
  .desktop-menu {
    display: none;
  }

  .mobile-menu {
    display: block;
  }

  .hamburger-btn {
    display: block;
  }

  .login-btn {
    display: none;
  }
}
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

.login-btn {
  background: #52c41a;
  border-color: #52c41a;
}

.login-btn:hover {
  background: #73d13d;
  border-color: #73d13d;
}
</style>
