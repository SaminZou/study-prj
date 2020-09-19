package com.samin.designpattern.build;

public class BuilderB implements IBuilder {

    private Production production = new Production();

    @Override
    public void buildPart1() {
        System.out.println("构造法拉利的第一部分。");
        production.setPart1("This is part1 of Ferrari");
    }

    @Override
    public void buildPart2() {
        System.out.println("构造法拉利的第二部分。");
        production.setPart2("This is part2 of Ferrari");
    }

    @Override
    public Production build() {
        System.out.println("咔擦！法拉利已造好！");
        return production;
    }
}
