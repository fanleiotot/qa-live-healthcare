#!/bin/bash

# 定义 MySQL 连接参数
MYSQL_HOST="localhost"
MYSQL_USER="root"
MYSQL_PASS="root"
MYSQL_DB="healthcare"

# JSON 文件路径
JSON_FILE="/home/azureuser/source/tk/qa-live-healthcare-bolt-vue-c1joxy7j/server/qa-service-user/src/main/resources/data/doctor-user-list.json"

# 检查 jq 工具是否安装
if ! command -v jq &> /dev/null; then
    echo "Error: jq 工具未安装，请先安装 jq（例如：sudo apt-get install jq）"
    exit 1
fi

# 读取 JSON 文件并生成 SQL 插入语句
SQL_SCRIPT="$(mktemp)"
echo "-- 导入医生用户数据" > "$SQL_SCRIPT"

jq -r '.[] | "INSERT INTO doctor_user (id, name, username, password, department) VALUES (\"\(.id)\", \"\(.name)\", \"\(.username)\", \"\(.password)\", \"\(.department)\");"' "$JSON_FILE" >> "$SQL_SCRIPT"

# 执行 SQL 脚本
mysql --protocol=TCP -h "$MYSQL_HOST" -P 3306 -u "$MYSQL_USER" -p"$MYSQL_PASS" --default-character-set=utf8mb4 "$MYSQL_DB" < "$SQL_SCRIPT"

# 清理临时文件
rm "$SQL_SCRIPT"

echo "数据导入完成！"