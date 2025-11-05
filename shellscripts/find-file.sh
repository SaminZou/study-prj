#!/bin/bash

set -o nounset
set -o errexit
# 管道命令出错时整体退出
set -o pipefail

# 显示帮助信息
usage() {
    cat << EOF
用法: $(basename "$0") <目录路径> <文件名模式>

在指定目录及子目录中查找匹配文件名模式的文件

参数说明:
  <目录路径>    要搜索的根目录（必须存在且为有效目录）
  <文件名模式>  要匹配的文件名模式（支持通配符，如"*.sh"）

示例:
  $(basename "$0") /study-prj/shelltips "*.sh"
  $(basename "$0") ~/documents "report_*.pdf"
EOF
}

# 检查参数数量
if [ $# -ne 2 ]; then
    echo "错误：参数数量不正确" >&2
    usage >&2
    exit 1
fi

SEARCH_DIR="$1"
PATTERN="$2"

# 检查目录是否存在
if [ ! -d "$SEARCH_DIR" ]; then
    echo "错误：目录 '$SEARCH_DIR' 不存在或不是有效目录" >&2
    exit 1
fi

# 执行查找并显示结果
echo "正在 '$SEARCH_DIR' 中查找匹配 '$PATTERN' 的文件..."
find "$SEARCH_DIR" -type f -name "$PATTERN"

# 检查是否找到结果
if [ $? -eq 0 ] && [ -z "$(find "$SEARCH_DIR" -type f -name "$PATTERN" -print -quit)" ]; then
    echo "未找到匹配 '$PATTERN' 的文件"
fi