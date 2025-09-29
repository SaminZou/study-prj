import time
import random
import datetime
import sys
import shutil

# 可选，彩色打印
# pip install colorama

try:
    from colorama import Fore, Style, init
    init(autoreset=True)
    COLOR_ENABLED = True
except ImportError:
    COLOR_ENABLED = False

# 彩色日志
def log(level, msg, emoji=""):
    prefix = f"[{level}]"
    if COLOR_ENABLED:
        if level == "INFO":
            prefix = Fore.GREEN + prefix + Style.RESET_ALL
        elif level == "WARN":
            prefix = Fore.YELLOW + prefix + Style.RESET_ALL
        elif level == "ERROR":
            prefix = Fore.RED + prefix + Style.RESET_ALL
    print(f"{prefix} {emoji} {msg}")
    sys.stdout.flush()

# 随机生成 Java 类名
def random_class():
    prefixes = ["User", "Order", "Product", "Service", "Utils", "Manager", "Controller"]
    suffixes = ["Impl", "Handler", "Helper", "Config", "Factory", "Test", ""]
    return random.choice(prefixes) + random.choice(suffixes) + ".java"

# 绘制进度条
def progress_bar(total=30, duration=3):
    width = shutil.get_terminal_size((80, 20)).columns - 20
    for i in range(total + 1):
        filled = int(width * i / total)
        bar = "█" * filled + "-" * (width - filled)
        sys.stdout.write(f"\r[INFO] ⏳ [{bar}] {int(i/total*100)}%")
        sys.stdout.flush()
        time.sleep(duration / total)
    print()

# 主流程
def fake_maven():
    log("INFO", "Scanning for projects...", "🔍")
    time.sleep(1)
    log("INFO", "-" * 72)
    log("INFO", "Building fake-project 1.0-SNAPSHOT", "🛠️")
    time.sleep(1)

    modules = ["core", "service", "web", "persistence", "api", "common"]
    total_classes = random.randint(20, 50)

    for i in range(total_classes):
        module = random.choice(modules)
        classname = random_class()
        log("INFO", f"Compiling {module}/src/main/java/com/example/{classname}", "⚡")
        time.sleep(random.uniform(0.2, 0.7))

        # 偶尔插入进度条
        if random.random() < 0.1:
            progress_bar(total=20, duration=random.uniform(1.5, 3.0))

        # 偶尔加警告
        if random.random() < 0.07:
            log("WARN", f"{classname}: uses unchecked or unsafe operations.", "⚠️")

    # 模拟结束
    log("INFO", "-" * 72)
    log("INFO", "BUILD SUCCESS", "✅")
    log("INFO", f"Total time: {random.randint(30, 120)} s", "⏱️")
    log("INFO", f"Finished at: {datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')}", "📅")
    log("INFO", f"Final Memory: {random.randint(80, 200)}M/{random.randint(256, 512)}M", "💾")

if __name__ == "__main__":
    fake_maven()
