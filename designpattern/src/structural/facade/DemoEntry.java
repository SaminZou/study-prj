package structural.facade;

/**
 * 外观模式
 *
 * <p> 统一入口
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        // 实例化组件
        Light light = new Light();
        Television tv = new Television();
        AirCondition ac = new AirCondition();
        Screen screen = new Screen();

        WatchTvSwtichFacade watchTv = new WatchTvSwtichFacade(light, ac, tv, screen);

        watchTv.on();
        System.out.println("--------------可以看电视了.........");
        watchTv.off();
        System.out.println("--------------可以睡觉了...........");
    }
}
