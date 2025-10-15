#!/bin/bash

set -o nounset
set -o errexit

# 仅面向 spring boot 项目，其他类型的项目启动进行微调即可

# 定义 jar 包路径和日志文件路径
JAR_PATH="app.jar"
LOG_FILE="app.log"
PID_FILE="run.pid"

# 检查 run.pid 文件是否存在且内容非空
if [ -f "$PID_FILE" ]; then
    PID=$(cat "$PID_FILE" 2>/dev/null)
    # 检查 PID 文件内容是否非空
    if [ -n "$PID" ]; then
        # 检查 PID 是否存在
        if kill -0 "$PID" 2>/dev/null; then
            echo "进程ID $PID 存在于系统中，正在停止..."
            kill "$PID"
            # 等待进程结束，防止立即重启
            wait "$PID" 2>/dev/null || true
            echo "进程已停止。"
        else
            echo "进程ID $PID 已不存在于系统中，但 PID 文件存在，正在清理PID文件..."
            rm "$PID_FILE"
        fi
    else
        echo "PID文件存在但内容为空，正在清理 PID 文件..."
        rm "$PID_FILE"
    fi
fi

# 启动 Java 应用
echo "正在启动 Java 应用..."
nohup java -jar "$JAR_PATH" -Dspring.profiles.active=prod \
-Xms4g -Xmx6g \
-XX:MaxDirectMemorySize=256m \
-XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m \
-XX:+UseG1GC \
-XX:ParallelGCThreads=4 -XX:MaxGCPauseMillis=200 -XX:ConcGCThreads=2 \
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/mnt/err-dump/heapdump.hprof \
-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -XX:NewRatio=3\
> "$LOG_FILE" 2>&1 &
PID=$!
echo "Java应用已启动，PID为：$PID"

# 将新的 PID 写入 run.pid 文件
echo $PID > "$PID_FILE"

echo "启动脚本执行完毕。"