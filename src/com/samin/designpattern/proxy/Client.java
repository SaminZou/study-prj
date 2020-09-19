package com.samin.designpattern.proxy;

/*
 * 代理模式
 * */
public class Client {

    public static void main(String[] args) {
        Subject star = new Star();
        Subject proxy = new Agent(star);
        proxy.movie();
    }
}
