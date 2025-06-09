#!/bin/bash

# 终端颜色设置
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

# 输入检查
if [ -z "$1" ]; then
    echo -e "${RED}用法: $0 <URL>${NC}"
    exit 1
fi

URL="$1"
DOMAIN=$(echo "$URL" | awk -F[/:] '{print $4}')
PORT=443

echo -e "${GREEN}[*] 开始全链路分析: $URL${NC}"
echo -e "${GREEN}[*] 提取域名: $DOMAIN${NC}"
echo

# Step 1: DNS 查询
echo -e "${GREEN}=== DNS 查询 ===${NC}"
dig +dnssec +trace "$DOMAIN" | grep -E "ANSWER|IN\s+A|IN\s+NS"
echo

# Step 2: curl 请求时间
echo -e "${GREEN}=== curl 请求耗时分析 ===${NC}"
curl -o /dev/null -s -w \
"DNS解析时间:\t%{time_namelookup}s\n连接时间:\t%{time_connect}s\nSSL握手时间:\t%{time_appconnect}s\n请求发送时间:\t%{time_pretransfer}s\n首字节时间(TTFB):\t%{time_starttransfer}s\n总耗时:\t%{time_total}s\n" \
"$URL"
echo

# Step 3: mtr 路由追踪（TCP 模式）
echo -e "${GREEN}=== mtr 路径追踪（TCP） ===${NC}"
sudo mtr -T -P $PORT --report-wide --report-cycles 5 "$DOMAIN"
echo

# Step 4: traceroute
echo -e "${GREEN}=== traceroute 路由追踪 ===${NC}"
traceroute "$DOMAIN"
echo