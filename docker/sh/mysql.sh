#!/bin/bash
docker run --name=mysql -itd --restart always \
-p 3306:3306 \
-v /mnt/docker/mysql:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=samin \
-e MYSQL_DATABASE=samin \
-e MYSQL_USER=samin \
-e MYSQL_PASSWORD=samin \
mysql:5.7