package com.samin.designpattern.interpret;

public class ValueNode implements Node {

    private final int value;

    public ValueNode(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return this.value;
    }
}
