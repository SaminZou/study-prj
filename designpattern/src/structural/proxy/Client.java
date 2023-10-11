package structural.proxy;

import java.lang.reflect.Proxy;
import structural.proxy.sendmsg.MoneyCountInvocationHandler;
import structural.proxy.sendmsg.SMSService;
import structural.proxy.sendmsg.SMSServiceImpl;
import structural.proxy.style1.Agent;
import structural.proxy.style1.Star;
import structural.proxy.style1.Subject;
import structural.proxy.style2.Star2;
import structural.proxy.style2.Subject2;

/**
 * 代理模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // style1
        // 代理者和被代理者必须实现同一个接口 代理者的目的是隐藏被代理者的行为
        Subject star = new Star();
        Subject proxy = new Agent(star);
        proxy.movie();

        System.out.println("-------------------------------------------------------");

        // style2
        // 这种方式是客户端直接访问被代理角色，代理由被代理角色指定。
        // 前面的一种方式则是客户端不能访问直接访问被代理角色，只能访问代理。
        Subject2 star2 = new Star2();
        Subject2 proxy2 = star2.getAgent();
        proxy2.movie();

        System.out.println("-------------------------------------------------------");

        // style3
        // sendmsg 使用 JDK 动态代理来实现计算发短信计费
        SMSService smsService = new SMSServiceImpl();
        smsService = (SMSService) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{SMSService.class},
                                                         new MoneyCountInvocationHandler(smsService));
        smsService.sendMessage();
        smsService.sendMessage();
        smsService.sendMessage();
        smsService.sendMessage();
    }
}
