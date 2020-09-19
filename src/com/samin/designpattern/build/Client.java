package com.samin.designpattern.build;

/*
 * 建造者模式
 * */
public class Client {

    public static void main(String[] args) {
        // 兰博基尼
        IBuilder builderA = new BuilderA();
        Director directorA = new Director(builderA);
        directorA.construct();

        // 法拉利
        IBuilder builderB = new BuilderB();
        Director directorB = new Director(builderB);
        directorB.construct();
    }
}
