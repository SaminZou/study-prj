#!/bin/bash

set -o nounset
set -o errexit

# 打印最新的 1000 行数据，根据关键词进行过滤
$ tail -n 1000 server-info.log -f | grep "\[task"