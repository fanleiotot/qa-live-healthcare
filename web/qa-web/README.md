# QA Live Healthcare - 在线医疗问诊平台

## 项目概述

QA Live Healthcare 是一个专业的在线医疗问诊平台前端应用，基于 Vue 3 + TypeScript + Vite 构建。该平台连接专业医生与患者，提供便捷、高效的医疗咨询服务，支持实时在线问诊、医生管理、患者咨询等核心功能。

## 项目文件结构

```
qa-web/
├── public/                          # 静态资源目录
│   └── vite.svg                    # Vite 图标
├── src/                            # 源代码目录
│   ├── assets/                     # 静态资源
│   │   └── vue.svg                # Vue 图标
│   ├── components/                 # 公共组件
│   │   ├── AppFooter.vue          # 应用底部组件
│   │   ├── AppHeader.vue          # 应用头部导航组件
│   │   └── HelloWorld.vue         # 示例组件
│   ├── data/                      # 模拟数据
│   │   ├── doctor-user-list.json  # 医生用户数据
│   │   ├── patient-user.json      # 患者用户数据
│   │   └── question-list.json     # 问诊记录数据
│   ├── router/                    # 路由配置
│   │   └── index.ts              # 路由定义和配置
│   ├── store/                     # 状态管理
│   │   └── index.ts              # 全局状态管理（医生、患者、问诊数据）
│   ├── views/                     # 页面组件
│   │   ├── About.vue             # 关于页面
│   │   ├── Consultation.vue      # 患者问诊页面
│   │   ├── DoctorLogin.vue       # 医生登录页面
│   │   ├── DoctorRoom.vue        # 医生工作台页面
│   │   ├── Doctors.vue           # 医生列表页面
│   │   └── Home.vue              # 首页
│   ├── App.vue                   # 根组件
│   ├── main.ts                   # 应用入口文件
│   ├── style.css                 # 全局样式
│   └── vite-env.d.ts            # Vite 类型声明
├── .bolt/                        # Bolt 配置目录
│   └── config.json              # Bolt 配置文件
├── .env                         # 环境变量配置
├── .gitignore                   # Git 忽略文件配置
├── index.html                   # HTML 入口文件
├── package.json                 # 项目依赖和脚本配置
├── package-lock.json            # 依赖锁定文件
├── README.md                    # 项目说明文档
├── tsconfig.json                # TypeScript 配置
├── tsconfig.app.json            # 应用 TypeScript 配置
├── tsconfig.node.json           # Node.js TypeScript 配置
└── vite.config.ts              # Vite 构建配置
```

## 项目技术栈

### 核心框架
- **Vue 3** (v3.5.10) - 渐进式 JavaScript 框架
- **TypeScript** (v5.5.3) - JavaScript 的超集，提供静态类型检查
- **Vite** (v5.4.8) - 现代化的前端构建工具

### UI 组件库
- **Ant Design Vue** (v4.2.6) - 企业级 UI 设计语言和组件库

### 路由和状态管理
- **Vue Router** (v4.6.3) - Vue.js 官方路由管理器
- **Reactive Store** - 基于 Vue 3 reactive API 的轻量级状态管理

### 工具库
- **Day.js** (v1.11.19) - 轻量级日期处理库

### 开发工具
- **@vitejs/plugin-vue** (v5.1.4) - Vite 的 Vue 插件
- **vue-tsc** (v2.1.6) - Vue 的 TypeScript 编译器

## 核心功能模块

### 1. 用户角色管理
- **患者端**: 身份验证、问诊提交、医生选择
- **医生端**: 登录认证、问诊管理、患者回复

### 2. 问诊系统
- 实时问诊提交和管理
- 问诊状态跟踪（待回复/已回复）
- 医生-患者匹配机制

### 3. 医生管理
- 医生信息展示（姓名、职称、科室、专长）
- 医生在线状态管理
- 医生工作台界面

### 4. 数据管理
- 本地 JSON 数据模拟
- 响应式状态管理
- 数据持久化（内存级别）

## 开发调试

### 环境要求
- Node.js >= 16.0.0
- npm >= 8.0.0

### 安装依赖
```bash
npm install
```

### 开发服务器
```bash
npm run dev
```
启动开发服务器，默认运行在 `http://localhost:5173`

### 构建生产版本
```bash
npm run build
```
构建优化后的生产版本到 `dist/` 目录

### 预览生产构建
```bash
npm run preview
```
本地预览生产构建版本

## 应用管理工具

本项目提供了完整的应用管理脚本，用于在开发环境中管理Vue.js应用的后台运行。

### 管理脚本

项目提供了 `app-management.sh` 脚本，支持以下命令：

| 命令 | npm脚本 | 功能描述 |
|------|---------|---------|
| `start` | `npm run start` | 启动应用（后台运行） |
| `stop` | `npm run stop` | 停止应用 |
| `restart` | `npm run restart` | 重启应用 |
| `status` | `npm run status` | 查看应用状态 |
| `logs` | `npm run logs` | 查看应用日志 |

### 使用方法

#### 使用npm脚本（推荐）
```bash
# 启动应用
npm run start

# 查看状态
npm run status

# 查看日志
npm run logs

# 停止应用
npm run stop

# 重启应用
npm run restart
```

#### 直接使用脚本
```bash
# 给脚本添加执行权限（首次使用）
chmod +x app-management.sh

# 启动应用
./app-management.sh start

# 查看状态
./app-management.sh status

# 查看日志
./app-management.sh logs

# 停止应用
./app-management.sh stop

# 重启应用
./app-management.sh restart

# 查看帮助
./app-management.sh help
```

### 功能特性

