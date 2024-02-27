#!/bin/bash
docker run --name portainer -itd --restart always \
--publish 9000:9000 \
--volume /var/run/docker.sock:/var/run/docker.sock \
--volume /mnt/data/portainer:/data \
portainer/portainer