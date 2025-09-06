import smtplib
from email.mime.text import MIMEText
from email.utils import formataddr

def test_smtp(server, port, sender_email, sender_name, password, receiver_email):
    try:
        # 构造邮件
        msg = MIMEText("这是一封测试邮件，来自 SMTP 配置测试。", "plain", "utf-8")
        msg['From'] = formataddr((sender_name, sender_email))
        msg['To'] = formataddr(("Receiver", receiver_email))
        msg['Subject'] = "SMTP 配置测试"

        # 连接 SMTP 服务器
        smtp = smtplib.SMTP(server, port, timeout=10)

        # 如果端口是 587 通常需要 STARTTLS
        if port == 587:
            smtp.starttls()

        # 登录 SMTP
        smtp.login(sender_email, password)

        # 发送邮件
        smtp.sendmail(sender_email, [receiver_email], msg.as_string())
        smtp.quit()

        print("✅ 邮件发送成功，SMTP 配置正常。")

    except Exception as e:
        print("❌ 邮件发送失败:", str(e))


if __name__ == "__main__":
    # SMTP 服务器地址
    SMTP_SERVER = "smtp.163.com"
    # 常见端口: 25, 465(SSL), 587(TLS)
    SMTP_PORT = 25
    SENDER_EMAIL = "123456@163.com"
    SENDER_NAME = "Samin"
    # 邮箱密码或授权码
    PASSWORD = "Test123"
    RECEIVER_EMAIL = "123456@qq.com"

    test_smtp(SMTP_SERVER, SMTP_PORT, SENDER_EMAIL, SENDER_NAME, PASSWORD, RECEIVER_EMAIL)

