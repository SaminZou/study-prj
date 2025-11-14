#!/bin/bash

# 需求：把远程 Ubuntu 服务器所有以 info.2025-11-13* 开头的日志拉到本地

$ scp -P 1234 root@192.168.1.123:"/data/logs/info.2025-11-13*" ~/Desktop/logs