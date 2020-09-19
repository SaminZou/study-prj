package com.samin.designpattern.responsibility;

public class TechnicalManager extends Handler {

    public TechnicalManager() {
        super(1);
    }

    @Override
    public void report(Demand demand) {
        System.out.println("需求：" + demand.getDetail());
        System.out.println(this.getClass().getSimpleName() + "：小猿我挺你，这个需求不干");
    }
}
