```yaml
title: MySQL 常用指令
author: samin
date: 2022-02-06
```

# 创建新数据库和用户

mysql> CREATE USER 'keycloak'@'%' IDENTIFIED BY 'keycloak';

mysql> CREATE DATEBASE keycloak CHARACTER SET utf8 COLLATE utf8_unicode_ci;

mysql> GRANT ALL PRIVILEGES ON keycloak.* TO 'keycloak'@'%';

mysql> FLUSH PRIVILEGES;

# 查看存储地址

show variables like 'datadir';

每个表，如果以 InnoDB 引擎存储，都有一个对应的 *.ibd 文件