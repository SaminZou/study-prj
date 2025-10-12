#!/bin/bash
# =====================================================
# top-pods-by-node.sh
# 功能：显示指定节点上 Pod 的 CPU / 内存 使用情况
# 适用：K3s
# =====================================================

set -e

usage() {
  echo "用法: $0 -n <node-name> [-l <limit>] [-s <sort>]"
  echo "  -n <node-name>   指定节点名 (必填)"
  echo "  -l <limit>       显示前 N 个 Pod (默认: 10)"
  echo "  -s <sort>        排序依据: cpu 或 mem (默认: mem)"
  exit 1
}

LIMIT=10
NODE=""
SORT_BY="mem"

while getopts ":n:l:s:" opt; do
  case $opt in
    n) NODE="$OPTARG" ;;
    l) LIMIT="$OPTARG" ;;
    s) SORT_BY="$OPTARG" ;;
    *) usage ;;
  esac
done

if [ -z "$NODE" ]; then
  usage
fi

if [[ "$SORT_BY" != "cpu" && "$SORT_BY" != "mem" ]]; then
  echo "❌ 排序方式必须是 cpu 或 mem"
  exit 1
fi

echo "正在分析节点: $NODE"
echo "排序依据: $SORT_BY"
echo "------------------------------------------------------------"

TMP_FILE=$(mktemp)

kubectl get pods -A -o wide | grep "$NODE" | awk '{print $1, $2}' | while read ns pod; do
  metrics=$(kubectl top pod -n "$ns" "$pod" --no-headers 2>/dev/null)
  if [ -n "$metrics" ]; then
    cpu=$(echo "$metrics" | awk '{print $2}')
    mem=$(echo "$metrics" | awk '{print $3}')
    echo -e "$ns\t$pod\t$cpu\t$mem" >> "$TMP_FILE"
  fi
done

# 按 CPU 或内存排序
if [ "$SORT_BY" = "cpu" ]; then
  sort -k3 -hr "$TMP_FILE" | head -n "$LIMIT"
else
  sort -k4 -hr "$TMP_FILE" | head -n "$LIMIT"
fi | awk '{printf "%-20s %-40s %-10s %-10s\n", $1, $2, $3, $4}'

rm -f "$TMP_FILE"

echo "------------------------------------------------------------"
echo "✅ 已完成，显示节点 $NODE 上按 $SORT_BY 排序的前 $LIMIT 个 Pod"
