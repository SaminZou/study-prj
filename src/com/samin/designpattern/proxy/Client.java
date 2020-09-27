package com.samin.designpattern.proxy;

/**
 * 代理模式
 *
 * 代理者和被代理者必须实现同一个接口
 * 代理者的目的是隐藏被代理者的行为
 */
public class Client {

    public static void main(String[] args) {
        Subject star = new Star();
        Subject proxy = new Agent(star);
        proxy.movie();
    }
}
