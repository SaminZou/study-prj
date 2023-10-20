package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;

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
