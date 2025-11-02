# 企业级 Vue 3 + TypeScript 项目标准化目录结构

以下是一个适用于复杂企业级项目的完整目录结构设计，支持模块化、可扩展性和团队协作。

```
web/qa-web/
├── .github/                 # GitHub 相关配置
│   ├── workflows/           # CI/CD 工作流
│   └── ISSUE_TEMPLATE/      # Issue 模板
├── .husky/                  # Git Hooks 配置
├── .vscode/                 # VSCode 配置
├── public/                  # 静态资源（不经过构建）
│   ├── favicon.ico
│   ├── robots.txt
│   └── index.html
├── src/
│   ├── api/                 # API 请求封装
│   │   ├── services/        # 按模块划分的 API
│   │   └── interceptors/    # 请求拦截器
│   ├── assets/              # 静态资源（经过构建）
│   │   ├── fonts/
│   │   ├── images/
│   │   └── styles/          # 全局样式
│   ├── components/          # 公共组件
│   │   ├── base/            # 基础组件（如 Button、Input）
│   │   ├── business/        # 业务组件
│   │   └── layout/          # 布局组件
│   ├── composables/         # Vue 组合式函数
│   ├── constants/           # 常量定义
│   ├── directives/          # 自定义指令
│   ├── enums/               # TypeScript 枚举
│   ├── hooks/               # React 风格的自定义 Hook
│   ├── i18n/                # 国际化配置
│   ├── models/              # 数据模型和 DTO
│   ├── plugins/             # Vue 插件
│   ├── router/              # 路由配置
│   │   ├── guards/          # 路由守卫
│   │   └── routes/          # 路由定义
│   ├── stores/              # 状态管理（Pinia）
│   │   ├── modules/         # 按模块划分的 Store
│   │   └── types/           # Store 类型定义
│   ├── test/                # 测试工具和配置
│   ├── utils/               # 工具函数
│   │   ├── auth/            # 认证相关工具
│   │   ├── date/            # 日期处理
│   │   └── validation/      # 表单验证
│   ├── views/               # 页面视图
│   │   ├── auth/            # 认证相关页面
│   │   ├── dashboard/       # 仪表盘
│   │   └── ...              # 其他业务模块
│   ├── App.vue              # 根组件
│   └── main.ts              # 应用入口
├── tests/                   # 测试目录
│   ├── e2e/                 # 端到端测试
│   ├── unit/                # 单元测试
│   └── integration/         # 集成测试
├── .dockerignore            # Docker 忽略文件
├── .editorconfig            # 编辑器配置
├── .env                     # 环境变量
├── .env.development
├── .env.production
├── .env.staging
├── .eslintrc.js             # ESLint 配置
├── .gitignore
├── .npmrc                   # npm 配置
├── .prettierrc.js           # Prettier 配置
├── babel.config.js          # Babel 配置
├── Dockerfile               # Docker 构建文件
├── jest.config.js           # Jest 配置
├── package.json
├── README.md
├── tsconfig.json            # TypeScript 配置
└── vite.config.ts           # Vite 配置
```

### 关键设计原则
1. **模块化**：按功能划分目录，业务逻辑与 UI 分离。
2. **可扩展性**：支持多环境配置和动态路由。
3. **测试覆盖**：单元测试、集成测试和端到端测试分离。
4. **团队协作**：统一的代码风格和 Git Hooks。
5. **企业级特性**：国际化、微前端支持预留。

此文档将作为未来开发的参考标准。