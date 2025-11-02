# Vue 3 + TypeScript + Vite + Ant Design Vue

本项目是一个基于 Vue 3、TypeScript 和 Vite 的开发模板，使用 Ant Design Vue 作为 UI 框架。

## 项目结构

```
web/qa-web/
├── .env                  # 环境变量配置文件
├── .gitignore           # Git 忽略规则
├── index.html           # 应用入口 HTML 文件
├── package.json         # 项目依赖和脚本配置
├── package-lock.json    # 依赖版本锁定文件
├── tsconfig.json        # TypeScript 全局配置
├── tsconfig.app.json    # 应用 TypeScript 配置
├── tsconfig.node.json   # Node 环境 TypeScript 配置
├── vite.config.ts       # Vite 构建配置
├── public/              # 公共静态资源
│   └── ...
└── src/                 # 源代码目录
    ├── App.vue          # 主应用布局组件，包含头部、内容区域和底部
    ├── main.ts          # 应用入口文件，集成 Ant Design Vue 和路由配置
    ├── style.css        # 全局样式
    ├── vite-env.d.ts    # TypeScript 环境声明
    ├── assets/          # 静态资源（图片、字体等）
    ├── components/      # 可复用的公共组件
    ├── data/            # 模拟数据或数据配置文件
    ├── router/          # 路由配置
    ├── store/           # 状态管理（如 Vuex/Pinia）
    └── views/           # 页面组件
```

## 开发指南

### 运行开发服务器
```bash
npm run dev
```

### 构建生产版本
```bash
npm run build
```

### 预览生产版本
```bash
npm run preview
```

## 依赖说明

### 核心依赖
- `vue`：Vue 3 框架。
- `vue-router`：路由库。
- `ant-design-vue`：UI 组件库。
- `dayjs`：日期处理库。

### 开发依赖
- `@vitejs/plugin-vue`：Vite 的 Vue 插件。
- `typescript`：TypeScript 支持。
- `vite`：构建工具。
- `vue-tsc`：Vue 的 TypeScript 检查工具。