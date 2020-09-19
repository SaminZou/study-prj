package com.samin.designpattern.responsibility;

public class BossWife extends Handler {

    public BossWife() {
        super(3);
    }

    @Override
    public void report(Demand demand) {
        System.out.println("需求：" + demand.getDetail());
        System.out.println(this.getClass().getSimpleName() + "：不要怂就是干...");
    }
}
