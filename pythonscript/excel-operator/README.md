# 安装依赖 

pip install pandas openpyxl

# 线上运行

如果使用的是 ubuntu22 以上环境，默认是 python3 环境，使用以下方式可以添加依赖包使用

> 可能出现错误：
> error: externally-managed-environment 
> 是因为系统默认将 Python 视为“外部管理的环境”，这是 Debian/Ubuntu 的新特性，旨在防止用户意外修改系统自带的 Python 环境

```shell
# 注意执行结果，有时候提示安装依赖
python3 -m venv myenv  

# 进入虚拟环境
source myenv/bin/activate

# 运行相关指令  
pip install mypackage somelibrary
  
python myscript.py

# 退出虚拟环境
deactivate
```

```bash
!#/bin/bash

# 激活虚拟环境
source /mnt/myenv/bin/activate
  
# 调用执行
python /mnt/excel-operator.py

# 退出虚拟环境
deactivate
```