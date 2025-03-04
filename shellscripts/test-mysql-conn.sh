#/bin/bash

# 密码不能有空格，如果密码存在特殊字符，使用 \ 转移
$ mysql -h samin.dev -u root -psamin\@123 -e "SELECT 1" study