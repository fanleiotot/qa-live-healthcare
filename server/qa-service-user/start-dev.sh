#!/bin/bash

# ASDM开发环境启动脚本
# 用途: 一键启动MySQL数据库和Spring Boot应用

echo "正在启动ASDM开发环境..."

# 检查Docker是否安装
if ! command -v docker &> /dev/null
then
    echo "错误: Docker未安装，请先安装Docker"
    exit 1
fi

# 检查Docker Compose是否安装
if ! command -v docker-compose &> /dev/null
then
    echo "错误: Docker Compose未安装，请先安装Docker Compose"
    exit 1
fi

# 启动MySQL数据库
echo "启动MySQL数据库..."
docker-compose up -d mysql

# 等待MySQL启动
echo "等待MySQL数据库启动..."
sleep 30

# 检查MySQL是否启动成功
if docker-compose ps | grep -q "Up"; then
    echo "MySQL数据库启动成功!"
else
    echo "MySQL数据库启动失败，请检查日志"
    exit 1
fi

# 启动Spring Boot应用
echo "启动Spring Boot应用..."
if command -v mvn &> /dev/null; then
    # 使用Maven启动
    mvn spring-boot:run
else
    # 使用Maven Wrapper启动
    ./mvnw spring-boot:run
fi