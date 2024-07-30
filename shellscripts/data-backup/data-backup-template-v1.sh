#!/bin/bash
# docker 容器内部执行备份指令
BACKUP_FILE_NAME_BY_TIME="BACKUP_"$(date +%Y%m%d%H%M%S)
docker exec -d samin-mongodb bash -c "mongodump --db biz --out /bitnami/${BACKUP_FILE_NAME_BY_TIME}"
docker exec -d samin-postgresql bash -c "pg_dump -h 127.0.0.1 -U test -d biz > /var/lib/postgresql/${BACKUP_FILE_NAME_BY_TIME}"
LOG_TIME="备份任务执行成功："$(date +%Y/%m/%d-%H:%M:%S)
echo $LOG_TIME >> /root/BackupLog.txt

# 执行自动清除脚本
ReservedNum=9
# 目录地址
mongoFileDir=/mnt/data/mongodb
# 查看目录数量
mongoFileNum=$(ls -l $mongoFileDir | grep "BACKUP*" | wc -l)
# 遍历处理文件夹
while(( $mongoFileNum > $ReservedNum))
do
    mongoOldFile=$(ls -rt $mongoFileDir | grep "BACKUP*" | head -1)
    echo  $LOG_TIME "Delete File:"$mongoOldFile >> /root/BackupLog.txt
    rm -rf $mongoFileDir/$mongoOldFile
    let "mongoFileNum--"
done

# 目录地址
postgresFileDir=/mnt/data/postgresql
# 查看目录数量
postgresFileNum=$(ls -l $postgresFileDir | grep "BACKUP*" | wc -l)
# 遍历处理文件夹
while(( $postgresFileNum > $ReservedNum))
do
    postgresOldFile=$(ls -rt $postgresFileDir | grep "BACKUP*" | head -1)
    echo  $LOG_TIME "Delete File:"$postgresOldFile >> /root/BackupLog.txt
    rm -rf $postgresFileDir/$postgresOldFile
    let "postgresFileNum--"
done