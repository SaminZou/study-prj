#!/bin/bash

### 参数
# 备份时间跨度
BACKUP_DAYS=7
# 备份文件名
BACKUP_FILE_NAME="BACKUP_"$(date +%Y%m%d)
# 删除时间
DELETE_TIME=$(date -d "${BACKUP_DAYS} days ago" +%Y-%m-%d)

### 自动备份脚本
docker exec -d samin-mongodb bash -c "mongodump --db biz --out /bitnami/${BACKUP_FILE_NAME}"
docker exec -d samin-postgresql bash -c "pg_dump -h 127.0.0.1 -U samin -d biz >
/var/lib/postgresql/${BACKUP_FILE_NAME}"

### 自动清理脚本
# 清理过期mongo数据
docker exec -d samin-mongodb bash -c \
"mongo biz --eval \"printjson(db.getCollection('delivery').remove({'sendTime':{'\\\$lt':new Date('${DELETE_TIME}')}})
)\""
docker exec -d samin-mongodb bash -c \
"mongo biz --eval \"printjson(db.getCollection('push').remove({'responseTime':{'\\\$lt':new Date('${DELETE_TIME}')}})
)\""
# 清理过期postgresql数据
docker exec -d samin-postgresql bash -c \
"psql -h 127.0.01 -d biz -U samin --command \
\"delete from biz_delivery where delivery_time < to_date('${DELETE_TIME}', 'yyyy-MM-dd')\""
docker exec -d samin-postgresql bash -c \
"psql -h 127.0.01 -d biz -U samin --command \
\"delete from biz_push where push_time < to_date('${DELETE_TIME}', 'yyyy-MM-dd')\""

LOG_TIME="清除数据任务执行成功："$(date +%Y/%m/%d-%H:%M:%S)
echo $LOG_TIME >> /root/BackupLog.txt