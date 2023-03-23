#!/bin/bash

set -o nounset
set -o errexit

num=$1
flag=1

# 非逻辑
if [ ! "$flag" -eq 1 ]; then
  echo "trigger not logic"
fi

if [ "$num" == 1 ]; then
  echo "1"
# 或逻辑
elif [ "$num" == 2 ] || [ "$num" == 3 ]; then
  echo "2"
# 与逻辑
elif [ "$num" == 4 ] && [ "$flag" -eq 1 ]; then
  echo "3"
else
  echo "unknow"
fi
