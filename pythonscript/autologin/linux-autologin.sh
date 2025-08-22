#!/bin/bash

# linux 环境需要搭配切换 python 环境脚本
# 通过 python3 -m venv runtime 初始化

source /mnt/runtime/bin/activate

python /mnt/autologin.py

deactivate