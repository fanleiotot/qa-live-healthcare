# QA Web 前端项目

## 项目概述
这是一个基于 Vue 3 和 TypeScript 的前端项目，用于提供 QA 服务的用户界面。项目使用 Vite 作为构建工具，支持快速开发和高效打包。

## 技术栈
- **Vue 3**：前端框架，支持 Composition API 和 `<script setup>` 语法。
- **TypeScript**：提供类型检查和更好的开发体验。
- **Vite**：现代化的前端构建工具，支持快速热更新和高效打包。

## 关键功能
1. **用户管理**：提供用户登录、注册和个人信息管理功能。
2. **QA 服务集成**：与后端 `qa-service-user` 服务交互，实现 QA 相关功能。
3. **响应式设计**：适配不同设备屏幕，提供良好的用户体验。

## 项目结构
```
web/qa-web/
├── public/                  # 静态资源
│   ├── favicon.ico          # 网站图标
│   └── index.html           # 入口 HTML 文件
├── src/                     # 源代码目录
│   ├── assets/              # 静态资源（图片、样式等）
│   ├── components/          # 公共组件
│   ├── router/              # 路由配置
│   ├── stores/              # 状态管理（Pinia）
│   ├── views/               # 页面视图
│   ├── App.vue              # 根组件
│   └── main.ts              # 应用入口文件
├── .env                     # 环境变量配置
├── vite.config.ts           # Vite 配置文件
└── README.md                # 项目说明文档
```

## 项目配置
- **开发环境**：运行 `npm run dev` 启动开发服务器。
- **生产环境**：运行 `npm run build` 生成优化后的静态资源。

## 依赖
- `vue`：Vue 3 核心库。
- `vite`：构建工具。
- `axios`：HTTP 客户端，用于与后端 API 交互。