package behavioural.strategy;

import behavioural.strategy.strategy.ConcreteStrategyA;
import behavioural.strategy.strategy.ConcreteStrategyB;

/**
 * 策略模式
 *
 * <p> 通过多态，让使用者感知不到内部策略的变化，策略方法本身是主体
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyA());
        context.contextInterface();

        context = new Context(new ConcreteStrategyB());
        context.contextInterface();
    }
}
