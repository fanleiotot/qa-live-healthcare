# 标准项目结构

## 概述
本文档定义了此工作空间的标准项目结构。它提供了组织和维护文件目录的指南，以保持一致性并促进协作。

## 项目结构模板

### 通用结构
```
qa-live-healthcare/
├── .asdm/                          # ASDM配置和工具集
│   ├── contexts/                   # AI模型上下文文件
│   └── toolsets/                   # 已安装的ASDM工具集
├── server/                         # 后端微服务
│   ├── qa-service-question/       # 问题管理服务
│   │   ├── src/main/java/com/leansofx/qaservicequestion/
│   │   │   ├── QaServiceQuestionApplication.java  # 应用入口
│   │   │   └── ...                               # 其他Java类
│   │   ├── src/main/resources/
│   │   │   └── application.properties            # 应用配置
│   │   ├── pom.xml                               # Maven配置
│   │   └── mvnw                                  # Maven包装器
│   └── qa-service-user/          # 用户管理服务
│       ├── src/main/java/com/leansofx/qaserviceuser/
│       │   ├── QaServiceUserApplication.java     # 应用入口
│       │   ├── config/                           # 配置类
│       │   │   └── CorsConfig.java              # CORS配置
│       │   └── controller/                       # 控制器
│       │       └── TestController.java          # 测试控制器
│       ├── src/main/resources/
│       │   └── application.properties            # 应用配置
│       ├── pom.xml                               # Maven配置
│       └── mvnw                                  # Maven包装器
├── web/                           # 前端应用
│   └── qa-web/                    # Vue 3前端应用
│       ├── src/                   # 源代码
│       │   ├── assets/            # 静态资源
│       │   │   └── vue.svg        # Vue图标
│       │   ├── components/        # Vue组件
│       │   │   ├── AppFooter.vue  # 页脚组件
│       │   │   ├── AppHeader.vue  # 页头组件
│       │   │   └── HelloWorld.vue # 示例组件
│       │   ├── data/              # 模拟数据
│       │   │   ├── doctor-user-list.json    # 医生数据
│       │   │   ├── patient-user.json        # 患者数据
│       │   │   └── question-list.json       # 问题数据
│       │   ├── router/            # 路由配置
│       │   │   └── index.ts       # 路由定义
│       │   ├── store/             # 状态管理
│       │   │   └── index.ts       # 状态存储
│       │   ├── views/             # 页面视图
│       │   │   ├── Home.vue       # 首页
│       │   │   ├── Consultation.vue  # 问诊页面
│       │   │   ├── DoctorLogin.vue   # 医生登录
│       │   │   ├── DoctorRoom.vue    # 医生诊室
│       │   │   ├── Doctors.vue       # 医生列表
│       │   │   └── About.vue         # 关于页面
│       │   ├── App.vue            # 根组件
│       │   ├── main.ts            # 应用入口
│       │   ├── style.css          # 全局样式
│       │   └── vite-env.d.ts      # Vite环境类型
│       ├── public/                # 公共资源
│       │   └── vite.svg           # Vite图标
│       ├── package.json           # npm依赖
│       ├── package-lock.json      # 依赖锁文件
│       ├── tsconfig.json          # TypeScript配置
│       ├── vite.config.ts         # Vite配置
│       └── index.html             # HTML入口
└── _TRAINING_ASSETS/             # 训练资源
    ├── qa-service-question.zip   # 问题服务资源
    └── qa-service-user.zip       # 用户服务资源
```

## 语言特定变体

### Java/Spring Boot项目结构
```
server/qa-service-user/
├── src/main/java/com/leansofx/qaserviceuser/
│   ├── QaServiceUserApplication.java  # 应用主类
│   ├── config/                        # 配置类
│   │   └── CorsConfig.java           # CORS跨域配置
│   ├── controller/                    # REST控制器
│   │   └── TestController.java       # 测试控制器
│   ├── service/                       # 服务层（待实现）
│   ├── repository/                    # 数据仓库（待实现）
│   ├── model/                         # 实体模型（待实现）
│   └── dto/                           # 数据传输对象（待实现）
└── src/main/resources/
    └── application.properties         # Spring Boot配置
```

