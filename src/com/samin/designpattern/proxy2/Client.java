package com.samin.project.proxy2;

/*
* 这种方式是客户端直接访问被代理角色，代理由被代理角色指定。
* 前面的一种方式则是客户端不能访问直接访问被代理角色，只能访问代理。
* */
public class Client {
    public static void main(String[] args) {
        Subject star = new Star();
        Subject proxy = star.getAgent();
        proxy.movie();
    }
}
