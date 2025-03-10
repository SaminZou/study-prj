#!/bin/bash

docker run --name=influxdb-dev -itd --restart always \
-p 8083:8083 \
-p 8086:8086 \
-e INFLUXDB_ADMIN_USER=influxdb2024 \
-e INFLUXDB_ADMIN_PASSWORD=influxdb2024 \
-v /mnt/data/influxdb:/var/lib/influxdb \
influxdb:1.8.3