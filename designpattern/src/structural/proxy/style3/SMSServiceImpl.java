package structural.proxy.style3;

public class SMSServiceImpl implements SMSService {

    @Override
    public void sendMessage() {
        System.out.println("【Samin】您正在执行重置密码操作，您的验证码为：1234，5分钟内有效，请不要将验证码转发给他人。");
    }
}
