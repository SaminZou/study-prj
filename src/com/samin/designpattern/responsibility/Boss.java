package com.samin.designpattern.responsibility;

public class Boss extends Handler {

    public Boss() {
        super(2);
    }

    @Override
    public void report(Demand demand) {
        System.out.println("需求：" + demand.getDetail());
        System.out.println(this.getClass().getSimpleName() + "：你们打一架吧，打赢的做决定");
    }
}
