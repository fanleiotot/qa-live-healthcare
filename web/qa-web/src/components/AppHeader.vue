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
      
      <!-- 桌面端登录按钮 -->
      <a-button type="primary" class="login-btn desktop-login" @click="navigateTo('/doctor/login')">
        <UserOutlined />
        医生登录
      </a-button>
      
      <!-- 移动端汉堡包菜单按钮 -->
      <a-button type="text" class="mobile-menu-btn" @click="toggleMobileMenu">
        <MenuOutlined />
      </a-button>
    </div>
    
    <!-- 移动端汉堡包菜单 -->
    <div v-if="mobileMenuVisible" class="mobile-menu-overlay" @click="closeMobileMenu">
      <div class="mobile-menu" @click.stop>
        <div class="mobile-menu-header">
          <span>菜单</span>
          <a-button type="text" class="close-btn" @click="closeMobileMenu">
            <CloseOutlined />
          </a-button>
        </div>
        <div class="mobile-menu-content">
          <a class="mobile-menu-item" @click="handleMobileNavigation('/')">
            <HomeOutlined />
            <span>首页</span>
          </a>
          <a class="mobile-menu-item" @click="handleMobileNavigation('/consultation')">
            <MessageOutlined />
            <span>问诊</span>
          </a>
          <a class="mobile-menu-item" @click="handleMobileNavigation('/doctors')">
            <TeamOutlined />
            <span>医生</span>
          </a>
          <a class="mobile-menu-item" @click="handleMobileNavigation('/about')">
            <InfoCircleOutlined />
            <span>关于</span>
          </a>
          <a-button type="primary" class="mobile-login-btn" @click="handleMobileNavigation('/doctor/login')">
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

/* 响应式设计 */
@media (max-width: 1024px) {
  /* iPad 及以下设备 */
  .desktop-menu,
  .desktop-login {
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
