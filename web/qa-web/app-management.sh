#!/bin/bash

# QA Web 应用管理脚本
# 提供 start, stop, restart, status, logs 功能

# 设置变量
APP_NAME="qa-web"
PID_FILE="${APP_NAME}.pid"
LOG_FILE="logs/${APP_NAME}.log"
LOGS_DIR="logs"
AUTO_CLEANUP=${AUTO_CLEANUP:-false}  # 是否自动清理端口，默认false

# 显示帮助信息
show_help() {
    echo "QA Web 应用管理工具"
    echo "用法: $0 {start|stop|restart|status|logs}"
    echo ""
    echo "命令:"
    echo "  start   - 启动应用（后台运行）"
    echo "  stop    - 停止应用"
    echo "  restart - 重启应用"
    echo "  status  - 查看应用状态"
    echo "  logs    - 查看应用日志"
    echo ""
    echo "环境变量:"
    echo "  AUTO_CLEANUP=true  # 设置自动清理端口（默认需要确认）"
    echo ""
    echo "示例:"
    echo "  $0 start                    # 启动应用"
    echo "  AUTO_CLEANUP=true $0 start  # 启动应用并自动清理端口"
    echo "  $0 status                   # 查看状态"
    echo "  $0 logs                     # 查看日志"
}

# 安全地检查端口占用情况
check_port_safe() {
    local port=$1
    local pids=$(lsof -ti :${port} 2>/dev/null)
    
    if [ -n "$pids" ]; then
        echo "端口 ${port} 被以下进程占用:"
        for pid in $pids; do
            local cmd=$(ps -p $pid -o command= 2>/dev/null)
            local ppid=$(ps -p $pid -o ppid= 2>/dev/null | tr -d ' ')
            local user=$(ps -p $pid -o user= 2>/dev/null)
            
            # 判断是否为安全的开发进程
            local is_safe=false
            if echo "$cmd" | grep -qE "(node.*vite|npm.*dev|yarn.*dev)"; then
                is_safe=true
            fi
            
            echo "  PID: $pid (PPID: $ppid)"
            echo "  用户: $user"
            echo "  命令: $cmd"
            echo "  类型: $([ "$is_safe" = true ] && echo "开发服务器" || echo "其他进程")"
            echo ""
        done
        return 0
    else
        echo "端口 ${port} 未被占用"
        return 1
    fi
}

# 检查应用是否正在运行
check_if_running() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            return 0
        else
            # 进程不存在但PID文件存在，清理PID文件
            rm -f "$PID_FILE"
        fi
    fi
    return 1
}

# 智能清理端口占用
cleanup_port() {
    local port=$1
    echo "正在检查端口 ${port} 的占用情况..."
    
    # 查找占用端口的进程（排除SSH相关进程）
    local pids=$(lsof -ti :${port} 2>/dev/null)
    
    if [ -n "$pids" ]; then
        echo "发现端口 ${port} 被以下进程占用:"
        local safe_pids=""
        
        for pid in $pids; do
            local cmd=$(ps -p $pid -o command= 2>/dev/null)
            echo "  PID: $pid, 命令: $cmd"
            
            # 检查进程类型，避免误杀系统进程
            if echo "$cmd" | grep -qE "(ssh|vscode|node.*vite|npm)"; then
                if echo "$cmd" | grep -qE "(ssh|vscode)"; then
                    echo "    ⚠️  检测到可能是VSCode或SSH相关进程，跳过终止"
                    continue
                fi
            fi
            
            # 只处理看起来像是开发服务器的进程
            if echo "$cmd" | grep -qE "(node.*vite|npm.*dev|yarn.*dev)"; then
                safe_pids="$safe_pids $pid"
            fi
        done
        
        if [ -n "$safe_pids" ]; then
            echo "正在安全地清理开发服务器进程..."
            for pid in $safe_pids; do
                if kill -0 $pid 2>/dev/null; then
                    echo "正在优雅地终止开发进程 $pid..."
                    kill $pid
                    sleep 1
                    
                    # 如果进程仍然存在，强制终止
                    if kill -0 $pid 2>/dev/null; then
                        echo "强制终止进程 $pid..."
                        kill -9 $pid
                    fi
                fi
            done
            
            # 等待端口释放
            sleep 2
            echo "端口 ${port} 清理完成"
        else
            echo "没有找到可以安全终止的开发进程"
            return 1
        fi
    else
        echo "端口 ${port} 未被占用"
    fi
}

