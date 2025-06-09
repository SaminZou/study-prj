#!/bin/bash

# 安装依赖
# sudo apt install curl
# sudo apt install dnsutils
# sudo apt install mtr
# sudo apt install traceroute

# 终端颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

if [ -z "$1" ]; then
  echo -e "${RED}[错误] 用法: $0 <URL>${NC}"
  exit 1
fi

URL="$1"
DOMAIN=$(echo "$URL" | awk -F[/:] '{print $4}')
PORT=443

echo -e "${YELLOW}🔍 正在分析 URL: ${URL}${NC}"
echo -e "${YELLOW}🌐 提取的域名: ${DOMAIN}${NC}"
echo

# Step 1: DNS 查询
echo -e "${GREEN}1️⃣  DNS 解析检查...${NC}"
IP=$(dig +short "$DOMAIN" | grep -Eo '([0-9]{1,3}\.){3}[0-9]{1,3}' | head -n1)
if [ -n "$IP" ]; then
  echo -e "${GREEN}[✓] 成功解析：$DOMAIN => $IP${NC}"
else
  echo -e "${RED}[✗] 无法解析域名：$DOMAIN${NC}"
  exit 1
fi
echo

# Step 2: curl 请求测试
echo -e "${GREEN}2️⃣  HTTP 请求测试 (curl)...${NC}"
curl -s -o /dev/null -w "状态码: %{http_code}, 总耗时: %{time_total}s\n" "$URL"
echo

# Step 3: mtr 路由追踪（TCP 简洁版）
echo -e "${GREEN}3️⃣  路由追踪（mtr TCP 模式）...${NC}"
sudo mtr -T -P $PORT -c 5 --report "$DOMAIN" | grep -E "HOST|Loss|^\s*[0-9]+"
echo

# Step 4: traceroute 简洁输出
echo -e "${GREEN}4️⃣  路由追踪（traceroute 简版）...${NC}"
traceroute -m 10 "$DOMAIN" | head -n 10
echo