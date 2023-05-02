#!/bin/bash

set -o nounset
set -o errexit

# grep (global regular expression print)

echo "筛选含有 boo 的行数："
grep "boo" foo_file

echo "筛选含有 boo 的行数，显示行数："
grep -n "boo" foo_file

echo "-v 为反向输出其他内容："
grep -vn "boo" foo_file

echo "以 'b' 开头的内容："
grep "^b" foo_file

echo "以 's' 结尾的内容："
grep "s$" foo_file

# awk
# A text pattern scanning and processing language, created by Aho, Weinberger & Kernighan (hence the name).

echo "计算当前目录所有文件的总大小："
ls -l | awk 'BEGIN {sum=0} {sum=sum+$5} END {print sum}'

# sed (stream editor)