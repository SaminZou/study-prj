#!/bin/bash

set -o nounset
set -o errexit

num=$1
flag=0

# 非逻辑案例， flag 不等于 1
if [ ! "$flag" -eq 1 ]; then
  echo "trigger not logic"
  # 正常结束程序
  exit 0
fi

if [ "$num" -eq 1 ]; then
  echo "1"
# 或逻辑
elif [ "$num" -eq 2 ] || [ "$num" -eq 3 ]; then
  echo "2"
# 与逻辑
elif [ "$num" -eq 4 ] && [ "$flag" -eq 1 ]; then
  echo "3"
elif [ "$num" -lt 10 ]; then
  echo "2"
elif [ "$num" -eq 99 ]; then
  # 使用 : 可以代替空代码块
  :
else
  echo "unknow"
fi