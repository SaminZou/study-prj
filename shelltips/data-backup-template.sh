#!/bin/bash

set -o nounset
set -o errexit

# Tips：
# 1. 注意命名，如对 MongoDB 如果也做了备份，文件名也是 backup_ 开头，使用本会导致本备份文件异常 ！
# 2. 对应还原数据指令 pg_restore -U <username> -h 127.0.0.1 -p 5432 -F p -v -O -x -a "<backup_file>"

keep() {
  # 计算保留的文件数量
  KEEP_NUM=$1
  # 查找备份文件，并按修改时间倒序排序
  files=$(find "$2" -type f -name "backup_*" -printf '%T@\t%p\n' | sort -k1nr | cut -f2-)

  # 打印文件数量
  count=$(echo "$files" | wc -l)
  if [ $count -lt $KEEP_NUM ]; then
    echo "备份文件数量不足 $KEEP_NUM 个，无需删除"
    exit 0
  fi
  # 需要删除的文件数量
  delete=$(expr $count - $KEEP_NUM)
  echo "保留最新的 $KEEP_NUM 个备份文件，删除 $delete 个旧文件"
  # 删除旧的备份文件，调试时使用 echo "$files" | tail -n "$delete" | xargs echo rm -f 可以防止文件被删除，正式使用去掉 echo
  echo "$files" | tail -n "$delete" | xargs rm -f
}

# 设置备份文件名和相关路径
BACKUP_FILE_NAME="/postgresql_backup_data"
# 备份文件名
FILENAME="backup_$(date +%Y-%m-%d_%H-%M-%S).sql"
# 宿主机的备份文件夹路径
CONTAINER_VOLUME_DIR="/root/docker/pg/data$BACKUP_FILE_NAME"

# PG Docker 容器名
CONTAINER_NAME="auth_pg"
# 容器内部的备份文件夹路径
CONTAINER_BACKUP_DIR="/var/lib/postgresql/data$BACKUP_FILE_NAME"

# 数据库名
DATABASE_NAME="auth"
# 数据库连接用户名
USERNAME="auth"

# 检查文件夹是否存在
if [ ! -d "$CONTAINER_VOLUME_DIR" ]; then
  mkdir -p $CONTAINER_VOLUME_DIR
  # 快捷显示备份文件，可以注释去掉
  ln -s $CONTAINER_VOLUME_DIR "/data$BACKUP_FILE_NAME"
fi

# 使用 pg_dump 导出数据库
docker exec $CONTAINER_NAME bash -c "pg_dump -U $USERNAME -h 127.0.0.1 -p 5432 -F p -b -f $CONTAINER_BACKUP_DIR/$FILENAME $DATABASE_NAME"

# 如果遇上需要输入密码的报错，使用以下指令，使用 PGPASSWORD 指定密码
# docker exec $CONTAINER_NAME bash -c "export PGPASSWORD=<password> && pg_dump -U $USERNAME -h 127.0.0.1 -p 5432 -F p -b -f $CONTAINER_BACKUP_DIR/$FILENAME $DATABASE_NAME"

echo "数据备份执行成功：$BACKUP_DIR$FILENAME"

# 删除超过 4 周的备份文件，-mtime 是查找多少天以前的文件
# find "$CONTAINER_VOLUME_DIR" -type f -name "backup_*" -mtime +28 -delete

# 查找备份文件，并按修改时间倒序排序，保留前 4 个文件
keep 4 $CONTAINER_VOLUME_DIR