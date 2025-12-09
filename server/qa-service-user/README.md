# ASDM MySQL开发环境

本文档介绍如何使用Docker Compose搭建ASDM项目的MySQL开发环境。

## 目录

- [环境要求](#环境要求)
- [快速开始](#快速开始)
- [配置说明](#配置说明)
- [数据库管理](#数据库管理)
- [故障排除](#故障排除)

## 环境要求

- Docker 20.10+
- Docker Compose 2.0+
- 至少2GB可用内存
- 至少5GB可用磁盘空间

## 快速开始

### 1. 克隆项目并进入目录

```bash
cd server/qa-service-user
```

### 2. 启动MySQL数据库

```bash
docker-compose up -d
```

### 3. 验证数据库状态

```bash
docker-compose ps
```

输出应显示MySQL容器状态为`Up`。

### 4. 连接数据库

使用以下命令连接到数据库：

```bash
docker-compose exec mysql mysql -u asdm_dev -p asdm_dev
```

输入密码：`asdm_dev_password123`

## 配置说明

### 环境变量配置

所有数据库配置通过`.env`文件管理：

| 变量名 | 描述 | 默认值 |
|--------|------|--------|
| MYSQL_ROOT_PASSWORD | MySQL root用户密码 | rootpassword123 |
| MYSQL_DATABASE | 默认数据库名 | asdm_dev |
| MYSQL_USER | 开发用户名 | asdm_dev |
| MYSQL_PASSWORD | 开发用户密码 | asdm_dev_password123 |
| MYSQL_PORT | 主机端口 | 3306 |

### 数据库初始化

数据库初始化脚本位于`database/init/`目录：

- `01-create-database.sql`: 创建医生表和示例数据

## 数据库管理

### 启动数据库

```bash
docker-compose up -d
```

### 停止数据库

```bash
docker-compose stop
```

### 重启数据库

```bash
docker-compose restart
```

### 查看日志

```bash
docker-compose logs -f mysql
```

### 删除容器和数据

⚠️ **警告：此操作将删除所有数据！**

```bash
docker-compose down -v
```

## 应用连接配置

### Spring Boot应用配置

在`application.properties`或`application.yml`中配置数据库连接：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/asdm_dev?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai
spring.datasource.username=asdm_dev
spring.datasource.password=asdm_dev_password123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

或直接使用`.env`文件中的变量：

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=${SPRING_DATASOURCE_DRIVER_CLASS_NAME}
```

## 故障排除

### 端口冲突

如果3306端口已被占用，可以修改`.env`文件中的`MYSQL_PORT`变量：

```bash
# 使用3307端口
MYSQL_PORT=3307
```

### 数据库连接失败

1. 检查容器状态：`docker-compose ps`
2. 查看容器日志：`docker-compose logs mysql`
3. 确保密码正确，检查`.env`文件中的配置

### 数据持久化问题

数据存储在Docker卷`mysql_data`中。如果需要备份数据：

```bash
# 备份数据
docker-compose exec mysql mysqldump -u root -p asdm_dev > backup.sql

# 恢复数据
docker-compose exec -T mysql mysql -u root -p asdm_dev < backup.sql
```

### 容器启动失败

1. 检查Docker是否正常运行：`docker version`
2. 确保有足够的系统资源
3. 查看容器日志获取详细错误信息：`docker-compose logs mysql`

## 数据库结构

### 医生表 (doctor)

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | bigint | 主键，自增 |
| name | varchar(100) | 医生姓名 |
| title | varchar(50) | 职称 |
| department | varchar(100) | 科室 |
| hospital | varchar(200) | 医院 |
| specialization | varchar(200) | 专业领域 |
| license_number | varchar(50) | 执业证号(唯一) |
| phone | varchar(20) | 联系电话 |
| email | varchar(100) | 电子邮箱 |
| years_of_experience | int | 从业年限 |
| status | tinyint | 状态: 1-正常, 0-禁用 |
| created_at | timestamp | 创建时间 |
| updated_at | timestamp | 更新时间 |

## 开发指南

### 添加新的初始化脚本

1. 在`database/init/`目录创建新的SQL文件
2. 使用数字前缀指定执行顺序，如`02-new-table.sql`
3. 重启数据库容器应用新脚本

### 数据库版本管理

建议使用数据库迁移工具(如Flyway或Liquibase)管理生产环境的数据库变更。初始化脚本仅用于开发环境快速搭建。

## 相关资源

- [Docker Compose官方文档](https://docs.docker.com/compose/)
- [MySQL Docker官方镜像](https://hub.docker.com/_/mysql)
- [ASDM项目文档](../../../README.md)
- [ASDM架构文档](../../../contexts/asdm.architecture.md)