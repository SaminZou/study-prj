#!/bin/bash

# 定义多个规则条件
SEARCH_SUFFIXES=("api" "client")

# 遍历当前目录下的所有文件夹，排除指定文件夹的内容
find . -type d -not -path "./.repository/*" -not -path "*/target/*" -not -path "./.repository" -not -path "*/src/*" | while read -r dir
do
  # 判断文件夹名称是否符合任何一个规则条件
  folder=$(basename "$dir")
  for suffix in "${SEARCH_SUFFIXES[@]}"
  do
    if [[ "$folder" == *"$suffix" ]]; then
      # 打印文件名
      echo "$folder 符合条件 ($suffix)"
      # 打印完整路径
      echo "$dir 符合条件 ($suffix)"

      # 在这里可以执行特定操作，如进入文件夹、处理文件夹内的文件等

      break  # 退出内层循环，继续处理下一个文件夹
    fi
  done
done