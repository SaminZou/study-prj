package behavioural.observer;

import behavioural.observer.publisher.Judge;
import behavioural.observer.subscriber.LazySheep;
import behavioural.observer.subscriber.Observer;
import behavioural.observer.subscriber.PleasantSheep;

/**
 * 观察者模式（发布订阅模式）
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        EventManger eventManger = new EventManger();

        // 喊数人--被观察者（发布者）
        Judge judge = new Judge(eventManger);

        // 喜羊羊--观察者（参与游戏）
        Observer pleasantSheep = new PleasantSheep();
        // 登记观察者
        eventManger.attach(pleasantSheep);

        // 懒羊羊--观察者（参与游戏）
        Observer lazySheep = new LazySheep();
        // 登记观察者
        eventManger.attach(lazySheep);

        // 123，木头人
        judge.instruction();

        // 解除观察者
        eventManger.dettach(lazySheep);

        // 123，木头人
        judge.instruction();
    }
}
