#!/bin/bash

# 检查是否至少有一个参数
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 username1 [username2 ...]"
    exit 1
fi

# 循环处理所有提供的用户名
for user in "$@"
do
    # 生成8位随机密码
    password=$(pwgen 8 1)

    # 创建用户并指定密码
    echo "$user:$password"
    echo "$user:$password" | sudo chpasswd

    echo "密码已经重置 '$user' : '$password'"
done