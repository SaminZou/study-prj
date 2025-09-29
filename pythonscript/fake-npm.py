#!/usr/bin/env python3

import time
import random
import datetime
import sys
import shutil
import signal

# 可选，彩色打印
# pip install colorama

# optional color support
try:
    from colorama import Fore, Style, init
    init(autoreset=True)
    COLOR = True
except Exception:
    COLOR = False

def colored(prefix, level):
    if not COLOR:
        return prefix
    if level == "INFO":
        return Fore.GREEN + prefix + Style.RESET_ALL
    if level == "WARN":
        return Fore.YELLOW + prefix + Style.RESET_ALL
    if level == "ERROR":
        return Fore.RED + prefix + Style.RESET_ALL
    return prefix

def log(level, msg, emoji=""):
    prefix = f"[{level}]"
    prefix = colored(prefix, level)
    print(f"{prefix} {emoji} {msg}")
    sys.stdout.flush()

def random_class():
    prefixes = ["User","Order","Product","Service","Utils","Manager","Controller","Auth","Payment","Report"]
    suffixes = ["Impl","Handler","Helper","Config","Factory","Test","Dao","Service","Resource",""]
    return random.choice(prefixes) + random.choice(suffixes) + ".java"

def term_width():
    return shutil.get_terminal_size((80,20)).columns

def progress_bar(prefix_label, total_steps=30, duration=2.0):
    width = max(10, term_width() - 30)
    for i in range(total_steps + 1):
        filled = int(width * i / total_steps)
        bar = "█" * filled + "-" * (width - filled)
        pct = int(i/total_steps*100)
        sys.stdout.write(f"\r{prefix_label} [{bar}] {pct}%")
        sys.stdout.flush()
        time.sleep(duration / max(1, total_steps))
    print()

def simulate_download(dep_name):
    # 模拟一个依赖下载：随机速度、可能失败然后重试
    log("INFO", f"Downloading: {dep_name}", "⬇️")
    total_kb = random.randint(200, 3000)  # 假装大小
    downloaded = 0
    # 随机决定是否会“卡住/失败”一次
    will_fail = random.random() < 0.08
    fail_at = random.randint(int(total_kb*0.2), int(total_kb*0.9)) if will_fail else None

    start = time.time()
    while downloaded < total_kb:
        # chunk
        speed = random.uniform(50, 600)  # KB per second (fictional)
        step = min(total_kb - downloaded, int(speed * random.uniform(0.08, 0.3)))
        downloaded += step
        pct = int(downloaded / total_kb * 100)
        sys.stdout.write(f"\r[INFO] ⏳ Downloading {dep_name} {pct}% ({downloaded}KB/{total_kb}KB) ")
        sys.stdout.flush()
        time.sleep(random.uniform(0.05, 0.35))

        # simulate failure
        if will_fail and downloaded >= fail_at:
            print()  # newline
            log("ERROR", f"Failed to download {dep_name}: connection reset", "❌")
            # retry with backoff
            for attempt in range(1, 1 + random.randint(1,3)):
                backoff = attempt * random.uniform(0.5, 1.8)
                log("INFO", f"Retrying download ({attempt}) {dep_name} after {backoff:.1f}s ...", "🔁")
                time.sleep(backoff)
                # small chance retry still fails
                if random.random() < 0.2 and attempt < 3:
                    log("WARN", f"Retry {attempt} failed for {dep_name}", "⚠️")
                    continue
                else:
                    log("INFO", f"Resumed download {dep_name}", "⬇️")
                    will_fail = False
                    break
    elapsed = time.time() - start
    print()  # newline after progress
    log("INFO", f"Downloaded {dep_name} ({total_kb}KB) in {int(elapsed)}s", "📦")

