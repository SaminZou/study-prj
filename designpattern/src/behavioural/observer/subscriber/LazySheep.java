package behavioural.observer.subscriber;

/**
 * 观察者 / 订阅者
 *
 * @author samin
 * @date 2022-10-14
 */
public class LazySheep implements Observer {

    @Override
    public String getName() {
        return "懒羊羊";
    }

    @Override
    public void update(String msg) {
        System.out.println("懒羊羊收到通知：" + msg);
    }
}
