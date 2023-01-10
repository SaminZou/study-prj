#!/bin/bash

set -o nounset
set -o errexit

# 物理 CPU
# 物理 CPU 数是指实际 Server 中插槽上的 CPU 个数
# 物理核数是指一个 CPU 上的物理核心数

echo "# 物理 CPU 个数：$(cat /proc/cpuinfo| grep "physical id"| sort | uniq | wc -l)" > server.info
echo "" >> server.info

# 每个 CPU 上有一到多个物理核
echo "# 物理 CPU 核数：$(cat /proc/cpuinfo | grep "cpu cores" | uniq)" >> server.info
echo "" >> server.info

# 物理总核数 = 物理 CPU 个数 * 每个物理 CPU 的核

# 逻辑 CPU
# 逻辑 CPU 是指处理器单元，它可以在与其它逻辑 CPU 并行执行
# 一般所说的 CPU 核数是指逻辑 CPU 数
# 总逻辑 CPU 数 = 物理 CPU 个数 * 每颗物理 CPU 的核数 * 超线程数

echo "# 逻辑 CPU 核数：$(cat /proc/cpuinfo | grep "processor" | wc -l)" >> server.info
echo "" >> server.info

# 如果采用了 Intel 的超线程技术（ HT )，则上面公式的超线程数 = 2 即总逻辑 CPU 数为物理总核数的两倍

# 内存信息

echo "# 内存信息：" >> server.info
free -mh | sed -n 1,2p >> server.info
echo "" >> server.info

# 硬盘信息

echo "# 硬盘信息：" >> server.info
lsblk >> server.info
echo "" >> server.info