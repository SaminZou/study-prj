import os

def print_unique_file_extensions(directory):
    extensions = set()  # 用于存储唯一后缀的集合

    for root, dirs, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            _, file_extension = os.path.splitext(file_path)
            extensions.add(file_extension)

    # 打印不重复的文件后缀
    for extension in extensions:
        print(extension)

# 指定要遍历的目录路径
directory_path = "D:\云资料\软考"

# 调用函数遍历目录并打印不重复的文件后缀
print_unique_file_extensions(directory_path)