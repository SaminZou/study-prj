```yaml
title: Linux 指令入门 
author: samin
date: 2021-09-20
```

# Linux 的 swap 空间

相当于windows系统的虚拟内存，只有在物理内存不够的情况下才会使用，虚拟内存的速度要远远慢于物理内存

# Linux 系统

- 软件仓库（git）、程序包管理（yum）、文件权限、用户管理
- 类unix系统，模块化，可拓展，灵活
- 一切皆文件，文件是一切的最终归宿
  - / 根目录
  - /bin 包含二进制文件，系统启动需要的可执行程序
  - /boot 包含机器启动时的内核和文件
  - /dev 包含设备节点，与连接到计算机的物理设备一起使用的指令/接口
  - /etc 包含系统范围的配置文件
  - /home 包含系统用户的目录
  - /lib 包含启动计算机的核心程序所必需的共享库
  - /lost+found 包含由于系统崩溃或驱动器错误而放错位置的数据
  - /media 包含用于USB，CD和DVD等媒体设备的安装点
  - /mnt 是临时文件系统的挂载点
  - /opt 包含程序的其他程序包
  - /proc 包含与内核和正在运行的程序有关的文件
  - /tmp 包含在执行程序时可能创建的临时文件
  - /usr 包含一个次要的只读系统树，用于与某些程序共享或用于安装在其他Linux系统上。上面存在许多目录也存在这里，并包含一些其他文件
  - /var 包含随时间变化其大小发生变化的文件，例如日志和备份
- 最常见的shell程序是Bash（Apple最近将macOS的Shell程序切换为zsh）

# 操作系统的功能

- 进程管理
- 内存管理
- 设备管理
- 文件管理

进程和内存管理是每个系统都需要的，设备和文件管理在嵌入式系统中可不必备。

# 操作系统是机器和应用程序的中间层

# 指令游标移动快捷键

|快捷键|功能|
|---|---|
|ctrl+a|游标移动到最前面|
|ctrl+e|游标移动到最后面|
|ctrl+u|往前删除全部|
|ctrl+k|往后删除全部|

# 获取指令帮助

一般有 `--help` 入参，还有 `man` 指令可以提供一个正式的分页文档（ 一般为 `less` 指令 ）

语法：$  man program

针对 `man` 指令开发帮助手册，没有帮助手册的会提示 `No manual entry for program`

\# 获取cp指令的帮助

$ man cp

|章节|内容|
|---|---|
|1|用户命令|
|2|程序接口内核系统调用|
|3|C库函数程序接口|
|4|特殊文件，比如说设备节点和驱动程序|
|5|文件格式|
|6|游戏娱乐，如屏幕保护|
|7|其他方面|
|8|系统管理员命令|

\# 显示 /etc/passwd 的文件格式说明手册

$ man 5 passwd

# 打印字符相关

## 重定向

\# 输出结果到test.txt

$ ls -l > test.txt

\# 清空test.txt

$ > test.txt

\# 追加内容到test.txt

$ ls -l >> test.txt

## 管道

\# 管道 ‘|’ 上一个指令的输出做为下一个指令的输入

$ ls -l /usr/bin | less

## 管道和重定向的区别

1. 左边的命令应该有标准输出 | 右边的命令应该接受标准输入

- 左边的命令应该有标准输出 > 右边只能是文件
- 左边的命令应该需要标准输入 < 右边只能是文件

1. 管道触发两个子进程执行"|"两边的程序；而重定向是在一个进程内执行

> 一般命令间传递参数，使用管道 处理输出结果需要重定向到文件，用重定向

## cat 连接文件

\# 可以显示隐藏字符数

$ cat -a file

\# 显示file1和file2的内容

$ cat file1 file2

\# 合并file1和file2的内容到file3

$ cat file1 file2 > file3

## wc 统计一个文件的行数、字符数、字节数

- c 统计字节数。
- l 统计行数。
- m 统计字符数。这个标志不能与 -c 标志一起使用。
- w 统计字数。一个字被定义为由空白、跳格或换行字符分隔的字符串。
- L 打印最长行的长度。

## grep 打印匹配航

\# 在file中寻找匹配condition的行数

$ grep condition file

## head/tail 打印文件开头/结尾部分

\# 显示文件前5行

$ head -n 5 file

\# 显示文件后5行

$ tail -n 5 file

