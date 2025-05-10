#!/bin/bash

set -o nounset
set -o errexit
set -o pipefail

# 检测 git 仓库是否有变动
check_git_changes() {
    cd "$1" || exit
    if [ -d ".git" ]; then
        status=$(git status --porcelain)
        if [ -n "$status" ]; then
            echo "Git status has output in $1"
        # else
        #     echo "Git status is clean."
        fi
    fi
    cd ..
}

# 遍历当前目录所有文件夹
for dir in */; do
    check_git_changes "$dir"
done