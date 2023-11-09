package behavioural.observer.publisher;

/**
 * 事件发布者
 *
 * @author samin
 * @date 2022-10-14
 */
public class Publisher {

    EventManger eventManger;

    public Publisher(EventManger eventManger) {
        this.eventManger = eventManger;
    }

    /**
     * 具体业务
     */
    public void action() {
        System.out.println("灰太狼来了！");
        // 通知所有观察者
        eventManger.notifySubscriber();
    }
}