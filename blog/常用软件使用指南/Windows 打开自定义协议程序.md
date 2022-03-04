```yaml
title: Windows 打开自定义协议程序
author: samin
date: 2021-12-26
```

1. 编写注册表，保存为“tim_protocol.reg”，内容如下（注意地址的写法）

```text
Windows Registry Editor Version 5.00
[HKEY_CLASSES_ROOT\tim]
@="tim Protocol"
"URL Protocol"=""
[HKEY_CLASSES_ROOT\tim\DefaultIcon]
@="E:\\Program Files\\Tencent\\TIM\\Bin\\TIM.exe"
[HKEY_CLASSES_ROOT\tim\shell]
@=""
[HKEY_CLASSES_ROOT\tim\shell\open]
@=""
[HKEY_CLASSES_ROOT\tim\shell\open\command]
@="\"E:\\Program Files\\Tencent\\TIM\\Bin\\TIM.exe\" "
```

2. 双击执行“tim_protocol.reg”，加入到注册表中

3. 打开浏览器，在地址栏中输入“tim://”，回车即可验证