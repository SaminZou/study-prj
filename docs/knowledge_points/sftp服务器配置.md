# 通过用户组控制
新增用户，禁止ssh登录，hmoe路径不创建目录
``
$ useradd -s /sbin/nologin -M samin -g sftpusers
``

设置用户密码
``
$ passwd samin
``

加入用户组
``
$ usermod -aG sftpusers samin
``

创建用户的根目录，用户只能在此目录下活动
``
$ mkdir /sftp-data
``

设置目录权限实现：能够进入跟目录的用户只能是root；用户只能到达/sftp-data
``
$ chown root:root /sftp-data
``
``
$ chmod 755 /sftp-data
``

配置ssh（用户）
```
vi /etc/ssh/sshd_config

#注释掉这行  
#Subsystem sftp /usr/libexec/openssh/sftp-server
#添加在配置文件末尾
Subsystem sftp internal-sftp  #指定使用sftp服务使用系统自带的internal-sftp
Match User samin #匹配用户，如果要匹配多个组，多个组之间用逗号分割
ChrootDirectory %h/das/sftp-data #用chroot将指定用户的根目录
# ForceCommand internal-sftp #指定sftp命令
# X11Forwarding no #这两行，如果不希望该用户能使用端口转发的话就加上，否则删掉
AllowTcpForwarding no
```
配置ssh（用户组）
```
vi /etc/ssh/sshd_config

#注释掉这行  
#Subsystem sftp /usr/libexec/openssh/sftp-server
#添加在配置文件末尾
Subsystem sftp internal-sftp  #指定使用sftp服务使用系统自带的internal-sftp
Match Group sftpusers #匹配用户，如果要匹配多个组，多个组之间用逗号分割
ChrootDirectory %h/das/sftp-data #用chroot将指定用户的根目录
# ForceCommand internal-sftp #指定sftp命令
# X11Forwarding no #这两行，如果不希望该用户能使用端口转发的话就加上，否则删掉
AllowTcpForwarding no
```

重启ssfp服务
``
$ service sshd restart
``

用root账户给指定用户创建目录，赋予权限
``
$ mkdir /sftp-data/samin
``
``
$ chown samin /sftp-data/samin
``
``
$ chmod 777 /sftp-data/samin  
``

# 其他常用操作
增加用户组
``
$ groupadd sftpusers
``
查看用户隶属的用户组
``
$ groups samin
``
用户组中删除用户
``
$ gpasswd -d samin sftpusers
``
删除用户(-r 作用是删除home中的文件夹)
``
$ userdel -r samin
``
查看所有用户
``
$ cat /etc/passwd
``
查看所有用户组
``
$ cat /etc/group
``
查看sftp服务的日志
```
$ vi /etc/ssh/sshd_config # 在Subsystem下面增加 LogLevel INFO
$vi /etc/rsyslog.conf # 在最后增加
auth,authpriv.*,local5.* /var/log/sftp.log
$ systemctl restart rsyslog.service
$ systemctl restart sshd.service
$ tail -f /var/log/sftp.log
```
关闭SELINUX
```
$ getenforce # 查看SELINUX的开启状态
$ vi /etc/selinux/config # 修改 SELINUX=disabled  重启
```
