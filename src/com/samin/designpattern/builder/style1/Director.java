package com.samin.designpattern.builder.style1;

public class Director {

    private final Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /** 统一构造对象实例化方法 */
    public Production construct() {
        builder.buildPart1();
        builder.buildPart2();

        return this.builder.build();
    }
}
