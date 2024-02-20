import os
import shutil

# 改脚本需要存放在项目的根目录

class CleanTool:
    @staticmethod
    def clean_dir(path, dirName):
        for root, dirs, files in os.walk(path):
            for name in dirs:
                if name == dirName:
                    print("[clean directory] : " + os.path.join(root, name))
                    shutil.rmtree(os.path.join(root, name))

    @staticmethod
    def clean_file(path, fileSuffix):
        for root, dirs, files in os.walk(path):
            for name in files:
                if name.endswith(fileSuffix):
                    print("[clean file] : " + os.path.join(root, name))
                    os.remove(os.path.join(root, name))


if __name__ == "__main__":
    # 获取当前目录路径
    dir_path = os.getcwd()
    print("[current work directory] : " + dir_path)
    # 删除工程文件夹
    CleanTool.clean_dir(dir_path, ".idea")
    # 删除工程文件
    CleanTool.clean_file(dir_path, ".iml")
    CleanTool.clean_file(dir_path, ".log")
