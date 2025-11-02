# qa-service-user

## 项目描述
QA Service User 是一个后端服务。

## 已有功能
- 集成 Actuator 监控端点（/health, /info, /metrics, /env, /beans, /loggers）

## 技术栈
- **框架**: Spring Boot 3.5.7
- **数据库**: MySQL
- **构建工具**: Maven
- **监控**: Spring Boot Actuator

## 运行方式
1. 克隆项目：
   ```bash
   git clone <仓库地址>
   ```
2. 配置数据库：
   - 修改 `application.properties` 中的数据库连接信息。
3. 启动服务：
   ```bash
   mvn spring-boot:run
   ```

## 监控端点
- `/health`: 应用健康状态
- `/info`: 应用信息（名称、版本等）
- `/metrics`: 性能指标
- `/env`: 环境变量

## 注意事项
- 默认端口：`8080`
- 敏感端点（如 `/env`）需配置权限访问。