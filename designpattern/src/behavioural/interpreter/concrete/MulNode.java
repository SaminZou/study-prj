package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;
import behavioural.interpreter.expression.SymbolNode;

/**
 * MulNode - Multiplication Operator in Interpreter Pattern
 * <p>
 * Represents the multiplication operation in the expression grammar.
 * This is a non-terminal expression that multiplies its left and right operands.
 * <p>
 * Educational Note: Operator nodes like MulNode are non-terminal expressions
 * that recursively interpret their child nodes and combine the results.
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public class MulNode extends SymbolNode {

    /**
     * Constructor for multiplication operator node.
     * @param left The left operand expression node
     * @param right The right operand expression node
     * Educational Note: The multiplication operation will be applied to
     * the results of interpreting the left and right child nodes.
     */
    public MulNode(Node left, Node right) {
        super(left, right);
    }

    /**
     * Performs multiplication of the interpreted left and right operands.
     * @return The product of left.interpret() and right.interpret()
     * Educational Note: This demonstrates how non-terminal expressions
     * recursively interpret their children and combine the results.
     */
    @Override
    public int interpret() {
        return left.interpret() * right.interpret();
    }
}
