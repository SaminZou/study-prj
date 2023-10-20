package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;
import behavioural.interpreter.expression.SymbolNode;

public class DivNode extends SymbolNode {

    public DivNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return super.left.interpret() / super.right.interpret();
    }
}
