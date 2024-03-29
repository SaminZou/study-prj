```yaml
title: Sftp 服务器配置
author: samin
date: 2021-01-16
```

# SSH 服务

```shell
# 查看 openssh-server 的运行状态
$ /etc/init.d/ssh status

# 修改 sshd.config，去掉 Port 注释（默认不可访问，需要开放端口），重启服务
$ sudo /etc/init.d/ssh restart
```

# 通过用户组控制

新增用户，禁止 ssh 登录，home 路径不创建目录

```shell
$ useradd -s /sbin/nologin -M samin -g sftpusers
```

设置用户密码

```shell
$ passwd samin
```

加入用户组

```shell
$ usermod -aG sftpusers samin
```

创建用户的根目录，用户只能在此目录下活动

```shell
$ mkdir /sftp-data
```

设置目录权限实现：能够进入跟目录的用户只能是root；用户只能到达/sftp-data

```shell
$ chown root:root /sftp-data
```
```shell
$ chmod 755 /sftp-data
```

配置ssh（用户）

```shell
vi /etc/ssh/sshd_config
```

```shell
# Subsystem sftp /usr/libexec/openssh/sftp-server #注释掉这行
# 添加在配置文件末尾
Subsystem sftp internal-sftp  # 指定使用 sftp 服务使用系统自带的 internal-sftp
Match User samin # 匹配用户，如果要匹配多个组，多个组之间用逗号分割
ChrootDirectory /home/das/sftp-data # 用 chroot 将指定用户的根目录
# ForceCommand internal-sftp # 指定 sftp 命令
# X11Forwarding no # 这两行，如果不希望该用户能使用端口转发的话就加上，否则删掉
AllowTcpForwarding no
# 保持 TCP 连接
TCPKeepAlive yes
```

配置ssh（用户组）

```shell
# Subsystem sftp /usr/libexec/openssh/sftp-server # 注释掉这行  
# 添加在配置文件末尾
Subsystem sftp internal-sftp  # 指定使用 sftp 服务使用系统自带的 internal-sftp
Match Group sftpusers # 匹配用户，如果要匹配多个组，多个组之间用逗号分割
ChrootDirectory %h/das/sftp-data #用 chroot 将指定用户的根目录
# ForceCommand internal-sftp # 指定 sftp 命令
# X11Forwarding no # 这两行，如果不希望该用户能使用端口转发的话就加上，否则删掉
AllowTcpForwarding no
```

重启ssfp服务

```shell
$ service sshd restart
```

用root账户给指定用户创建目录，赋予权限

```shell
$ mkdir /sftp-data/samin

$ chown samin /sftp-data/samin

$ chmod 777 /sftp-data/samin  
```

# 其他常用操作

增加用户组

```shell
$ groupadd sftpusers
```

查看用户隶属的用户组

```shell
$ groups samin
```

用户组中删除用户

```shell
$ gpasswd -d samin sftpusers
```

删除用户(-r 作用是删除home中的文件夹)

```shell
$ userdel -r samin
```

查看所有用户

```shell
$ cat /etc/passwd
```

查看所有用户组

```shell
$ cat /etc/group
```

查看sftp服务的日志

```shell
# 在Subsystem下面增加 LogLevel INFO
$ vi /etc/ssh/sshd_config

# 在最后增加
$vi /etc/rsyslog.conf
auth,authpriv.*,local5.* /var/log/sftp.log

$ systemctl restart rsyslog.service

$ systemctl restart sshd.service

$ tail -f /var/log/sftp.log
```

关闭 SELINUX

```shell
# 查看SELINUX的开启状态
$ getenforce

# 修改 SELINUX=disabled  重启
$ vi /etc/selinux/config 
```
