#!/bin/bash

set -o nounset
set -o errexit

# 需要先声明
declare -A testMap

testMap["test1"]="01"
testMap["test2"]="02"
testMap["test3"]="03"
testMap["test4"]="04"
testMap["test5"]="05"

# 输出所有 key
echo '输出所有 key'
echo "${!testMap[@]}"

# 输出所有 value
echo '输出所有 value'
echo "${testMap[@]}"

# 获取长度
echo '获取长度'
echo ${#testMap[@]}

# 遍历操作，根据 key 找到对应的 value
echo '遍历操作，根据 key 找到对应的 value'
for key in ${!testMap[*]};do
  echo $key
  echo ${testMap[$key]}
done

# 遍历所有的 key
echo '遍历所有的 key'
for key in "${!testMap[@]}";do
  echo $key
  echo ${testMap[$key]}
done

# 遍历所有的 value
echo '遍历所有的 value'
for val in "${testMap[@]}";do
  echo $val
done