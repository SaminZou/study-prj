import wexpect

# pip install wexpect
# pip install setuptools

# 配置账号和密码（⚠️ 明文写在脚本里不安全，仅演示用，建议配置成环境变量）
username = "samin"
password = "test123456"

# 启动命令
child = wexpect.spawn("tools-cli login")

# 匹配提示并输入账号
child.expect("账号：")
child.sendline(username)

# 匹配提示并输入密码
child.expect("密码：")
child.sendline(password)

# 等待命令执行完成，这是静默方式
# child.wait()
# print("登录完成")

# 等待进程结束
child.wait()
# 读取进程的全部输出
output = child.read()
print(output)

# 配置 ~/.zshrc 增加 alias lg="python -W ignore /d/win-autologin.py"
# 由于依赖包有提醒内容，可以用 -W ignore 屏蔽