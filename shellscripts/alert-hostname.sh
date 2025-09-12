#!/bin/bash

#===========================================================
# 脚本功能: 修改主机名并更新 /etc/hosts
# 使用方式: sudo ./set_hostname.sh NEW_HOSTNAME
#===========================================================

set -o errexit
set -o nounset
set -o pipefail

#================ 配置区域 =================
NEW_HOSTNAME="${1:-}"          # 从参数获取新主机名
NETWORK_PREFIX="192.168."      # 内网网段前缀，可根据实际环境调整
HOSTS_FILE="/etc/hosts"
#==========================================

# 输出函数
log() {
    echo -e "[INFO] $*"
}

error_exit() {
    echo -e "[ERROR] $*" >&2
    exit 1
}

#================ 权限检测 =================
if [[ "$(id -u)" -ne 0 ]]; then
    error_exit "请使用 root 用户运行该脚本！"
fi

#================ 参数检查 =================
if [[ -z "$NEW_HOSTNAME" ]]; then
    error_exit "缺少主机名参数！用法: $0 NEW_HOSTNAME"
fi

#================ 修改主机名 =================
CURRENT_HOSTNAME="$(hostname)"
log "修改前 hostname: $CURRENT_HOSTNAME"

hostnamectl set-hostname "$NEW_HOSTNAME"

log "修改后 hostname: $(hostname)"

#================ 更新 /etc/hosts =================
# 获取当前主机内网 IP
LOCAL_IP=$(ip -4 addr show | awk -v prefix="$NETWORK_PREFIX" '
    $1 == "inet" && $2 ~ "^"prefix {
        split($2, a, "/"); print a[1]; exit
    }')

if [[ -z "$LOCAL_IP" ]]; then
    error_exit "未找到符合前缀 ${NETWORK_PREFIX} 的内网 IP，请检查网络配置！"
fi

# 如果已存在旧记录则删除
sed -i.bak "/[[:space:]]${CURRENT_HOSTNAME}$/d" "$HOSTS_FILE"
sed -i "/[[:space:]]${NEW_HOSTNAME}$/d" "$HOSTS_FILE"

# 添加新记录
echo "$LOCAL_IP $NEW_HOSTNAME" >> "$HOSTS_FILE"

log "已更新 /etc/hosts，最后一条记录为: $(tail -n 1 $HOSTS_FILE)"

#================ 脚本完成 =================
log "✅ 主机名和 hosts 修改完成！"
