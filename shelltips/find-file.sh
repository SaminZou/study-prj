#!/bin/bash

set -o nounset
set -o errexit

# 在指定目录及子目录中查找匹配文件名的文件
find $1 -type f -name "$2"

# 如果是使用通配符，使用方式为：
# ./find-file /study-prj/shelltips "*sh"