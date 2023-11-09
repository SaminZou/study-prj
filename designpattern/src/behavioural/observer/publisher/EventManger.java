package behavioural.observer.publisher;

import behavioural.observer.subscriber.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * 事件管理
 *
 * @author samin
 * @date 2022-10-14
 */
public class EventManger {

    /**
     * 观察者对象的集合
     */
    private final List<Observer> observerList = new ArrayList<>();

    /**
     * 登记观察者
     */
    public void subscribe(Observer observer) {
        observerList.add(observer);
        System.out.println("增加订阅者：" + observer.getName());
    }

    /**
     * 删除观察者
     */
    public void unSubscribe(Observer observer) {
        observerList.remove(observer);
        System.out.println("删除订阅者：" + observer.getName());
    }

    /**
     * 通知所有观察者
     */
    public void notifySubscriber() {
        for (Observer observer : observerList) {
            observer.update("开始逃跑！");
        }
    }
}