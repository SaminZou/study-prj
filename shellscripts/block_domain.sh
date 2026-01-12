#!/usr/bin/env bash

# 基于 Ubuntu 24.04，根据域名解析 IP，并使用 iptables 永久封禁出站访问
# 依赖：dig, iptables, iptables-persistent

set -euo pipefail

DOMAIN="${1:-}"

if [ -z "$DOMAIN" ]; then
  echo "用法: $0 <domain>"
  exit 1
fi

# 必须 root
if [ "$EUID" -ne 0 ]; then
  echo "请使用 root 或 sudo 运行"
  exit 1
fi

# 检查 dig
if ! command -v dig >/dev/null 2>&1; then
  echo "缺少 dig，请先安装: apt install -y dnsutils"
  exit 1
fi

# 检查 iptables-persistent
if ! dpkg -l | grep -q iptables-persistent; then
  echo "未安装 iptables-persistent，正在安装..."
  apt update -y
  DEBIAN_FRONTEND=noninteractive apt install -y iptables-persistent
fi

echo "[*] 域名解析: $DOMAIN"

IPS=$(dig +short "$DOMAIN" A | grep -E '^[0-9\.]+$' | sort -u)

if [ -z "$IPS" ]; then
  echo "[!] 未解析到 IPv4 地址"
  exit 1
fi

for IP in $IPS; do
  if iptables -C OUTPUT -d "$IP" -j DROP 2>/dev/null; then
    echo "[=] 已存在规则: DROP $IP"
  else
    iptables -I OUTPUT -d "$IP" -j DROP
    echo "[+] 新增规则: DROP $IP"
  fi
done

echo "[*] 保存 iptables 规则（永久生效）"
netfilter-persistent save

echo "[✓] 完成：$DOMAIN 已被永久封禁（出站）"