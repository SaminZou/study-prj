package com.samin.designpattern.responsibility;

/** 责任链模式 */
public class Client {

    public static void main(String[] args) {
        Demand demandA = new Demand(1, "加个按钮"); // 请求等级低
        Demand demandB = new Demand(2, "重构代码"); // 请求等级高
        Demand demandC = new Demand(3, "我要联谊"); // 请求等级高

        BossWife bossWife = new BossWife();
        Boss boss = new Boss();
        TechnicalManager technicalManager = new TechnicalManager();
        technicalManager.setNextHandler(boss); // 设置下一级
        boss.setNextHandler(bossWife);

        technicalManager.handleMessage(demandA);
        System.out.println("============================");
        technicalManager.handleMessage(demandB);
        System.out.println("============================");
        technicalManager.handleMessage(demandC);
    }
}
