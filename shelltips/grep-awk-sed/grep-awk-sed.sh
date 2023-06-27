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

echo "打印第二列：$(echo "1 2 3 4" | awk '{print $2}')"

# sed (stream editor)

# -e 和 -i 的区别，-e 是执行后标准输出，-i 会修改文件内容，即区别为是否会修改文件内容

# sed -i ‘行数i\要插入的内容’
# 注意，行数一定要是存在的，如文件只有两行内容，则 3i\111 不生效
echo "在第二行插入 111："
sed -i '2i\111' sed_test_file
cat sed_test_file
echo ''

# sed -i '/DELETE STRING/d' * 删除所有文件中含有 “DELETE STRING” 行
echo "删除 111："
sed -i '/111/d' sed_test_file
cat sed_test_file
echo ''

# sed 's/pattern/replace_string/g' file 替换文本
echo "修改 input 为 output："
sed -e 's/input/output/g' sed_test_file
echo ''

echo "删除所有的 #备注："
sed -e '/^#/ d' sed_test_file