#!/bin/bash

nexusDataDir=/mnt/data/nexus

mkdir -p $nexusDataDir

chown -R 200 $nexusDataDir

docker run --name nexus3 -itd --restart=always \
-p 8081:8081 \
-v $nexusDataDir:/nexus-data \
sonatype/nexus3