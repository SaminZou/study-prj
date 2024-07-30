#!/bin/bash

# 新密码
NEW_PASSWORD="123456"

# 需要修改的用户
echo "root:${NEW_PASSWORD}" | chpasswd
echo "user:${NEW_PASSWORD}" | chpasswd