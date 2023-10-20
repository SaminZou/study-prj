package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;
import behavioural.interpreter.expression.SymbolNode;

public class MulNode extends SymbolNode {

    public MulNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return left.interpret() * right.interpret();
    }
}
