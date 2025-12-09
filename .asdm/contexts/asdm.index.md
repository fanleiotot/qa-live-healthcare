# 工作空间上下文索引

## 概述
本文档作为AI模型理解和处理此工作空间的索引和指南。它提供了工作空间内容的结构化概览，并指导AI模型找到相关上下文。

## 工作空间信息

### 基本信息
- **工作空间名称**: qa-live-healthcare
- **描述**: 在线医疗问诊平台，连接医生和患者，提供便捷高效的医疗咨询服务
- **创建日期**: 2025-12-09
- **最后更新**: 2025-12-09

### 技术栈
- **主要语言**: Java 17, TypeScript
- **框架**: Spring Boot 3.5.7, Vue 3.5.10
- **构建工具**: Maven, npm/vite
- **数据库**: 内存数据存储（当前使用JSON文件）
- **测试框架**: JUnit, Testcontainers
- **部署平台**: 本地开发环境

### 业务上下文
- **业务领域**: 医疗健康、在线问诊
- **关键业务流程**: 
  1. 患者身份验证和问诊
  2. 医生登录和诊室管理
  3. 问题提交和回答
  4. 在线诊室会话
- **业务规则**:
  - 患者通过姓名和生日验证身份
  - 医生需要用户名和密码登录
  - 问题状态：待解答(pending)/已解答(answered)
  - 医生可以标记问题为已解答

## 工作空间结构

### 文件树结构
```
qa-live-healthcare/
├── .asdm/                          # ASDM配置和工具集
│   ├── contexts/                   # 上下文文件（本目录）
│   └── toolsets/                   # 已安装的工具集
├── server/                         # 后端服务
│   ├── qa-service-question/        # 问题服务（Spring Boot）
│   │   ├── src/main/java/com/leansofx/qaservicequestion/
│   │   ├── pom.xml                 # Maven配置
│   │   └── application.properties  # 应用配置
│   └── qa-service-user/           # 用户服务（Spring Boot）
│       ├── src/main/java/com/leansofx/qaserviceuser/
│       ├── pom.xml                 # Maven配置
│       └── application.properties  # 应用配置
├── web/                           # 前端应用
│   └── qa-web/                    # Vue 3前端
│       ├── src/                   # 源代码
│       │   ├── components/        # Vue组件
│       │   ├── views/            # 页面视图
│       │   ├── router/           # 路由配置
│       │   ├── store/            # 状态管理
│       │   └── data/             # 模拟数据
│       ├── package.json          # npm依赖
│       └── vite.config.ts        # Vite配置
└── _TRAINING_ASSETS/             # 训练资源
```

### 关键目录说明
- **`.asdm/contexts/`**: 包含所有AI模型参考的上下文文件
- **`server/`**: 后端微服务代码 - 基于Spring Boot
- **`web/qa-web/`**: 前端应用代码 - 基于Vue 3 + TypeScript
- **`src/data/`**: 模拟数据文件（医生、患者、问题列表）
- **`src/store/`**: 应用状态管理（包含业务逻辑）

## 开发指南

### 构建和编译
```bash
# 后端服务构建
cd server/qa-service-question
mvn clean package

cd server/qa-service-user
mvn clean package

# 前端开发服务器
cd web/qa-web
npm install
npm run dev

# 前端生产构建
npm run build
```

### 测试
```bash
# 后端测试
cd server/qa-service-question
mvn test

cd server/qa-service-user
mvn test

# 前端测试（需要配置测试框架）
# 当前项目未配置前端测试
```

### 代码质量
- **代码格式化**: 使用项目默认的代码风格
- **类型检查**: TypeScript严格模式
- **代码审查**: 遵循Vue和Spring Boot最佳实践

## 上下文文件参考

本工作空间在`.asdm/contexts/`目录中提供以下上下文文件：

1. **[asdm.standard-project-structure.md](./asdm.standard-project-structure.md)** - 标准项目结构和组织
2. **[asdm.standard-coding-style.md](./asdm.standard-coding-style.md)** - 编码标准和风格指南
3. **[asdm.data-models.md](./asdm.data-models.md)** - 数据模型、关系和图表
4. **[asdm.deployment.md](./asdm.deployment.md)** - 部署配置和流程
5. **[asdm.api.md](./asdm.api.md)** - API定义、端点和文档
6. **[asdm.architecture.md](./asdm.architecture.md)** - 系统架构和设计决策

## AI模型指导

### 如何使用此上下文
1. **从此索引开始**了解工作空间结构
2. **根据任务参考特定上下文文件**
3. **遵循开发指南**进行构建、测试和部署
4. **保持一致性**与现有模式和约定

### 常见任务
- **添加新功能**: 首先检查架构和数据模型
- **修改API**: 参考API文档并相应更新
- **数据库更改**: 更新数据模型和迁移脚本
- **部署更新**: 遵循部署流程文档

### 故障排除
- 如果某些功能不如预期工作，请检查相关上下文文件
- 对于构建问题，验证依赖项和配置
- 对于运行时问题，检查部署和环境配置

## 版本历史
| 版本 | 日期 | 变更 | 作者 |
|------|------|------|------|
| 1.0.0 | 2025-12-09 | 初始上下文创建 | AI助手 |

---

*此上下文文件由Context Builder工具集维护。当工作空间发生变化时，使用`/context-update-instruction`进行更新。*