package behavioural.observer;

public interface Observer {

    String getName();

    // 通知更新方法
    void update(String msg);
}
