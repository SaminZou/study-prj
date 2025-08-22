import pexpect

# pip install pexpect

# 配置账号和密码（⚠️ 明文写在脚本里不安全，仅演示用，建议配置成环境变量）
username = "samin"
password = "test123456"

# 启动命令
child = pexpect.spawn("tools-cli login", encoding="utf-8")

# 匹配提示并输入账号
child.expect("账号：")
child.sendline(username)

# 匹配提示并输入密码
child.expect("密码：")
child.sendline(password)

# 交互模式（保持登录状态）
child.interact()

# 配置 ~/.zshrc 增加 alias lg="python /mnt/linux-autologin.sh"
