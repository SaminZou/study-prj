#!/bin/bash

# 准备好 frp_0.38.0_linux_amd64.tar.gz，Github 可以下载到

set -o nounset
set -o errexit

# 本安装脚本和压缩包放置到同一目录
mkdir frp
tar -C frp -xzvf frp_0.38.0_linux_amd64.tar.gz

cd frp/frp_0.38.0_linux_amd64
mkdir -p /etc/frp

if [ "$1" = "server" ];then
  # 安装服务端
  cp frps.ini /etc/frp/
  cp frps /usr/bin
  cp systemd/frps.service /etc/systemd/system

  systemctl start frps.service
  systemctl enable frps.service
else
  # 安装客户端端
  cp frpc.ini /etc/frp/
  cp frpc /usr/bin
  cp systemd/frpc.service /etc/systemd/system

  systemctl start frpc.service
  systemctl enable frpc.service
fi