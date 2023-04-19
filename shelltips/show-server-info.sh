#!/bin/bash

set -o nounset
set -o errexit

# 物理 CPU
# 物理 CPU 数是指实际 Server 中插槽上的 CPU 个数
cpuNum=$(cat /proc/cpuinfo| grep "physical id"| sort | uniq | wc -l | tr -cd "[0-9]" | sed s/[[:space:]]//g)
echo "# 物理 CPU 个数：$cpuNum" > server.info
# tr -cd "[0-9]" 用于提取有效数字
# | sed s/[[:space:]]//g 用于去除空格

# 物理核数是指一个 CPU 上的物理核心数，每个 CPU 上有一到多个物理核
cpuCoreNum=$(cat /proc/cpuinfo | grep "cpu cores" | uniq | tr -cd "[0-9]" | sed s/[[:space:]]//g)
echo "# 物理 CPU 核数：$cpuCoreNum" >> server.info

# 物理总核数 = 物理 CPU 个数 * 每个物理 CPU 的核
echo "# 物理 CPU 总核数：$(($cpuNum*$cpuCoreNum))" >> server.info

# 逻辑 CPU（指处理器单元，它可以在与其它逻辑 CPU 并行执行）
# 一般所说的 CPU 核数是指逻辑 CPU 数
# 总逻辑 CPU 数 = 物理 CPU 个数 * 每颗物理 CPU 的核数 * 超线程数（ HT )
echo "# 逻辑 CPU 核数：$(cat /proc/cpuinfo | grep "processor" | wc -l)" >> server.info
echo "" >> server.info

# 内存信息
echo "# 内存信息：" >> server.info
free -mh | sed -n 1,2p >> server.info
echo "" >> server.info

# 硬盘信息
echo "# 硬盘信息：" >> server.info
lsblk >> server.info
echo "" >> server.info

# 主频信息，每个 CPU 会有一个主频信息
echo "# 主频信息：" >> server.info
cat /proc/cpuinfo | grep MHz | uniq >> server.info
echo "" >> server.info