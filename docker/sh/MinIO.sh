#!/bin/bash
docker run --name=minio -itd --restart always \
-p 9000:9000 \
-v /mnt/data/minio:/data \
-e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" \
-e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
minio/minio server /data