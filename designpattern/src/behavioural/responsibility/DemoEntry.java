package behavioural.responsibility;

import behavioural.responsibility.basehandler.HandlerChain;
import behavioural.responsibility.concretehandler.HandlerObj1;
import behavioural.responsibility.concretehandler.HandlerObj2;
import behavioural.responsibility.concretehandler.HandlerObj3;

/**
 * 责任链模式 Chain of responsibility
 *
 * <p>责任链非常注重执行顺序，需要注意
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) throws Exception {
        // 构建责任链，也可以在 BaseHandler 中编排 ConcreteHandler，直接声明使用
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerObj1());
        chain.addHandler(new HandlerObj2());
        chain.addHandler(new HandlerObj3());

        // 执行
        chain.process(new Request("test"));
        chain.process(new Request(""));
    }
}
