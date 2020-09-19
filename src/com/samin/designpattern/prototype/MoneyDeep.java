package com.samin.designpattern.prototype;

import java.io.Serializable;

public class MoneyDeep implements Cloneable, Serializable {

    private int faceValue;

    private AreaDeep areaDeep;

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public MoneyDeep(int faceValue, AreaDeep areaDeep) {
        this.faceValue = faceValue;
        this.areaDeep = areaDeep;
    }

    public AreaDeep getAreaDeep() {
        return areaDeep;
    }

    public void setAreaDeep(AreaDeep areaDeep) {
        this.areaDeep = areaDeep;
    }

    @Override
    protected MoneyDeep clone() throws CloneNotSupportedException {
        MoneyDeep cloneMoney = (MoneyDeep) super.clone();
        cloneMoney.areaDeep = this.areaDeep.clone(); // 增加Area的拷贝
        return cloneMoney;
    }
}
