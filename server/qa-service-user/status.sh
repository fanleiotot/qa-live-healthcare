#!/bin/bash

# QA Service User 状态检查脚本

APP_NAME="qa-service-user"
JAR_FILE="target/${APP_NAME}-0.0.1-SNAPSHOT.jar"
LOG_FILE="logs/${APP_NAME}.log"
PID_FILE="${APP_NAME}.pid"

# 函数：检查应用状态
check_status() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "✅ 应用正在运行"
            echo "   PID: $PID"
            echo "   启动时间: $(ps -p $PID -o lstart=)"
            echo "   内存使用: $(ps -p $PID -o rss= | awk '{print $1/1024 " MB"}')"
            echo "   日志文件: $LOG_FILE"
            
            # 检查端口是否监听
            PORT=$(netstat -tlnp 2>/dev/null | grep "$PID" | awk '{print $4}' | cut -d':' -f2)
            if [ -n "$PORT" ]; then
                echo "   监听端口: $PORT"
            fi
        else
            echo "❌ 应用未运行 (PID文件存在但进程不存在)"
            echo "   PID文件: $PID_FILE"
        fi
    else
        echo "❌ 应用未运行 (无PID文件)"
    fi
    
    # 检查JAR文件
    if [ -f "$JAR_FILE" ]; then
        echo "   JAR文件: $JAR_FILE"
        echo "   文件大小: $(du -h $JAR_FILE | cut -f1)"
        echo "   修改时间: $(stat -c %y $JAR_FILE)"
    else
        echo "   JAR文件: 不存在"
    fi
    
    # 检查日志文件
    if [ -f "$LOG_FILE" ]; then
        echo "   日志文件: $LOG_FILE"
        echo "   日志大小: $(du -h $LOG_FILE | cut -f1)"
        echo "   最后10行日志:"
        echo "   ------------------------"
        tail -n 10 "$LOG_FILE" | sed 's/^/   /'
    else
        echo "   日志文件: 不存在"
    fi
}

check_status