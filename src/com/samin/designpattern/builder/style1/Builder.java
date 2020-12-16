package com.samin.designpattern.builder.style1;

public interface Builder {

    /**
     * 有多少个属性，就有多少个建造方法
     */
    public void buildPart1();

    public void buildPart2();

    /**
     * builder 模式一般通用返回实例化方法名
     */
    public Production build();
}
