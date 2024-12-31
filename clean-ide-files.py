import os
import shutil

# 脚本作用：删除 IDEA 工程文件、日志文件

# $ python clean-idea-project-files.py

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
    dir_path = os.getcwd()
    print("[current work directory] : " + dir_path)
    CleanTool.clean_dir(dir_path, ".idea")
    CleanTool.clean_dir(dir_path, "out")
    CleanTool.clean_dir(dir_path, "target")
    CleanTool.clean_file(dir_path, ".iml")
    CleanTool.clean_file(dir_path, ".log")