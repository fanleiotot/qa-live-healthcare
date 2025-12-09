@echo off
REM ASDM开发环境启动脚本 (Windows版本)
REM 用途: 一键启动MySQL数据库和Spring Boot应用

echo 正在启动ASDM开发环境...

REM 检查Docker是否安装
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误: Docker未安装，请先安装Docker Desktop
    pause
    exit /b 1
)

REM 检查Docker Compose是否安装
docker-compose --version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误: Docker Compose未安装，请先安装Docker Compose
    pause
    exit /b 1
)

REM 启动MySQL数据库
echo 启动MySQL数据库...
docker-compose up -d mysql

REM 等待MySQL启动
echo 等待MySQL数据库启动...
timeout /t 30 /nobreak >nul

REM 检查MySQL是否启动成功
docker-compose ps | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo MySQL数据库启动成功!
) else (
    echo MySQL数据库启动失败，请检查日志
    pause
    exit /b 1
)

REM 启动Spring Boot应用
echo 启动Spring Boot应用...
if exist "mvnw.cmd" (
    mvnw.cmd spring-boot:run
) else (
    echo 找不到Maven Wrapper，请确保在项目根目录运行此脚本
    pause
    exit /b 1
)