#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
数据库备份脚本
适用于 Ubuntu 24 + MySQL/MariaDB 环境
功能：
  - 备份整个数据库表结构
  - 可配置是否备份指定表数据

sudo apt update
sudo apt install mysql-client -y

"""

import subprocess
import datetime
import os

# =========================
# 配置区
# =========================

# 数据库连接信息
DB_CONFIG = {
    "host": "localhost",
    "user": "backup_user",
    "password": "your_password",
    "database": "your_database",
    "port": 3306
}

# 哪些表需要导出数据（留空表示只导出结构）
TABLES_WITH_DATA = [
    "users",
    "orders",
    "products"
]

# 备份目录
BACKUP_DIR = "/opt/db_backups"

# =========================
# 执行逻辑
# =========================

def ensure_dir(path):
    if not os.path.exists(path):
        os.makedirs(path, exist_ok=True)

def get_timestamp():
    return datetime.datetime.now().strftime("%Y%m%d_%H%M%S")

def run_cmd(cmd):
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    if result.returncode != 0:
        print("❌ 运行失败：", result.stderr)
        raise RuntimeError(f"命令执行失败: {cmd}")
    return result.stdout

def backup_database():
    ensure_dir(BACKUP_DIR)
    ts = get_timestamp()
    backup_path = os.path.join(BACKUP_DIR, f"{DB_CONFIG['database']}_{ts}.sql")

    # 临时创建 MySQL 配置文件（避免密码暴露在命令行）
    cnf_path = os.path.join(BACKUP_DIR, f"tmp_my_{ts}.cnf")
    with open(cnf_path, "w") as f:
        f.write(f"""[client]
user={DB_CONFIG['user']}
password={DB_CONFIG['password']}
host={DB_CONFIG['host']}
port={DB_CONFIG['port']}
""")

    print(f"🧩 备份数据库结构：{DB_CONFIG['database']}")
    cmd_structure = (
        f"mysqldump --defaults-extra-file={cnf_path} "
        f"--no-data --routines --events --triggers "
        f"{DB_CONFIG['database']} > {backup_path}"
    )
    run_cmd(cmd_structure)

    for table in TABLES_WITH_DATA:
        print(f"📦 导出表数据：{table}")
        cmd_data = (
            f"mysqldump --defaults-extra-file={cnf_path} "
            f"--no-create-info {DB_CONFIG['database']} {table} >> {backup_path}"
        )
        run_cmd(cmd_data)

    os.remove(cnf_path)
    print(f"✅ 备份完成：{backup_path}")

if __name__ == "__main__":
    backup_database()
