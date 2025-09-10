#!/bin/bash
# ====================================================================
# Spring Boot 应用管理脚本
# 支持启动、停止、重启、状态检查
# ====================================================================

set -o nounset
set -o errexit
set -o pipefail

# -----------------------------
# 可配置参数
# -----------------------------
APP_NAME="my-springboot-app"
JAR_PATH="app.jar"
LOG_FILE="app.log"
PID_FILE="run.pid"

# JVM 参数（直接写死）
JAVA_OPTS="-Xms4g -Xmx6g -XX:NewRatio=3 \
-XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m \
-XX:+UseG1GC -XX:ParallelGCThreads=4 -XX:ConcGCThreads=2 \
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof \
-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8"

# Spring Boot 应用参数（直接写死）
SPRING_OPTS="--spring.profiles.active=prod --server.port=8080"

# -----------------------------
# 内部函数
# -----------------------------

is_running() {
    local pid
    [ -f "$PID_FILE" ] && pid=$(cat "$PID_FILE" 2>/dev/null)
    if [ -n "${pid:-}" ] && kill -0 "$pid" 2>/dev/null; then
        echo "$pid"
        return 0
    else
        return 1
    fi
}

start_app() {
    if is_running >/dev/null; then
        echo "⚠️ $APP_NAME 已在运行，PID: $(cat $PID_FILE)"
        return 0
    fi
    echo "🚀 正在启动 $APP_NAME ..."
    nohup java $JAVA_OPTS -jar "$JAR_PATH" $SPRING_OPTS > "$LOG_FILE" 2>&1 &
    echo $! > "$PID_FILE"
    sleep 1
    if is_running >/dev/null; then
        echo "✅ $APP_NAME 启动成功，PID: $(cat $PID_FILE)"
    else
        echo "❌ 启动失败，请检查日志 $LOG_FILE"
        exit 1
    fi
}

stop_app() {
    local pid
    pid=$(is_running) || {
        echo "ℹ️ $APP_NAME 未运行"
        [ -f "$PID_FILE" ] && rm -f "$PID_FILE"
        return 0
    }
    echo "🛑 正在停止 $APP_NAME (PID: $pid)..."
    kill "$pid"
    for i in {1..10}; do
        if ! kill -0 "$pid" 2>/dev/null; then
            echo "✅ 已停止"
            rm -f "$PID_FILE"
            return 0
        fi
        sleep 1
    done
    echo "⚠️ 进程未响应，执行强制杀死"
    kill -9 "$pid" || true
    rm -f "$PID_FILE"
    echo "✅ 已强制停止"
}

status_app() {
    if pid=$(is_running); then
        echo "✅ $APP_NAME 正在运行，PID: $pid"
    else
        echo "ℹ️ $APP_NAME 未运行"
    fi
}

restart_app() {
    stop_app
    start_app
}

# -----------------------------
# 主逻辑
# -----------------------------
case "${1:-}" in
    start) start_app ;;
    stop) stop_app ;;
    restart) restart_app ;;
    status) status_app ;;
    *)
        echo "用法: $0 {start|stop|restart|status}"
        exit 1
        ;;
esac