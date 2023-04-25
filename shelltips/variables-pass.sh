#!/bin/bash

# 方法一、二，二选一运行

# 方法一，设置 set -o nounset
#set -o nounset
#set -o errexit

# 方法二，没有设置 set -o nounset 的前提下使用以下语句
#if [ ! -n "$1" ] ; then
#    echo "you must input a variable !!!"
#    exit 0
#fi

# 定义为 0 的时候，程序是正确退出，exit 1 代表有错误，也可以通过这个来判断程序是否正常退出
if [ -z $1 ]; then
  echo "you must input a variable"
  exit 0
fi

echo "variable is: $1"