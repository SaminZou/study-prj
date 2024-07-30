# Centos 添加防火墙策略
firewall-cmd --zone=public --add-port=8080/tcp --permanent
firewall-cmd --reload

# Ubuntu 添加防火墙策略
sudo ufw allow 8080/tcp
sudo ufw enable