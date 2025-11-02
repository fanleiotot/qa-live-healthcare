# QA Service User

## 项目概述
这是一个 Spring Boot 应用，用于提供 QA 服务相关的用户管理功能。

## 已实现功能
1. **Spring Boot Actuator 集成**：
   - 健康检查端点：`/actuator/health`。
   - 应用信息端点：`/actuator/info`。
   - 所有 Actuator 端点已启用。

2. **应用配置**：
   - 应用名称：`qa-service-user`。
   - 版本号：`1.0.0`。
   - 描述：`QA Service User Application`。

3. **依赖**：
   - `spring-boot-starter-web`：提供 Web 功能支持。
   - `spring-boot-starter-actuator`：提供 Actuator 监控和管理功能。