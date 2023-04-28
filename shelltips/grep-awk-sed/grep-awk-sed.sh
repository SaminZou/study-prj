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

echo "符合筛选条件的数量："
grep -c "boo" foo_file

# sed (stream editor)