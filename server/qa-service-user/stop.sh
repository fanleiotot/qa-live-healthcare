#!/bin/bash

# QA Service User 停止脚本

APP_NAME="qa-service-user"
PID_FILE="${APP_NAME}.pid"

# 函数：停止应用
stop_app() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "正在停止 ${APP_NAME} (PID: $PID)..."
            kill "$PID"
            
            # 等待进程结束
            for i in {1..10}; do
                if ! ps -p "$PID" > /dev/null 2>&1; then
                    echo "应用已成功停止"
                    rm -f "$PID_FILE"
                    exit 0
                fi
                sleep 1
            done
            
            # 如果进程仍然存在，强制终止
            echo "正常停止失败，强制终止进程..."
            kill -9 "$PID"
            rm -f "$PID_FILE"
            echo "应用已强制停止"
        else
            echo "进程不存在，清理PID文件"
            rm -f "$PID_FILE"
        fi
    else
        echo "PID文件不存在，应用可能未运行"
    fi
}

stop_app