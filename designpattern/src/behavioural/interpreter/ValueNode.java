package behavioural.interpreter;

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
