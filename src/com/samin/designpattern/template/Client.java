package com.samin.designpattern.template;

/*
 * 模板方法模式
 * */
public class Client {

    public static void main(String[] args) {
        ScanBicycle scanBicycle = new ScanBicycle();
        scanBicycle.isNeedUnlock(false);
        scanBicycle.use();

        CodeBicycle codeBicycle = new CodeBicycle();
        codeBicycle.use();
    }
}