# 启动应用
start_app() {
    echo "正在启动 ${APP_NAME}..."
    
    # 检查是否已在运行
    if check_if_running; then
        PID=$(cat "$PID_FILE")
        echo "应用已经在运行中，PID: $PID"
        return 1
    fi
    
    # 创建日志目录
    mkdir -p "$LOGS_DIR"
    
    # 检查端口是否被占用
    if lsof -Pi :5173 -sTCP:LISTEN -t >/dev/null 2>&1; then
        echo "发现端口 5173 被占用"
        
        if [ "$AUTO_CLEANUP" = "true" ]; then
            echo "自动清理模式已启用，正在安全清理端口..."
            cleanup_port 5173
            
            # 再次检查端口是否已释放
            if lsof -Pi :5173 -sTCP:LISTEN -t >/dev/null 2>&1; then
                echo "⚠️  端口 5173 仍然被占用，应用将尝试使用其他端口"
                echo "   Vite 会自动寻找可用端口"
            fi
        else
            # 先尝试温和的方式：询问用户是否清理
            echo "是否尝试清理端口占用？(y/N): "
            read -t 5 -n 1 response  # 5秒内等待用户输入
            echo ""
            
            if [[ "$response" =~ ^[Yy]$ ]]; then
                cleanup_port 5173
                
                # 再次检查端口是否已释放
                if lsof -Pi :5173 -sTCP:LISTEN -t >/dev/null 2>&1; then
                    echo "⚠️  端口 5173 仍然被占用，应用将尝试使用其他端口"
                    echo "   Vite 会自动寻找可用端口"
                fi
            else
                echo "跳过端口清理，Vite 将自动寻找可用端口"
            fi
        fi
    fi
    
    # 使用npm run dev启动应用
    echo "正在构建并启动开发服务器..."
    nohup npm run dev > "$LOG_FILE" 2>&1 &
    PID=$!
    
    # 保存PID
    echo $PID > "$PID_FILE"
    
    echo "应用启动命令已发送，PID: $PID"
    echo "日志文件: $LOG_FILE"
    echo ""
    echo "等待应用启动..."
    
    # 等待应用启动
    for i in {1..10}; do
        if grep -q "Local:" "$LOG_FILE" 2>/dev/null; then
            echo "✅ 应用启动成功！"
            if grep -q "5173" "$LOG_FILE" 2>/dev/null; then
                echo "应用运行在: http://localhost:5173"
            fi
            break
        fi
        sleep 1
        echo -n "."
    done
    echo ""
    
    # 检查最终状态
    if ! ps -p "$PID" > /dev/null 2>&1; then
        echo "❌ 应用启动失败，请查看日志:"
        echo "tail -n 20 $LOG_FILE"
        rm -f "$PID_FILE"
        return 1
    fi
}

# 停止应用
stop_app() {
    echo "正在停止 ${APP_NAME}..."
    
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "正在终止进程 (PID: $PID)..."
            kill "$PID"
            
            # 等待进程结束
            for i in {1..10}; do
                if ! ps -p "$PID" > /dev/null 2>&1; then
                    echo "✅ 应用已成功停止"
                    rm -f "$PID_FILE"
                    return 0
                fi
                sleep 1
                echo -n "."
            done
            echo ""
            
            # 如果进程仍然存在，强制终止
            echo "正常停止失败，强制终止进程..."
            kill -9 "$PID" 2>/dev/null
            rm -f "$PID_FILE"
            echo "✅ 应用已强制停止"
        else
            echo "进程不存在，清理PID文件"
            rm -f "$PID_FILE"
        fi
    else
        echo "❌ 应用未运行 (无PID文件)"
    fi
}

# 重启应用
restart_app() {
    echo "正在重启 ${APP_NAME}..."
    stop_app
    echo ""
    sleep 2
    start_app
}

# 查看状态
show_status() {
    echo "=== ${APP_NAME} 应用状态 ==="
    
    if check_if_running; then
        PID=$(cat "$PID_FILE")
        echo "✅ 应用正在运行"
        echo "   PID: $PID"
        echo "   启动时间: $(ps -p $PID -o lstart= 2>/dev/null || echo '未知')"
        echo "   内存使用: $(ps -p $PID -o rss= 2>/dev/null | awk '{print $1/1024 " MB"}' || echo '未知')"
        
        # 检查端口（安全方式）
        local port_info=$(lsof -Pan -iTCP -sTCP:LISTEN 2>/dev/null | grep "$PID" | head -1)
        if [ -n "$port_info" ]; then
            local port=$(echo "$port_info" | awk -F'[.:]' '{print $(NF-1)}')
            if [ -n "$port" ]; then
                echo "   监听端口: $port"
                echo "   访问地址: http://localhost:$port"
            fi
        fi
        
        # 检查npm进程
        if ps -p "$PID" -o command= | grep -q "npm"; then
            echo "   进程类型: npm"
        fi
    else
        echo "❌ 应用未运行"
    fi
    
    # 检查日志文件
    if [ -f "$LOG_FILE" ]; then
        echo ""
        echo "📋 日志信息:"
        echo "   日志文件: $LOG_FILE"
        echo "   文件大小: $(du -h "$LOG_FILE" 2>/dev/null | cut -f1)"
        echo "   最后修改: $(stat -c %y "$LOG_FILE" 2>/dev/null | cut -d'.' -f1)"
        
        # 显示最后几行日志
        echo "   最近日志:"
        echo "   ------------------------"
        tail -n 10 "$LOG_FILE" 2>/dev/null | sed 's/^/   /'
    else
        echo "   日志文件: 不存在"
    fi
    
    # 检查PID文件
    if [ -f "$PID_FILE" ]; then
        echo ""
        echo "📁 文件状态:"
        echo "   PID文件: $PID_FILE (存在)"
    fi
}

# 查看日志
show_logs() {
    if [ -f "$LOG_FILE" ]; then
        echo "📋 查看 ${APP_NAME} 日志 (最后50行)"
        echo "日志文件: $LOG_FILE"
        echo "================================"
        tail -n 50 "$LOG_FILE"
        echo "================================"
        echo ""
        echo "实时查看日志: tail -f $LOG_FILE"
    else
        echo "❌ 日志文件不存在: $LOG_FILE"
        echo "应用可能尚未启动或运行失败"
    fi
}

# 主程序
if [ $# -eq 0 ]; then
    show_help
    exit 0
fi

case "$1" in
    start)
        start_app
        ;;
    stop)
        stop_app
        ;;
    restart)
        restart_app
        ;;
    status)
        show_status
        ;;
    logs)
        show_logs
        ;;
    help|--help|-h)
        show_help
        ;;
    *)
        echo "❌ 未知命令: $1"
        echo ""
        show_help
        exit 1
        ;;
esac