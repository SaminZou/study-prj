package com.samin.designpattern.responsibility;

public class Demand {

    private int level;
    private String detail;

    Demand(int level, String detail) {
        this.level = level;
        this.detail = detail;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
