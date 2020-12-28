package com.samin.designpattern.template;

// 扫码开锁的单车
public class ScanBicycle extends AbstractClass {

    @Override
    protected void unlock() {
        System.out.println("========" + "扫码开锁" + "========");
    }

    @Override
    protected void ride() {
        System.out.println(getClass().getSimpleName() + "骑起来很拉风");
    }

    @Override
    protected void isNeedUnlock(boolean isNeedUnlock) {
        this.isLock = isNeedUnlock;
    }
}
