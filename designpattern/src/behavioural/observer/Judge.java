package behavioural.observer;

/**
 * 订阅
 *
 * @author samin
 * @date 2022-10-14
 */
public class Judge extends Subject {

    public void instruction() {
        System.out.println("123,木头人！");
        // 通知所有观察者
        notifyObserver();
    }
}