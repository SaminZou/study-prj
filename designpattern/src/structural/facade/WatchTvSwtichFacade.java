package structural.facade;

public class WatchTvSwtichFacade {

    Light light;
    AirCondition ac;
    Television tv;
    Screen screen;

    public WatchTvSwtichFacade(Light light, AirCondition ac, Television tv, Screen screen) {
        this.light = light;
        this.ac = ac;
        this.tv = tv;
        this.screen = screen;
    }

    public void on() {
        light.on(); // 首先开灯
        ac.on(); // 然后是打开空调
        screen.down(); // 把银幕降下来
        tv.on(); // 最后是打开电视
    }

    public void off() {
        tv.off(); // 首先关闭电视机
        screen.up(); // 银幕升上去
        ac.off(); // 空调关闭
        light.off(); // 最后关灯
    }
}
