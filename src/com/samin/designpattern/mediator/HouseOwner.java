package com.samin.designpattern.mediator;

public class HouseOwner extends Person {

    HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    /**
     * @param message
     * @return void
     * @desc 与中介者联系
     */
    public void constact(String message) {
        mediator.constact(message, this);
    }

    /**
     * @param message
     * @return void
     * @desc 获取信息
     */
    public void getMessage(String message) {
        System.out.println("房主:" + name + ",获得信息：" + message);
    }
}
