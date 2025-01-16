#!/bin/bash

set -o nounset
set -o errexit

# 检测操作系统
detect_os() {
    if [ -f /etc/os-release ]; then
        . /etc/os-release
        OS=$PRETTY_NAME
    elif [ -f /etc/redhat-release ]; then
        OS=$(< /etc/redhat-release)
    elif [ -f /etc/debian_version ]; then
        OS="Debian $(cat /etc/debian_version)"
    elif [ -f /etc/issue ]; then
        OS=$(< /etc/issue)
    else
        OS="未知"
    fi
}

# 根据操作系统执行不同的指令
execute_commands() {
    if [[ "$OS" == *"Ubuntu"* ]]; then
        echo "这是 Ubuntu 系统"
        # 在这里执行 Ubuntu 系统的指令
    elif [[ "$OS" == *"CentOS"* ]]; then
        echo "这是 CentOS 系统"
        # 在这里执行 CentOS 系统的指令
    elif [[ "$OS" == *"Debian"* ]]; then
        echo "这是 Debian 系统"
        # 在这里执行 Debian 系统的指令
    else
        echo "无法识别操作系统"
    fi
}

# 调用函数
detect_os
execute_commands