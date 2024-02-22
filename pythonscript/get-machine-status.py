import psutil

def get_cpu_usage():
    cpu_percent = psutil.cpu_percent(interval=1)
    return cpu_percent

def get_disk_usage():
    disk_usage = psutil.disk_usage('/')
    total = disk_usage.total // (2**30)  # Convert to GB
    used = disk_usage.used // (2**30)
    free = disk_usage.free // (2**30)
    return total, used, free

def get_disk_io():
    disk_io = psutil.disk_io_counters()
    read_bytes = disk_io.read_bytes // (2**20)  # Convert to MB
    write_bytes = disk_io.write_bytes // (2**20)
    return read_bytes, write_bytes

cpu_usage = get_cpu_usage()
total_disk, used_disk, free_disk = get_disk_usage()
read_io, write_io = get_disk_io()

print(f'CPU Usage: {cpu_usage}%')
print(f'Total Disk Space: {total_disk}GB')
print(f'Used Disk Space: {used_disk}GB')
print(f'Free Disk Space: {free_disk}GB')
print(f'Read IO: {read_io}MB')
print(f'Write IO: {write_io}MB')