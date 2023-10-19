package behavioural.observer;

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
    public void attach(Observer observer) {
        observerList.add(observer);
        System.out.println("增加了观察者(开始被观察)：" + observer.getName());
    }

    /**
     * 删除观察者
     */
    public void dettach(Observer observer) {
        observerList.remove(observer);
        System.out.println("删除了观察者：" + observer.getName());
    }

    /**
     * 通知所有观察者
     */
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update("停止运动！");
        }
    }
}