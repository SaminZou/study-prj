#!/bin/bash

branchname=bibt.$(date +%Y%m.%d)
old_version=bibt.$(date -d "7 days ago" +%Y%m.%d)

# 测试指定时间
#branchname=bibt.$(date -d "2023-07-31" +%Y%m.%d)
#old_version=bibt.$(date -d "2023-07-31 - 7 days" +%Y%m.%d)

if test "$old_version" = "bibt.202307.24"; then
    old_version=bibt.2307.4
fi

echo "本周版本为：$branchname"

echo "上一个版本为：$old_version"