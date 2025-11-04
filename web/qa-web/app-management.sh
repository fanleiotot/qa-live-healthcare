#!/bin/bash

# Define the application name and port
APP_NAME="qa-web"
PORT=5173

# Function to start the application
start() {
    echo "Starting $APP_NAME..."
    # Check if port is in use
    if lsof -i :$PORT > /dev/null; then
        echo "Port $PORT is already in use. Attempting to stop the existing process..."
        stop
    fi
    nohup npm run dev > logs/qa-web.log 2>&1 &
    echo "$APP_NAME started in the background."
}

# Function to stop the application
stop() {
    echo "Stopping $APP_NAME..."
    PID=$(lsof -t -i :$PORT)
    if [ -z "$PID" ]; then
        echo "$APP_NAME is not running."
    else
        kill -9 $PID
        echo "$APP_NAME stopped."
    fi
}

# Function to restart the application
restart() {
    stop
    start
}

# Function to check the application status
status() {
    PID=$(lsof -t -i :$PORT)
    if [ -z "$PID" ]; then
        echo "$APP_NAME is not running."
    else
        echo "$APP_NAME is running (PID: $PID)."
    fi
}

# Function to show the application logs
logs() {
    echo "Showing logs for $APP_NAME..."
    cat logs/qa-web.log
}

# Handle the command
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        status
        ;;
    logs)
        logs
        ;;
    *)
        echo "Usage: $0 {start|stop|restart|status|logs}"
        exit 1
        ;;
esac
