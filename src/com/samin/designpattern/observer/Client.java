package com.samin.designpattern.observer;

/** 观察者模式 */
public class Client {

    public static void main(String[] args) {
        // 喊数人--被观察者
        Judge judge = new Judge();

        // 喜羊羊--观察者（参与游戏）
        Observer pleasantSheep = new PleasantSheep();
        // 登记观察者
        judge.attach(pleasantSheep);

        // 懒羊羊--观察者（参与游戏）
        Observer lazySheep = new LazySheep();
        // 登记观察者
        judge.attach(lazySheep);

        // 123，木头人
        judge.instruction();
    }
}