def simulate_dependency_phase():
    # 假装有多个依赖，少数大包，多数小包
    deps = []
    for _ in range(random.randint(3,10)):
        group = random.choice(["org.apache.commons", "com.fasterxml.jackson", "org.springframework",
                               "io.netty", "org.hibernate", "com.google.guava", "com.mycompany.lib"])
        artifact = random.choice(["core","util","codec","client","server","api","jdbc","common"])
        version = f"{random.randint(1,4)}.{random.randint(0,9)}.{random.randint(0,9)}"
        deps.append(f"{group}:{artifact}:{version}")
    # 插入一个大依赖（模拟 jar 大）
    if random.random() < 0.4:
        deps.insert(random.randint(0,len(deps)), "com.large:big-bundle:5.0.0")

    log("INFO", "Resolving dependencies...", "🔗")
    time.sleep(random.uniform(0.6, 1.6))
    # 模拟并列下载（但在单终端我们顺序显示）
    for dep in deps:
        # 小概率显示 fast progress bar instead of numeric
        if random.random() < 0.2:
            progress_bar(f"[INFO] ⏬ {dep}", total_steps=random.randint(10,25), duration=random.uniform(0.6, 1.8))
        else:
            simulate_download(dep)
    log("INFO", "Dependencies resolved", "✅")

def run_one_build(attempt=1, allow_failure=True):
    log("INFO", f"Scanning for projects... (attempt {attempt})", "🔍")
    time.sleep(random.uniform(0.4, 1.2))
    log("INFO", "-" * min(72, term_width()))
    log("INFO", "Building fake-project 1.0-SNAPSHOT", "🛠️")
    time.sleep(random.uniform(0.4, 1.0))

    # 先模拟依赖下载阶段（mvn 会有）
    if random.random() < 0.95:
        simulate_dependency_phase()

    modules = ["core", "service", "web", "persistence", "api", "common", "auth", "batch"]
    total_classes = random.randint(12, 45)
    for i in range(total_classes):
        module = random.choice(modules)
        classname = random_class()
        log("INFO", f"Compiling {module}/src/main/java/com/example/{classname}", "⚡")
        time.sleep(random.uniform(0.12, 0.6))

        if random.random() < 0.12:
            progress_bar(total_steps=random.randint(8,18), duration=random.uniform(0.8,2.2))

        if random.random() < 0.07:
            log("WARN", f"{classname}: uses unchecked or unsafe operations.", "⚠️")

        # 小概率触发 mid-build failure
        if allow_failure and random.random() < 0.03:
            log("ERROR", f"Tests run: {random.randint(10,30)}, Failures: 1, Errors: 0, Skipped: {random.randint(0,2)}", "❌")
            log("ERROR", "There are test failures.", "💥")
            log("INFO", f"Total time: {random.randint(5, 40)} s", "⏱️")
            log("INFO", f"Finished at: {datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')}", "📅")
            print()
            return False

    log("INFO", "-" * min(72, term_width()))
    log("INFO", "BUILD SUCCESS", "✅")
    log("INFO", f"Total time: {random.randint(20, 240)} s", "⏱️")
    log("INFO", f"Finished at: {datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')}", "📅")
    log("INFO", f"Final Memory: {random.randint(80, 900)}M/{random.choice([512,1024,2048])}M", "💾")
    print()
    return True

# Optional graceful termination flag (Ctrl+C handled below)
running = True
def handle_sigint(signum, frame):
    global running
    running = False
    print()  # newline to keep terminal clean
    log("INFO", "Received interrupt — stopping after current build.", "✋")

signal.signal(signal.SIGINT, handle_sigint)

def main_loop():
    loop_count = 0
    # infinite loop
    while running:
        loop_count += 1
        # choose whether this run may fail (first run of loop occasionally fails)
        may_fail = random.random() < 0.35
        success = run_one_build(attempt=loop_count, allow_failure=may_fail)
        if not success:
            log("INFO", "Retrying build after failures...", "🔄")
            time.sleep(random.uniform(1.0, 3.0))
            # second attempt usually succeeds
            run_one_build(attempt=loop_count, allow_failure=False)

        # small randomized sleep between builds to mimic real dev cycles
        idle = random.uniform(2.0, 12.0)
        log("INFO", f"Sleeping {idle:.1f}s before next build...", "💤")
        # during this sleep, still check for interrupt
        sleep_end = time.time() + idle
        while running and time.time() < sleep_end:
            time.sleep(0.4)

    log("INFO", "Fake maven stopped. Bye!", "👋")

if __name__ == "__main__":
    # quick banner
    log("INFO", "Starting infinite fake maven (CTRL+C to stop)", "🚀")
    try:
        main_loop()
    except Exception as e:
        log("ERROR", f"Unexpected error: {e}", "💥")
        sys.exit(1)
