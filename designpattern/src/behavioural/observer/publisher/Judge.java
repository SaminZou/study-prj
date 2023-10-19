package behavioural.observer.publisher;

import behavioural.observer.EventManger;

/**
 * 事件发布者
 *
 * @author samin
 * @date 2022-10-14
 */
public class Judge {

    EventManger eventManger;

    public Judge(EventManger eventManger) {
        this.eventManger = eventManger;
    }

    public void instruction() {
        System.out.println("123,木头人！");
        // 通知所有观察者
        eventManger.notifyObserver();
    }
}