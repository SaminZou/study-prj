#!/bin/bash

set -o nounset
set -o errexit

: > server.info

# ===============================
# CPU 信息
# ===============================

# 物理 CPU 个数
cpuNum=$(awk '/physical id/ {a[$4]=1} END {print length(a)}' /proc/cpuinfo)
echo "# 物理 CPU 个数：$cpuNum" >> server.info

# 每个 CPU 的核心数（取第一行即可）
cpuCoreNum=$(awk -F: '/cpu cores/ {gsub(/ /,"",$2); print $2; exit}' /proc/cpuinfo)
echo "# 每颗物理 CPU 核心数：$cpuCoreNum" >> server.info

# 逻辑 CPU 个数
logicalCpuNum=$(awk '/^processor/ {n++} END {print n}' /proc/cpuinfo)
echo "# 逻辑 CPU 核数：$logicalCpuNum" >> server.info
echo "" >> server.info

# ===============================
# 内存信息
# ===============================
echo "# 内存信息：" >> server.info
free -h | awk 'NR==2 {print "总量:", $2, "\t已用:", $3, "\t可用:", $7}' >> server.info
echo "" >> server.info

# ===============================
# 硬盘信息
# ===============================
echo "# 硬盘信息：" >> server.info
printf "%-30s %-10s %-6s\n" "挂载点" "总容量" "使用率" >> server.info
df -h --output=target,size,pcent -x tmpfs -x devtmpfs | \
awk 'NR>1 {print $2, $3, $1}' | sort -hr | \
awk '{printf "%-30s %-10s %-6s\n", $3, $1, $2}' >> server.info
echo "" >> server.info

# ===============================
# CPU 主频信息
# ===============================
# CPU 基准主频（最大频率）
cpuGHz=$(awk '{printf "%.2f", $1/1000000}' /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq)
echo "# CPU 基准主频：${cpuGHz} GHz" >> server.info

# CPU 当前平均主频
cpuAvgGHz=$(awk -F: '/cpu MHz/ {sum+=$2; n++} END {printf "%.2f", sum/n/1000}' /proc/cpuinfo)
echo "# CPU 当前平均主频：${cpuAvgGHz} GHz" >> server.info
echo "" >> server.info

# CPU 实时运行频率（每个逻辑 CPU）
echo "# CPU 实时运行频率（每个逻辑 CPU）：" >> server.info
awk -F: '/MHz/ {++i; gsub(/^[ \t]+/, "", $2); printf "%d: cpu MHz: %s\n", i, $2}' /proc/cpuinfo >> server.info
