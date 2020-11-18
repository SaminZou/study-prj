package com.samin.designpattern.build.style1;

public class Production {

    private String part1;
    private String part2;

    public String getPart1() {
        System.out.println("part1: " + part1);
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        System.out.println("part2: " + part2);
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }
}
