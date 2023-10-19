package behavioural.command.receiver;

/**
 * Receiver
 * <p>
 * Description: Receiver
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 */
public class TelevisionReceiver {

    public void open() {
        System.out.println("打开电视机......");
    }

    public void close() {
        System.out.println("关闭电视机......");
    }

    public void changeChannel(int i) {
        System.out.println("切换电视频道......现在的频道是：" + i);
    }
}
