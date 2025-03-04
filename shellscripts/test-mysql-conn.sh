#/bin/bash

# 密码不能有空格，如果密码存在特殊字符，使用 \ 转移
# $ mysql -h samin.dev -u root -psamin\@123 -e "SELECT 1" study

#!/bin/bash

# 数据库连接信息
DB_HOST="samin.dev"
DB_USER="root"
DB_PORT="3306"
DB_PASSWORD="samin@123"
DB_NAME="study"

# 尝试连接数据库并执行简单查询
if mysql -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASSWORD" -e "SELECT 1" "$DB_NAME" > /dev/null 2>&1; then
    echo "数据库连接正常。"
else
    echo "数据库连接失败，请检查网络或数据库配置。"
fi