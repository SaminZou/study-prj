#!/bin/bash

# 检测 git 仓库是否有变动
check_git_changes() {
    cd "$1" || exit
    if [ -d ".git" ]; then
        if git diff-index --quiet HEAD --; then
            echo "No changes in $1"
        else
            echo "Changes detected in $1"
        fi
    fi
        cd ..
}

# 遍历当前目录所有文件夹
for dir in */; do
    check_git_changes "$dir"
done
