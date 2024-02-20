import os

# 获取目录中的所有SQL文件
sql_files_dir = './files'
output_file_path = './test.sql'
sql_files = [f for f in os.listdir(sql_files_dir) if f.endswith('.sql')]

# 创建输出文件
with open(output_file_path, 'w', encoding='UTF-8') as output_file:
    # 遍历每个SQL文件
    for sql_file in sql_files:
        # 写入文件名作为注释
        output_file.write('-- File: {}\n'.format(sql_file))

        # 读取SQL文件内容
        with open(os.path.join(sql_files_dir, sql_file), 'r', encoding='UTF-8') as input_file:
            # 将SQL文件内容写入输出文件
            output_file.write(input_file.read())

        # 添加分隔行
        output_file.write('\n-- -------------------------\n\n')