package behavioural.observer.subscriber;

/**
 * 观察者 / 订阅者
 *
 * @author samin
 * @date 2022-10-14
 */
public class PleasantSheep implements Observer {

    public String getName() {
        return "喜羊羊";
    }

    /**
     * 具体业务逻辑
     */
    @Override
    public void update(String msg) {
        System.out.println("喜羊羊收到通知：" + msg);
    }
}
