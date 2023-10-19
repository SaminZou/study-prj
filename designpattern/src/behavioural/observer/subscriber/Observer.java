package behavioural.observer.subscriber;


/**
 * 观察者
 *
 * @author samin
 * @date 2022-10-14
 */
public interface Observer {

    String getName();

    /**
     * 通知更新方法
     */
    void update(String msg);
}
