#!/bin/bash

# 批量增加用户

# 检查是否至少有一个参数
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 username1 [username2 ...]"
    exit 1
fi

# 循环处理所有提供的用户名
for user in "$@"
do
    # 创建用户并指定密码
    password=$(pwgen 8 1)
    sudo adduser --disabled-password --gecos "" "$user"
    echo "$user:$password"
    echo "$user:$password" | sudo chpasswd

    # 添加用户到sudo组
    sudo usermod -aG sudo "$user"

    # 如果创建成功，输出确认消息
    if [ $? -eq 0 ]; then
        echo "User '$user' created successfully with password set."
    else
        echo "Failed to create user '$user'."
    fi
done

# 注意敏感文件夹的保护，比如需要保护 /mnt 不被新用户访问，可以配置 chmod 700 /mnt