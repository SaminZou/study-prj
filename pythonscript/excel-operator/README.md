pip install pandas openpyxl

如果使用的是 ubuntu 22 环境，默认是 python3 环境，使用以下方式可以添加依赖包使用

```shell
# 注意执行结果，有时候提示安装依赖
python3 -m venv myenv  
# 进入虚拟环境
source myenv/bin/activate
# 运行相关指令  
pip install mypackage somelibrary  
python myscript.py
```

```bash
!#/bin/bash

# 激活虚拟环境
source /mnt/myenv/bin/activate
  
# 调用执行
python /mnt/excel-operator.py
```