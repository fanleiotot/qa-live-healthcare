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
      <a-button type="primary" class="login-btn desktop-login" @click="navigateTo('/doctor/login')">
        <UserOutlined />
        医生登录
      </a-button>

      <!-- 移动端汉堡包菜单按钮 -->
      <a-button type="text" class="mobile-menu-btn" @click="toggleMobileMenu">
        <MenuOutlined />
      </a-button>
    </div>

    <!-- 移动端菜单抽屉 -->
    <div v-if="mobileMenuVisible" class="mobile-menu-overlay" @click="closeMobileMenu">
      <div class="mobile-menu-drawer" @click.stop>
        <div class="mobile-menu-header">
          <span>菜单</span>
          <a-button type="text" class="close-btn" @click="closeMobileMenu">
            <CloseOutlined />
          </a-button>
        </div>
        <div class="mobile-menu-content">
          <div class="mobile-menu-item" @click="navigateToMobile('/')">
            <HomeOutlined />
            <span>首页</span>
          </div>
          <div class="mobile-menu-item" @click="navigateToMobile('/consultation')">
            <MessageOutlined />
            <span>问诊</span>
          </div>
          <div class="mobile-menu-item" @click="navigateToMobile('/doctors')">
            <TeamOutlined />
            <span>医生</span>
          </div>
          <div class="mobile-menu-item" @click="navigateToMobile('/about')">
            <InfoCircleOutlined />
            <span>关于</span>
          </div>
          <a-button type="primary" class="mobile-login-btn" @click="navigateToMobile('/doctor/login')">
            <UserOutlined />
            医生登录
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
  CloseOutlined
} from '@ant-design/icons-vue';

const router = useRouter();
const route = useRoute();
const selectedKeys = ref<string[]>(['home']);
const mobileMenuVisible = ref(false);

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
  .desktop-login {
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
