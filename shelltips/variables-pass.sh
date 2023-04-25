#!/bin/bash

# 方法一、二，二选一运行

# 方法一，设置 set -o nounset
#set -o nounset
#set -o errexit

# 方法二，没有设置 set -o nounset 的前提下使用以下语句
#if [ ! -n "$1" ] ; then
#    echo "you must input a variable !!!"
#    exit
#fi

if [ -z $1 ]; then
  echo "you must input a variable"
  exit
fi

echo "variable is: $1"