\# 实时显示文件最新行数，按ctrl+c退出

$ tail -f <file>

## nl 显示行号

\# 前面带行号显示文件内容

$ nl <filename>

## cut 按列截取片段

\# cut tmp.txt文件的前10列

$cut -c-10 tmp.txt

\#cut tmp.txt文件的第3到5列

$cut -c3-5 tmp.txt

\#cut tmp.txt文件的第3到结尾列

$cut -c3- tmp.txt

## diff

\# 比较两个文件的不同

$ diff <file1> <file2>

# history 历史操作

\# 显示最近使用的10条指令

$ history | tail -n 10

\# 删除历史记录

$ history -c

# alias

用法：

$ alias name='string'

说明： 'name':新指令名称 'string':指令集

举例：

$ alias foo='ls;cd ~;ls'

\# 查看所有别名

$ alias

\# 取消指令别名

$ unalias foo

# 显示指令信息相关指令

\# 查看指令是否系统自带路径

$ type foo

\# 查看指令存在路径

$ which java

\# 查看指令信息

$ whereis java

\# 搭配updatedb使用，通过linux内置数据库查找信息

$ locate

# 远程相关

## SCP

\# 远程复制文件

$ sudo scp hip20190224 [hipappb@177.16.11.49](mailto:hipappb@177.16.11.49):/home/hipappb

\# 复制文件夹

$ sudo scp -r /home/hipapp/mq_prj/ui/ [root@177.16.11.49](mailto:root@177.16.11.49):/home/hipappb/mq_prj/ui

\# 不检查公钥

