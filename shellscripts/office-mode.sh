#!/bin/bash

# apt install ddcutil

# 调试：./office.sh close && sleep 10 && ./office.sh open

set -o nounset
set -o errexit

# 设置不同的办公环境
set_office_env() {
    case "$1" in
        open)
            ddcutil setvcp D6 01
            ifconfig enp2s0 up
            echo "上班 $(date +"%Y-%m-%d %H:%M:%S")" >> office.log
            ;;
        close)
            ifconfig enp2s0 down
            # 锁屏
            # loginctl lock-session
            # 关闭屏幕
            ddcutil setvcp D6 04
            echo "下班 $(date +"%Y-%m-%d %H:%M:%S")" >> office.log
            ;;
        *)
            echo "未知变量: $1"
            echo "可用环境: open, close"
            return 1
            ;;
    esac
}

# 检查参数数量
if [ $# -ne 1 ]; then
    echo "用法: $0 {open|close}"
    exit 1
fi

# 切换办公环境
set_office_env "$1"