#### 智能启动
- 自动检查端口占用情况
- **端口被占用时自动清理相关进程**
- 检测应用是否已在运行
- 等待应用完全启动并显示访问地址
- 启动失败时提供详细的错误信息

#### 安全停止
- 优雅终止进程
- 自动清理PID文件
- 必要时强制终止顽固进程
- 等待确认进程完全结束

#### 状态监控
- 显示应用运行状态和PID
- 显示内存使用情况
- 显示监听端口和访问地址
- 显示日志文件信息
- 显示最近10行日志内容

#### 日志管理
- 自动创建日志目录
- 保存完整应用日志
- 支持查看最近日志
- 提供实时监控命令提示

### 文件结构

| 文件/目录 | 说明 |
|----------|------|
| `app-management.sh` | 主管理脚本 |
| `qa-web.pid` | 存储应用进程ID |
| `logs/qa-web.log` | 应用日志文件 |

### 注意事项

1. 首次使用需要给脚本添加执行权限
2. 脚本会自动创建必要的日志目录
3. 如果端口被占用，应用会自动尝试其他端口
4. 生产环境建议使用专门的进程管理工具（如PM2）
5. 日志文件会持续增长，建议定期清理或配置日志轮转

### 故障排除

#### VSCode Remote 连接问题
如果在使用 VSCode Remote 时遇到连接中断问题，这是因为端口清理功能可能误杀了 VSCode 的端口转发进程。

**原因分析:**
- VSCode Remote 使用 SSH 隧道和端口转发进行连接
- 端口清理功能会查找并终止占用指定端口的进程
- 可能误将 VSCode 的端口转发进程识别为需要清理的进程

**解决方案:**
```bash
# 方法1: 使用交互模式（默认）
./app-management.sh start  # 会询问是否清理端口

# 方法2: 禁用自动端口清理
AUTO_CLEANUP=false ./app-management.sh start

# 方法3: 启用自动清理（适合CI/自动化环境）
AUTO_CLEANUP=true ./app-management.sh start

# 方法4: 让 Vite 自动寻找其他端口
# 直接跳过端口清理步骤，Vite会自动使用其他可用端口
```

#### 端口占用问题
```bash
# 脚本会自动检测并清理端口5173的占用（安全模式）
# 如果需要手动检查端口占用
lsof -i :5173

# 查看占用端口的进程详情（安全方式）
lsof -Pan -iTCP -sTCP:LISTEN | grep 5173

# 手动清理端口（仅开发服务器进程）
# 先确认进程类型，避免误杀系统进程
ps aux | grep -E "(node.*vite|npm.*dev)" | grep -v grep
```

#### 应用无法启动
```bash
# 检查端口占用
lsof -i :5173

# 查看详细日志
npm run logs

# 手动清理PID文件
rm -f qa-web.pid
```

#### 进程无法停止
```bash
# 查找并手动终止进程
ps aux | grep vite
kill -9 <PID>

# 清理PID文件
rm -f qa-web.pid
```

#### 权限问题
```bash
# 给脚本添加执行权限
chmod +x app-management.sh
```

## 路由结构

| 路径 | 组件 | 描述 |
|------|------|------|
| `/` | Home | 首页 - 平台介绍和快速入口 |
| `/consultation` | Consultation | 患者问诊页面 |
| `/consultation/:doctorUsername` | Consultation | 指定医生问诊页面 |
| `/doctors` | Doctors | 医生列表页面 |
| `/about` | About | 关于平台页面 |
| `/doctor/login` | DoctorLogin | 医生登录页面 |
| `/doctor/room/:username` | DoctorRoom | 医生工作台页面 |

## 数据结构

### 医生数据结构
```typescript
interface Doctor {
  id: string;           // 医生ID
  username: string;     // 用户名
  password: string;     // 密码
  name: string;         // 姓名
  title: string;        // 职称
  department: string;   // 科室
  avatar: string;       // 头像URL
  experience: string;   // 经验描述
  specialties: string[]; // 专长领域
  isActive: boolean;    // 在线状态
}
```

### 患者数据结构
```typescript
interface Patient {
  id: string;       // 患者ID
  name: string;     // 姓名
  birthday: string; // 生日
  phone: string;    // 电话
  gender: string;   // 性别
}
```

### 问诊数据结构
```typescript
interface Question {
  id: string;                    // 问诊ID
  patientId: string;            // 患者ID
  patientName: string;          // 患者姓名
  doctorId: string;             // 医生ID
  doctorName: string;           // 医生姓名
  question: string;             // 问诊内容
  submitTime: string;           // 提交时间
  status: 'pending' | 'answered'; // 状态
  answer: string | null;        // 回复内容
  answerTime: string | null;    // 回复时间
}
```

## 项目特性

### 响应式设计
- 支持桌面端和移动端适配
- 使用 Ant Design Vue 组件库确保 UI 一致性

### 类型安全
- 全面的 TypeScript 类型定义
- 编译时类型检查和错误提示

### 模块化架构
- 组件化开发模式
- 清晰的目录结构和职责分离

#### 开发体验
- Vite 提供快速的热重载
- 现代化的开发工具链

## 📚 项目文档

本项目提供了完整的技术文档，帮助开发者快速了解项目结构和开发规范：

### 核心文档
- **[项目结构规范](./docs/project-structure.md)** - 详细的文件组织规范和命名约定，所有开发者必须遵循的标准结构

### 文档说明
- 📋 **项目结构规范**: 定义了标准的文件和目录组织方式，包含详细的命名规范和扩展指南
- 🔧 **开发规范**: 确保团队协作的一致性和代码的可维护性
- 📖 **使用指南**: 为新成员提供快速上手的参考资料

> **重要提醒**: 请在开发前仔细阅读项目结构规范，严格按照规范进行文件组织和命名。