$  sudo scp -o StrictHostKeyChecking=no [mr-data-backup.sh](http://mr-data-backup.sh/) [root@172.168.110.250](mailto:root@172.168.110.250):/home/daship

## SSH

\# 生成公钥

$ ssh-keygen -t rsa -C "[email@example.com](mailto:email@example.com)"

\# 不检查公钥

$ sudo ssh -o StrictHostKeyChecking=no [root@172.168.110.250](mailto:root@172.168.110.250)

\# 默认是22端口，也可以通过 `-p` 指定

$ ssh root@ip$ ssh -p <port> root@ip

\# 连接出错的时候，可以输出客户端报错

$ ssh -v root@ip

\# 调试模式启动sshd服务

$ /usr/sbin/sshd -d

\# 查看sshd的日志

$ journalctl -u ssh.service -n 10

\# 通过公钥方式直接访问服务器，复制公钥到目的服务器，之后可以直接通过ssh指令访问服务器

$ ssh-copy-id -i ~/.ssh/id_rsa.pub root@<ip>

## SSH和SEHLL的区别

- SSH：Secure Shell，SSH 是较可靠，专为远程登录会话和其他网络服务提供安全性的协议。
- Shell：命令提示符

## ssh和sshd的区别

- ssh：指开启远程shell的客户端
- sshd：指服务端开启ssh服务

# 文件信息相关指令

- 对象：所有者、组、其他
- 权限：写、读、执行
- 相关命令: chmod（改变权限）、chown（改变归属）

\# 查看文件大小

$ stat filepath

\# 查看文件大小

$ du -h filepath

\# 查看文件大小

$ ls -h filepath

\# 查看服务器硬盘使用情况

$ df -h

\# 展示当前目录下每个目录大小

$ du -sh

\# 显示所有文件的大小，以可读方式展示

$ du -ah /root

\# 显示当前目录中每个文件/文件夹大小

$ du -h -d 1

\# 展示当前系统磁盘使用情况，以可读的方式展示

$ df -h

\# 用 m 做单位，显示当前所有目录大小，排序输出

$ sudo du -md 1 | sort

# 网络相关

\# 查看是否开启bbr

\# 输出有bbr字样即可

$ sysctl net.ipv4.tcp_available_congestion_control

\# 输出有bbr字样即可

$ lsmod | grep bbr

\# 查看端口是否占用

$ netstat -ap | grep 8080

\# 查看端口的进程id

$ lsof -i:8080

\# 列出所有端口使用情况

$ netstat -a

\# 显示当前UDP连接状况

$ netstat -nu

\# 显示UDP端口号的使用情况

$ netstat -apu

\# 显示网卡列表

$ netstat -i

\# 显示网络统计信息

$ netstat -s

\# 显示监听的套接口

$ netstat -l

\# 显示所有已建立的有效连接

$ netstat -n

\# 显示关于路由表的信息

$ netstat -r

\# 列出所有 tcp 端口

$ netstat -at

\# 找出程序运行的端口

$ netstat -ap | grep ssh

\# 在 netstat 输出中显示 PID 和进程名称

$ netstat -pt

\# 查询当前对外开放的接口

$ netstat -lntp

\# 查询当前端口的信息

$ netstat -nap | grep <port>

\# 查看所有建立的TCP连接

$ netstat -antp

\# 查看所有建立的IPV4协议的TCP连接

$ netstat -antp4

\# 查看服务监听的端口（没有netstat指令的替代指令）

$ ss -tanl

\# 查看DNS服务器

$ cat /etc/resolv.conf

## TCP连接情况

$ netstat -an | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'

- LISTEN：侦听来自远方的TCP端口的连接请求
- SYN-SENT：再发送连接请求后等待匹配的连接请求
- SYN-RECEIVED：再收到和发送一个连接请求后等待对方对连接请求的确认
- ESTABLISHED：代表一个打开的连接
- FIN-WAIT-1：等待远程TCP连接中断请求，或先前的连接中断请求的确认
- FIN-WAIT-2：从远程TCP等待连接中断请求
- CLOSE-WAIT：等待从本地用户发来的连接中断请求
- CLOSING：等待远程TCP对连接中断的确认
- LAST-ACK：等待原来的发向远程TCP的连接中断请求的确认
- TIME-WAIT：等待足够的时间以确保远程TCP接收到连接中断请求的确认
- CLOSED：没有任何连接状态

## ping指令

\# 检测网络情况

$ ping <host>

\# ping网关

$ ping -b <host>

\# ping指定次数

$ ping -c 10 <host>

\# ping指定时间间隔和次数限制

$ ping -c 10 -i 0.5 <host>

\# traceroute 网络链路追踪，看下数据包的路由途径

$  traceroute [www.baidu.com](http://www.baidu.com/)

# 时间、时区相关

\# 查看时区

$ timedatectl | grep "Time zone"

$ cat /etc/timezone

\# 修改时区

$ sudo tzselect

\# 替换时间配置

$ sudo cp /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime

\# 显示地区

$ locale

\# 显示时间

$ date +%Y-%m-%d_%H:%M:%S

$ echo $(date +%F)

\# 输出年月日

$ echo $(date +%T)

\# 输出时分秒

$ echo $(date +%Y/%m/%d-%H:%M:%S)

# 查看系统信息相关

\# 显示所有信息

$ uname -a

\# 显示内核名称 Linux

$ uname -s

\# 显示网络节点上的主机名 BigManing

$ uname -n

\# 显示内核发行号5.4.27-1-MANJARO

$ uname -r

\# 显示内核版本号 #106-Ubuntu SMP Mon Jun 26 17:54:43 UTC 2017

$ uname -v

\# 显示机器硬件名称 x86_64

$ uname -m

\# 显示处理器类型 x86_64

$ uname -p

\# 显示硬件平台 x86_64

$ uname -i

\# 操作系统 GNU/Linux

$ uname -o

\# 显示操作系统信息

$ cat /proc/version

\# 查看发行版本信息

$ cat /etc/issue

$ cat /etc/os-release

# 全局变量的设定

## shell的配置文件

1. etc目录下存放系统管理和配置文件

- /etc/profile 为所有的用户设置系统范围的环境变量和启动顺序。当用户登录时读取该文件。这个文件对每个shell都有效。
- /etc/.bashrc 为每一个运行bash shell的用户执行此文件，当bash shell被打开时,该文件被读取。也就是说，当用户shell执行了bash时，运行这个文件。

1. ~/.profile和~/.bashrc 类似上述文件，作用范围仅仅是当前用户。 Tips:像安装JDK这样的，最好把其配置成系统范围的。下载之后，把文件移到/etc等这种全局目录下。

> 加载顺序：/etc/profile -> /etc/paths （前面两者是系统级别）-> ~/.bash_profile -> ~/.bash_login -> ~/.profile -> ~/.bashrc bash 开头的配置文件，仅当 shell 使用 bash 的时候有效，zsh 的配置文件为 ~/.zshrc

## 设置方法

1. 设置、取消环境变量

$ export test=123

$ echo $test

$ unset test

1. 查看环境变量

\# 查看环境变量（$PATH，$HOME）

$ env

\# 查看所有环境变量，包括环境变量和自定义变量

$ set

# 系统登录显示的标语

\# 可以修改登录加载语句，编辑用于区分不同服务器

$ vim /etc/motd

# 查看所有用户和用户组

$ cat /etc/passwd

$ cat /etc/group

\# 新装系统，设置root密码

$ sudo passwd root

# whereis 和 which

\# 查询范围比which广

$ whereis ls

\# 只能查找$PATH里面的

$ which ls

# 查看防火墙规则

$ iptables -nL

# 设置路由

\# 显示当前路由

$ route

\# 屏蔽一条路由

$ route add -net 224.0.0.0 netmask 240.0.0.0 reject

\# 删除路由记录

$ route del -net 224.0.0.0 netmask 240.0.0.0

\# 删除和添加设置默认网关

$ route del default gw 192.168.0.100

$ route add default gw 192.168.0.100

# cd

\# 顶级根目录

$ cd /

\# root目录

$ cd ~

\# 返回上一层目录

$ cd ..

\# 返回之前操作目录

$ cd -

# hostname

\# 修改主机名

$ sudo hostname <hostname>

\# 查看主机ip，不需要用到ifconfig

$ hostname -i

\# 查看当前服务器名

$ hostname

\# 修改服务器名，重启后生效

$ sudo vim /etc/hostname

## 修改 hostname

\# 修改 hostname

$ hostnamectl set-hostname your-new-host-name

\# 查看修改结果

$ hostnamectl status

\# 设置 hostname 解析

$ echo "127.0.0.1   $(hostname)" >> /etc/hosts

# echo

- 字体颜色：30m-37m 黑、红、绿、黄、蓝、紫、青、白
- 背景颜色：40-47 黑、红、绿、黄、蓝、紫、青、白

\# 黄色背景红色字

$ echo -e "\033[43;31m samin test \033[0m"

# & && | || ; 的区别

- & 表示任务在后台执行
- && 表示前一条命令执行成功时，才执行后一条命令 $ echo ‘1‘ && echo ‘2’
- | 表示管道，上一条命令的输出，作为下一条命令参数 $ echo ‘yes’ | wc -l
- || 表示上一条命令执行失败后，才执行下一条命令 $ cat nofile || echo “fail”
- ; 表示按顺序执行执行 $ ls;echo 'hello'

# nohup和&的区别

- 使用&后台运行程序
- 结果会输出到终端
- 使用Ctrl + C发送SIGINT信号，程序免疫
- 关闭session发送SIGHUP信号，程序关闭
- 使用nohup运行程序
- 结果默认会输出到nohup.out
- 使用Ctrl + C发送SIGINT信号，程序关闭
- 关闭session发送SIGHUP信号，程序免疫
- 一般组合运用，做到Ctrl+c和关闭session都无法导致程序关闭

\# 后台运行springboot项目

$ nohup java -jar spring-boot.jar >tomcat.log &

# 修改默认编辑器

\# 查看默认编辑器

$ echo $EDITOR

\# 载入到当前用户配置

$ export EDITOR=/usr/bin/vim >> ~ ./bashrc

# 系统性能相关

\# 实时监控

$ top

\# 实时监控指定 pid 的运行情况

$ top -Hp pid

\# 安装htop，一般程序不自带需要安装

$ htop

\# 查看CPU信息

$ lscpu

> Core(s) per socket 每个插槽的核心数

\# 内存使用情况

$ free -mh

\# 分别可以查看1分钟、5分钟、15分钟的系统负荷，CPU完全空闲为0

$ uptime

\# sync 命令将所有未写的系统缓冲区写到磁盘中，包含已修改的 i-node、已延迟的块 I/O 和读写映射文件，改写drop_caches值是为了强制清缓存。drop_caches(0:不释放（系统默认）；1:释放页缓存；2:释放dentries和inodes)

$ sync;echo 3 > /proc/sys/vm/drop_caches

## 方法

USE（Utilization，Saturation，Errors） 定位性能瓶颈的方法论，包括了检查使用率（Utilization），饱和度（Saturation），所有资源（比如 CPU，内存，磁盘等）的错误指标（Errors）。

## 常用指令：有些命令需要安装 sysstat 工具包

- uptime 统计过去1分钟、5分钟、15分钟的I/O情况
- dmesg | tail 展示的是最近 10 条系统消息日志，如果系统消息没有就不会展示。主要是看由于性能问题导致的错误
- vmstat 1 vmstat是一个指定周期和采集次数的虚拟内存检测工具，可以统计内存，CPU，swap的使用情况，它还有一个重要的常用功能，用来观察进程的上下文切换s

1. r: CPU 上的等待运行的可运行进程数。这个指标提供了判断 CPU 饱和度的数据，因为它不包含 I/O 等待的进程。可解释为：“r” 的值比 CPU 数大的时候就是饱和的。
2. free：空闲内存，单位是 k。如果这个数比较大，就说明你还有充足的空闲内存。“free -m” 和下面第 7 个命令，可以更详细的分析空闲内存的状态。
3. si，so：交换进来和交换出去的数据量，如果这两个值为非 0 值，那么就说明没有内存了。
4. us，sy，id，wa，st：这些是 CPU 时间的分解，是所有 CPU 的平均值。它们是用户时间，系统时间（内核），空闲，等待 I/O 时间，和被偷的时间（这里主要指其它的客户，或者使用 Xen，这些客户有自己独立的操作域）。

- mpstat -P ALL 1 这个命令打印各个 CPU 的时间统计，可以看出整体 CPU 的使用是不是均衡的。有一个使用率明显较高的 CPU 就可以明显看出来这是一个单线程应用。
- pidstat 1 pidstat 命令有点像 top 命令中的为每个 CPU 统计信息功能，但是它是以不断滚动更新的方式打印信息，而不是每次清屏打印。 pidstat 是 Sysstat 中的一个组件，也是一款功能强大的性能监测工具，top 和 vmstat 两个命令都是监测进程的内存、CPU 以及 I/O 使用情况，而 pidstat 命令可以检测到线程级别的
- iostat -xz 1 这个工具对于理解块设备（比如磁盘）很有用，展示了请求负载和性能数据。

1. r/s, w/s, rkB/s, wkB/s：这些表示设备上每秒钟的读写次数和读写的字节数（单位是k字节）。这些可以看出设备的负载情况。性能问题可能就是简单的因为大量的文件加载请求。
2. await：I/O 等待的平均时间（单位是毫秒）。这是应用程序所等待的时间，包含了等待队列中的时间和被调度服务的时间。过大的平均等待时间就预示着设备超负荷了或者说设备有问题了。
3. avgqu-sz：设备上请求的平均数。数值大于 1 可能表示设备饱和了（虽然设备通常都是可以支持并行请求的，特别是在背后挂了多个磁盘的虚拟设备）。
4. %util：设备利用率。是使用率的百分数，展示每秒钟设备工作的时间。这个数值大于 60% 则会导致性能很低（可以在 await 中看），当然这也取决于设备特点。这个数值接近 100% 则表示设备饱和了。

- free -m
- sar -n DEV 1 使用这个工具是可以检测网络接口的吞吐
- sar -n TCP,ETCP 1 这是对 TCP 关键指标的统计，它包含了以下内容：

1. active/s：每秒本地发起的 TCP 连接数（例如通过 connect() 发起的连接）。
2. passive/s：每秒远程发起的连接数（例如通过 accept() 接受的连接）。
3. retrans/s：每秒TCP重传数。这种主动和被动统计数通常用作对系统负载的粗略估计：新接受连接数（被动），下游连接数（主动）。可以把主动看作是外部的，被动的是内部，但是这个通常也不是非常准确（例如：当有本地到本地的连接时）。重传是网络或者服务器有问题的一个信号；可能是一个不可靠的网络（例如：公网），或者可能是因为服务器过载了开始丢包。上面这个例子可以看出是每秒新建一个 TCP 连接。

- top top 的一个缺陷也比较明显，很难看出变化趋势，其它像 vmstat 和 pidstat 这样的工具就会很清晰，它们是以滚动的方式输出统计信息。所以如果你在看到有问题的信息时没有及时的暂停下来（Ctrl-S 是暂停, Ctrl-Q 是继续），那么这些有用的信息就会被清屏。

# Java相关指令

- jstack jstack是JDK工具命令，它是一种线程堆栈分析工具，最常用的功能就是使用 jstack pid 命令查看线程的堆栈信息，也经常用来排除死锁情况

- jstat 它可以检测Java程序运行的实时情况，包括堆内存信息和垃圾回收信息，我们常常用来查看程序垃圾回收情况。常用的命令是jstat -gc pid。信息字段说明如下：

  - S0C：年轻代中 To Survivor 的容量（单位 KB）
  - S1C：年轻代中 From Survivor 的容量（单位 KB）
  - S0U：年轻代中 To Survivor 目前已使用空间（单位 KB）
  - S1U：年轻代中 From Survivor 目前已使用空间（单位 KB）
  - EC：年轻代中 Eden 的容量（单位 KB）
  - EU：年轻代中 Eden 目前已使用空间（单位 KB）
  - OC：老年代的容量（单位 KB）
  - OU：老年代目前已使用空间（单位 KB）
  - MC：元空间的容量（单位 KB）
  - MU：元空间目前已使用空间（单位 KB）
  - YGC：从应用程序启动到采样时年轻代中 gc 次数
  - YGCT：从应用程序启动到采样时年轻代中 gc 所用时间 (s)
  - FGC：从应用程序启动到采样时 老年代（Full Gc）gc 次数
  - FGCT：从应用程序启动到采样时 老年代代（Full Gc）gc 所用时间 (s)
  - GCT：从应用程序启动到采样时 gc 用的总时间 (s)

- jmap命令 可以查看堆内存的初始化信息以及堆内存的使用情况，还可以生成dump文件来进行详细分析。查看堆内存情况命令

  $ jmap -heap pid

## 进程相关

$ ps -A | grep <proname> | grep -v grep | awk '{print $1}'

$ pidof <proname>

$ pgrep -f <proname>

\# 查看执行的java程序

$ ps -ef | grep -i java

\# 根据pid强制关闭程序

$ kill -9 [pid]

\# 根据pid关闭程序，一般程序会接收信号后自动释放内存

$ kill -15 [pid]

\# 查看进程的线程号

$ ps -T -p <pid>

# 开关机

\# 计算机将于10分钟后关闭，且会显示在登录用户的当前屏幕中

$ shutdown -h 10

\# 计算机会立刻关机

$ shutdown -h now

\# 计算机会在这个时刻关机

$ shutdown -h 22:22

\# 计算机会立刻重启

$ shutdown -r now

\# 计算机会将于10分钟后重启

$ shutdown -r +10

\# 取消关机

$ pkill shutdown

\# 重启

$ reboot

\# 关机

$ halt

# systemctl 服务管理指令，兼容service

\# 重启ssh

$ systemctl restart sshd.service

\# 开机启动ssh

$ systemctl enable sshd.service

\# 查看ssh运行状态

$ systemctl status sshd.service

\# 查看ssh在自动开机项目中的状态

$ systemctl list-unit-files | grep ssh

# 查看文件夹内文件数量

\# 查看文件数量

$ ls -l | grep ^- | wc -l

\# 查看目录数量

$ ls -l | grep ^d | wc -l

\# 查看以某字符串开头的目录数量

$ ls -l | grep "MRO*" | wc -l

# 设置网络超时时间

\# 查看目前的超时设定

$ cat /proc/sys/net/ipv4/tcp_fin_timeout

## 修改配置

\# 使用vim打开配置文件

$ vim /etc/sysctl.conf

\# 在这个文件中，加入下面的几行内容

```
net.ipv4.tcp_syncookies = 1 net.ipv4.tcp_tw_reuse = 1 net.ipv4.tcp_tw_recycle = 1 net.ipv4.tcp_fin_timeout = 5
```

\#  最后输入下面的命令，让内核参数生效

$ /sbin/sysctl -p

# 硬盘相关

\# 查看所有硬盘信息

$ fdisk -l

\# 挂载硬盘到/home目录

$ mount /dev/sdb /home

\# 查看挂载配置文件

$ vi /etc/fstab

\# 自动修复设备坏道

$ fsck -fy /dev/sdb1

\# 查看设备查用文件类型

$ blkid

# shell

\# 查看当前使用点shell

$ echo $SHELL

\# 列举系统可用指令集

$ cat /etc/shells

\# 选择切换shell指令集

$ chsh -s /bin/zsh

> 有时候关闭 一个 shell 重新打开 zsh失效 可以修改 ～/.bashrc 文件，末尾加上 exec zsh

# 远程桌面

- VNC协议，linux一般使用协议
- RDP协议，windows一般使用协议
- 使用第三方工具，类似teamviewer、向日葵、anydesk等软件

# 文件打包压缩

> 打包和压缩是有区别的，打包指的是把多个文件变成一个文件，压缩指的是通过算法把一个大文件变成一个体积比较小的文件

\# 打包

$ tar -cvf FileName.tar DirName

\# 追加文件到tar包中

$ tar -rf FileName.tar DirName

\# 列出包中的所有文件

$ tar -tf FileName.tar

\# 解包

$ tar -xvf FileName.tar DirName

\# 打包并调用压缩功能

$ tar -czvf FileName.tar DirName

\# 打包并调用压缩功能

$ tar -czvf FileName.tar.gz DirName

\# 解包并调用解缩功能

$ tar -xzvf FileName.tar.gz DirName

\#打包  tar -cvf 包名  文件名

$ tar -cvf test.tar test.txt

\#解包  tar -xvf 包名

$ tar -xvf test.tar

\#压缩  tar -czvf 包名 文件名

$ tar -czvf test.tgz test.txt

\#解压  tar -xzvf 包名

$ tar -xzvf test.tgz

# 软连接和硬链接

\# 连接文件夹的话，需要用绝对路径

$ ln -s [源文件] [软连接文件]

\# 硬链接只能是文件，和软连接的区别是软连接的源文件删除了，软连接失效，硬链接的源文件删除了，一样可以打开文件

$ ln [源文件] [软连接文件]

# 使用U盘

1. 使用root权限 $ su -
2. 创建供U盘挂载使用的文件夹 $ mkdir /mnt/usb-disk
3. 查看机器中是否有 "/dev/sdb1"，能查到说明机器已经识别到U盘 $ fdisk -l
4. 挂载U盘 $ mount /dev/sdb1 /mnt/usb-disk
5. 卸载U盘，以下两条指令均可 $ umount /dev/sdb1 $ umonut /mnt/usd-disk

# 执行shell脚本“bad interpreter”分隔符错误

一般是因为文件是windows系统编写，分隔符无法识别导致。

\# vi打开文件

$ vi fliename

\# 查看文件格式，如果看到"fileformat=dos" :set ff

\# 修改文件格式为 unix :set ff=unix

\# 保存退出 :wq

# 统计指令执行时间

$ time [command-line]

# 用户相关

\# -d 指定home路径创建用户
\# -m 自动创建用户主目录
\# -M 不创建用户主目录
\# -s 指定用户登录使用的 sh

$ useradd -s / -d /home/ops -M newuser

# 查看远程服务器端口是否开放

$ nc -zv <ip> <port>

# curl

$ curl -X POST 'http://172.168.110.249:8080/oauth/token' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Authorization: Basic ZGFzOnNlY3JldA==' -d 'grant_type=password' -d 'username=das-sso' -d 'password=sso'

$ curl -X POST 'http://172.168.110.249:8080/messages/topic' -H 'Content-Type: application/json' -H 'Authorization: Bearer f4f5f21c-bd88-4a7b-877a-7901416cfa86' -d '{"messageName":"MsgTest","type":"restful","content":"test"}'

$ curl -X POST 'http://172.20.0.56:8084/getMsg2' -H 'Content-Type: application/json' -d '{ "msgName": "test", "senderId": "sso", "content": "test msg", "deliveryId": "123", "sendTime": "2020-06-20 12:00:00.000" }'

json_pp 可以格式化json数据，一般使用为

$ echo "{\"test\":"123"}" | json_pp { "test":"123" }

搭配curl使用为

$ curl ... | json_pp

验证代理是否配置成功

$ curl -v --socks5 127.0.0.1:1080 https://www.google.com

# 工作常用

$ export

$ xargs

$ date

$ whereis

$ crontab

## 网络

$ scp

$ ssh

$ wget

$ mysql

# 日常运维

$ shutdown

$ mount

$ chmod

$ su

$ yum

$ password

$ service / systemctl

# 漫游

$ find

$ ls

$ cd

$ pwd

# 压缩

$ unzip

$ tar

$ unrar

$ bzip2

# 系统状态概览

$ ps

$ top

$ free

$ ifconfig

$ uname

$ ping

$ netstat

$ nslookup

# 文本处理

## 查看

$ less

$ tail

$ cat

## 统计

$ sort

$ uniq

## 编辑

$ vi

$ vim

## 过滤

$ grep

$ awk

$ sed

$ diff