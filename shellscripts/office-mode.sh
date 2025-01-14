#!/bin/bash

# apt install ddcutil

# debugger：./office.sh close && sleep 10 && ./office.sh open

set -o nounset
set -o errexit

set_office_env() {
    case "$1" in
        open)
            ddcutil setvcp D6 01
            ifconfig enp2s0 up
            echo "Work $(date +"%Y-%m-%d %H:%M:%S")" >> office.log
            ;;
        close)
            ifconfig enp2s0 down
            # lock screen
            # loginctl lock-session
            # close screen
            ddcutil setvcp D6 04
            echo "Leave $(date +"%Y-%m-%d %H:%M:%S")" >> office.log
            ;;
        *)
            echo "未知变量: $1"
            echo "可用环境: open, close"
            return 1
            ;;
    esac
}

# check parameter
if [ $# -ne 1 ]; then
    echo "用法: $0 {open|close}"
    exit 1
fi

# call function
set_office_env "$1"