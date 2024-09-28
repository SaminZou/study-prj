import time

def print_duration(start_time, end_time):
    # 计算时间差（秒）
    elapsed_time = end_time - start_time

    # 将秒数转换为天、小时、分钟和秒
    days = int(elapsed_time // (24 * 3600))
    elapsed_time %= (24 * 3600)
    hours = int(elapsed_time // 3600)
    elapsed_time %= 3600
    minutes = int(elapsed_time // 60)
    seconds = int(elapsed_time % 60)

    # 打印结果
    print(f"{days}天{hours}小时{minutes}分钟{seconds}秒")

# 记录开始时间
start_time = time.time()

# 模拟程序执行时间（这里使用sleep函数模拟）
import time
time.sleep(5)  # 假设程序执行了5秒

# 记录结束时间
end_time = time.time()

# 打印执行时间
print_duration(start_time, end_time)