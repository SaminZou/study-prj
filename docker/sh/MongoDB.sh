#!/bin/bash
docker run --name mongodb -itd --restart always \
-p 27017:27017 \
-v /mnt/docker/mongodb:/bitnami \
bitnami/mongodb