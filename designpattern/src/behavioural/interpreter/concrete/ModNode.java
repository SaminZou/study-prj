package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;
import behavioural.interpreter.expression.SymbolNode;

public class ModNode extends SymbolNode {

    public ModNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return super.left.interpret() % super.right.interpret();
    }
}
