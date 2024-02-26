#!/bin/bash
docker run --name admin-tomcat -itd --restart=always \
-p 8090:8090 \
-e TOMCAT_SHUTDOWN_PORT_NUMBER=8020 \
-e TOMCAT_HTTP_PORT_NUMBER=8090 \
-e TOMCAT_AJP_PORT_NUMBER=8021 \
-v /mnt/data/tomcat:/bitnami \
bitnami/tomcat:8.5-master