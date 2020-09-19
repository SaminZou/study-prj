package com.samin.designpattern.build;

public interface IBuilder {

    // 产品有多少个组件，就有多少个建造方法
    public void buildPart1();

    public void buildPart2();

    // 返回产品类
    public Production build();
}
