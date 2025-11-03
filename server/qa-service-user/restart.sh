#!/bin/bash

# QA Service User 重启脚本

APP_NAME="qa-service-user"

echo "正在重启 ${APP_NAME}..."

# 停止应用
./stop.sh

# 等待2秒确保进程完全结束
sleep 2

# 启动应用
./start.sh

echo "重启完成"