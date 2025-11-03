#!/bin/bash

# QA Service User 启动脚本
# 使用nohup在后台运行Spring Boot应用

# 设置变量
APP_NAME="qa-service-user"
JAR_FILE="target/${APP_NAME}-0.0.1-SNAPSHOT.jar"
LOG_FILE="logs/${APP_NAME}.log"
PID_FILE="${APP_NAME}.pid"

# 创建日志目录
mkdir -p logs

# 函数：检查应用是否已经运行
check_if_running() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "应用已经在运行中，PID: $PID"
            return 0
        else
            echo "PID文件存在但进程不存在，清理PID文件"
            rm -f "$PID_FILE"
        fi
    fi
    return 1
}

# 函数：启动应用
start_app() {
    echo "正在启动 ${APP_NAME}..."
    
    # 检查JAR文件是否存在
    if [ ! -f "$JAR_FILE" ]; then
        echo "JAR文件不存在，正在构建..."
        ./mvnw clean package -DskipTests
        if [ $? -ne 0 ]; then
            echo "构建失败，无法启动应用"
            exit 1
        fi
    fi
    
    # 使用nohup启动应用
    nohup java -jar "$JAR_FILE" > "$LOG_FILE" 2>&1 &
    PID=$!
    
    # 保存PID
    echo $PID > "$PID_FILE"
    
    echo "应用已启动，PID: $PID"
    echo "日志文件: $LOG_FILE"
    echo "使用 './stop.sh' 停止应用"
    echo "使用 './status.sh' 查看状态"
}

# 主逻辑
if check_if_running; then
    echo "应用已在运行，无需重复启动"
else
    start_app
fi