# 导出当前 python 环境已经安装的依赖包

$ pip freeze > requirements.txt

> requirements.txt 跟随项目到另一个环境之后，可以欢迎依赖快速安装

$ pip install -r requirements.txt

# Linux 系发行版使用 python 运行程序，建议使用 venv 进行系统 Python 环境和 用户 Python 环境的隔离

$ python3 -m venv .venv

# pyenv 负责版本切换，venv 负责依赖隔离 