### TypeScript/Vue项目结构
```
web/qa-web/src/
├── components/                        # 可复用组件
│   ├── AppHeader.vue                  # 页面头部
│   ├── AppFooter.vue                  # 页面底部
│   └── HelloWorld.vue                 # 示例组件
├── views/                             # 页面级组件
│   ├── Home.vue                       # 首页
│   ├── Consultation.vue               # 患者问诊
│   ├── DoctorLogin.vue                # 医生登录
│   ├── DoctorRoom.vue                 # 医生诊室
│   ├── Doctors.vue                    # 医生列表
│   └── About.vue                      # 关于页面
├── router/                            # 路由配置
│   └── index.ts                       # 路由定义
├── store/                             # 状态管理
│   └── index.ts                       # Pinia/Vuex存储
├── data/                              # 模拟数据
│   ├── doctor-user-list.json          # 医生数据
│   ├── patient-user.json              # 患者数据
│   └── question-list.json             # 问题数据
└── utils/                             # 工具函数（待实现）
```

## 目录用途和约定

### 源代码目录
- **`server/`**: 包含所有后端微服务代码
- **`web/qa-web/`**: 包含前端Vue应用代码
- **`src/main/java/`**: Java生产源代码
- **`src/main/resources/`**: 配置文件资源
- **`src/test/`**: 测试代码（当前项目测试待完善）

### 前端特定目录
- **`src/components/`**: 可复用的Vue组件
- **`src/views/`**: 页面级Vue组件
- **`src/router/`**: 路由配置和导航
- **`src/store/`**: 应用状态管理
- **`src/data/`**: 模拟数据文件（JSON格式）

### 配置目录
- **按服务分离**: 每个微服务有自己的配置
- **外部化配置**: 使用application.properties文件
- **环境特定**: 支持不同环境配置（当前为开发环境）

## 命名约定

### 文件和目录
- 使用**kebab-case**作为目录名: `qa-service-user`, `qa-service-question`
- 使用**PascalCase**作为Java类名: `QaServiceUserApplication.java`
- 使用**PascalCase**作为Vue组件名: `AppHeader.vue`, `Consultation.vue`
- 使用**camelCase**作为TypeScript文件名: `index.ts`

### 测试文件
- Java测试文件后缀为`Test.java`: `QaServiceUserApplicationTests.java`
- TypeScript测试文件后缀为`.spec.ts`（当前项目未配置）
- 测试文件与源文件保持平行目录结构

## 最佳实践

### 1. 模块化
- 保持模块专注和内聚
- 最小化模块间依赖
- 在层之间使用清晰的接口

### 2. 可扩展性
- 结构应支持项目增长
- 避免深层嵌套（最多3-4层）
- 大型项目使用功能文件夹

### 3. 可维护性
- 类似项目间保持结构一致
- 关注点清晰分离
- 新开发者易于导航

### 4. 工具集成
- 结构应与IDE和构建工具配合
- 遵循框架约定
- 支持自动化测试和部署

## 当前项目特点

### 微服务架构
- 后端服务分离：用户服务和问题服务
- 独立构建和部署能力
- 清晰的API边界

### 前后端分离
- 前端使用现代Vue 3 + TypeScript
- 后端使用Spring Boot微服务
- 通过REST API通信

### 数据管理
- 当前使用JSON文件作为模拟数据
- 数据模型定义在TypeScript接口中
- 状态管理集中在前端store中

## 定制指南

虽然此模板提供了标准结构，但项目可能需要定制：

1. **框架要求**: 遵循特定框架约定
2. **团队偏好**: 适应团队已建立的模式
3. **项目规模**: 简化小型项目结构
4. **领域复杂性**: 根据业务领域需求调整

任何偏离标准结构的变更都应在项目README中记录。

---

*此模板应根据工作空间的特定技术栈和项目需求进行定制。*