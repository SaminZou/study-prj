package behavioural.observer;

import behavioural.observer.publisher.EventManger;
import behavioural.observer.publisher.Publisher;
import behavioural.observer.subscriber.LazySheep;
import behavioural.observer.subscriber.Observer;
import behavioural.observer.subscriber.PleasantSheep;

/**
 * 观察者模式（发布订阅模式）
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        EventManger eventManger = new EventManger();
        Observer pleasantSheep = new PleasantSheep();
        Observer lazySheep = new LazySheep();

        // 添加订阅者
        eventManger.subscribe(pleasantSheep);
        // 添加订阅者
        eventManger.subscribe(lazySheep);

        // 声明事件发布者
        Publisher publisher = new Publisher(eventManger);

        // 灰太狼来了
        publisher.action();
        // 解除观察者
        eventManger.unSubscribe(lazySheep);
        // 灰太狼来了
        publisher.action();
    }
}
