#!/bin/bash

set -o nounset
set -o errexit

echo "修改前 hostname：$(hostnamectl status | head -n 1 | awk '{print $3}')"

# 修改 hostname

hostnamectl set-hostname $1

# 查看修改结果

echo "修改后 hostname：$(hostname)"

# 设置 hostname 解析

echo "修改前 hosts 记录：$(cat /etc/hosts | sed -e :a -e '/^\n*$/{$d;N;ba' -e '}' | tail -n 1)"

# ！此行主要脚本应用的时候，注意内网地址开头部分是否为 192.168，按需修改
echo "$(ip addr | awk '$1 == "inet" && $2 ~ /^192\.168\./ {split($2, a, "/"); print a[1]}') $(hostname)" >> /etc/hosts

echo "最新 hosts 记录：$(cat /etc/hosts | sed -e :a -e '/^\n*$/{$d;N;ba' -e '}' | tail -n 1)"