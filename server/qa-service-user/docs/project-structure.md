# 项目文件结构说明

以下是 `server/qa-service-user` 项目的标准化文件结构，包含当前和未来可能扩充的目录：

```
.
├── .github/                     # GitHub 相关配置
│   ├── workflows/               # CI/CD 流水线配置（如 GitHub Actions）
│   │   └── build-and-deploy.yml
│   └── ISSUE_TEMPLATE/          # Issue 模板
├── .mvn/                        # Maven 配置
│   └── wrapper/
│       └── maven-wrapper.properties
├── config/                      # 外部化配置（如 Kubernetes/DevOps 配置）
│   ├── k8s/                     # Kubernetes 部署文件
│   └── docker/                  # Docker 相关配置
├── docs/                        # 项目文档
│   ├── api/                     # API 文档（如 Swagger 导出）
│   ├── db/                      # 数据库设计文档
│   └── diagrams/                # 架构图或流程图
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── leasoftx/
│   │   │           └── qaserviceuser/
│   │   │               ├── QaServiceUserApplication.java  # 启动类
│   │   │               ├── config/            # 配置类（安全、Swagger 等）
│   │   │               ├── controller/        # REST API 入口
│   │   │               ├── service/           # 业务逻辑层
│   │   │               │   ├── impl/          # 业务实现类
│   │   │               ├── repository/       # 数据访问层（JPA/MyBatis）
│   │   │               ├── model/             # 数据模型（DTO/Entity/VO）
│   │   │               ├── exception/         # 自定义异常处理
│   │   │               ├── util/              # 工具类
│   │   │               └── aspect/            # AOP 切面（日志、权限等）
│   │   └── resources/
│   │       ├── application.yml          # 主配置文件（YAML 格式）
│   │       ├── application-dev.yml      # 开发环境配置
│   │       ├── application-prod.yml     # 生产环境配置
│   │       ├── static/                  # 静态资源
│   │       ├── templates/               # 模板文件
│   │       ├── db/                      # 数据库脚本（如 Flyway 迁移）
│   │       └── i18n/                    # 国际化资源文件
│   └── test/
│       ├── java/                       # 测试代码（分层与 main 对应）
│       └── resources/                  # 测试资源
├── target/                     # 构建输出目录（自动生成）
├── pom.xml                     # Maven 配置
├── mvnw                        # Maven Wrapper（Linux）
├── mvnw.cmd                    # Maven Wrapper（Windows）
├── Dockerfile                  # Docker 镜像构建文件
├── Jenkinsfile                 # Jenkins 流水线配置
├── .gitignore                  # Git 忽略规则
└── README.md                   # 项目说明
```

### 关键目录说明
1. **`.github/workflows`**  
   - 存放 GitHub Actions 配置，用于自动化构建、测试和部署。

2. **`config/k8s`**  
   - Kubernetes 部署文件（如 `deployment.yaml`、`service.yaml`），用于容器化部署。

3. **`src/main/java` 分层扩展**  
   - `aspect/`：AOP 切面（如日志记录、权限校验）。
   - `util/`：通用工具类（如日期处理、加密解密）。

4. **`src/main/resources/db`**  
   - 数据库迁移脚本（如 Flyway 或 Liquibase），管理数据库版本。

5. **`src/main/resources/i18n`**  
   - 国际化资源文件（如 `messages_en.properties`）。

6. **`Jenkinsfile` 和 `Dockerfile`**  
   - 用于 CI/CD 流程和容器化部署。

### 使用建议
- **按需创建目录**：根据项目需求逐步扩充，避免过度设计。
- **配置分离**：通过 `application-{env}.yml` 实现多环境配置。