#!/bin/bash

#!/bin/bash 是一个 shebang（也称为 hashbang），它指定了脚本应该用哪个解释器来执行。
# #! 是 shebang 的起始符号。
# /bin/bash 是 Bash 解释器的路径，表示这个脚本应该用 Bash 来执行。

# 在默认情况下，遇到不存在的变量，会忽略并继续执行，而这往往不符合预期，加入该选项，可以避免恶果扩大，终止脚本的执行
set -o nounset
# 在默认情况下，遇到执行出错，会跳过并继续执行，而这往往不符合预期，加入该选项，可以避免恶果扩大，终止脚本的执行
set -o errexit
# 以上两个选项都符合 fail-fast 设计理念

# 封装常用脚本
log() {
  # local 为函数内变量
  local prefix="[$(date +%Y-%m-%d\ %H:%M:%S)]: "
  echo "${prefix} $@" >&2
}

log "INFO" "a message"

# 使用$()代替`(反单引号)
echo "A-`echo B-\`echo C-\\\`echo D\\\`\``"
echo "A-$(echo B-$(echo C-$(echo D)))"

# echo 不是唯一调试方式
# 对脚本进行语法检查
# bash -n commonshell-best-practices.sh
# 跟踪脚本里每个命令的执行
# bash -v commonshell-best-practices.sh
# 跟踪脚本里每个
# bash -x commonshell-best-practices.sh