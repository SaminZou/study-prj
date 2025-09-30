#!/usr/bin/env python3
# fake_npm.py — 无限运行的 fake npm 模拟器
import time, random, datetime, sys, shutil, signal

try:
    from colorama import Fore, Style, init
    init(autoreset=True)
    COLOR = True
except Exception:
    COLOR = False

def colored(prefix, level):
    if not COLOR: return prefix
    if level == "info": return Fore.GREEN + prefix + Style.RESET_ALL
    if level == "warn": return Fore.YELLOW + prefix + Style.RESET_ALL
    if level == "error": return Fore.RED + prefix + Style.RESET_ALL
    return prefix

def log(level, msg, emoji=""):
    prefix = f"{level}"
    prefix = colored(prefix, level)
    print(f"{prefix} {emoji} {msg}")
    sys.stdout.flush()

def term_width():
    return shutil.get_terminal_size((80,20)).columns

def progress_bar(prefix_label, total_steps=30, duration=2.0):
    width = max(10, term_width() - 30)
    for i in range(total_steps+1):
        filled = int(width * i / total_steps)
        bar = "█"*filled + "-"*(width-filled)
        pct = int(i/total_steps*100)
        sys.stdout.write(f"\r{prefix_label} [{bar}] {pct}%")
        sys.stdout.flush()
        time.sleep(duration/total_steps)
    print()

def simulate_download(pkg):
    size = random.randint(100, 5000)
    downloaded = 0
    will_fail = random.random() < 0.05
    fail_at = random.randint(size//4, size//2) if will_fail else None

    log("info", f"Downloading {pkg}", "⬇️")
    while downloaded < size:
        step = random.randint(30,200)
        downloaded = min(size, downloaded+step)
        pct = int(downloaded/size*100)
        sys.stdout.write(f"\rinfo ⏳ {pkg} {pct}% ({downloaded}KB/{size}KB) ")
        sys.stdout.flush()
        time.sleep(random.uniform(0.05,0.2))

        if will_fail and downloaded >= fail_at:
            print()
            log("error", f"Failed to fetch {pkg}: network timeout", "❌")
            log("info", f"Retrying {pkg} ...", "🔁")
            time.sleep(random.uniform(0.5,2.0))
            will_fail = False

    print()
    log("info", f"Fetched {pkg}@{random.randint(1,9)}.{random.randint(0,9)}.{random.randint(0,9)}", "📦")

def simulate_npm_install(attempt=1, allow_fail=True):
    log("info", f"npm install (attempt {attempt})", "📥")
    time.sleep(random.uniform(0.5,1.2))
    deps = ["react","express","lodash","axios","chalk","moment","webpack","eslint","typescript","jest"]
    for _ in range(random.randint(5,12)):
        pkg = random.choice(deps)
        simulate_download(pkg)

        if random.random() < 0.07:
            log("warn", f"deprecated {pkg}@{random.randint(0,2)}.{random.randint(0,9)}.{random.randint(0,9)}", "⚠️")

        if allow_fail and random.random() < 0.04:
            log("error", f"npm ERR! {pkg} failed to build", "💥")
            # 退出
            # return False

    log("info", "added " + str(random.randint(200,800)) + " packages in " + str(random.randint(5,40)) + "s", "✅")
    return True

def simulate_npm_build():
    log("info", "npm run build", "🏗️")
    steps = ["Compiling modules","Bundling assets","Optimizing chunks","Writing output"]
    for step in steps:
        progress_bar(f"info {step}", total_steps=random.randint(10,20), duration=random.uniform(1.5,3.5))
        if random.random() < 0.08:
            log("warn", f"{step} took longer than expected", "🐢")
    log("info", "Build completed successfully", "🎉")

running = True
def handle_sigint(signum,frame):
    global running
    running = False
    print()
    log("info", "Received interrupt, stopping...", "✋")

signal.signal(signal.SIGINT, handle_sigint)

def main_loop():
    round_no = 0
    while running:
        round_no += 1
        ok = simulate_npm_install(attempt=round_no, allow_fail=True)
        if not ok:
            log("info", "Retrying npm install ...", "🔄")
            time.sleep(2)
            simulate_npm_install(attempt=round_no, allow_fail=False)
        simulate_npm_build()
        pause = random.uniform(5,15)
        log("info", f"Waiting {pause:.1f}s before next cycle...", "💤")
        time.sleep(pause)
    log("info", "Fake npm stopped. Bye!", "👋")

if __name__ == "__main__":
    log("info", "Starting fake npm loop (CTRL+C to stop)", "🚀")
    main_loop()
