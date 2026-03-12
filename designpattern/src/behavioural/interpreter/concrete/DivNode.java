package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;
import behavioural.interpreter.expression.SymbolNode;

/**
 * DivNode - Division Operator in Interpreter Pattern
 * <p>
 * Represents the division operation in the expression grammar.
 * This is a non-terminal expression that divides its left operand by its right operand.
 * <p>
 * Educational Note: Division nodes demonstrate how binary operations
 * are implemented in the interpreter pattern - by interpreting children and combining.
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public class DivNode extends SymbolNode {

    /**
     * Constructor for division operator node.
     * @param left The left operand (dividend) expression node
     * @param right The right operand (divisor) expression node
     * Educational Note: Division by zero is not handled here -
     * this would be an important enhancement for production code.
     */
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    /**
     * Performs division of the interpreted left operand by the right operand.
     * @return The quotient of left.interpret() divided by right.interpret()
     * Educational Note: This shows integer division behavior.
     * For educational purposes, we might want to handle division by zero.
     */
    @Override
    public int interpret() {
        int divisor = right.interpret();
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return left.interpret() / divisor;
    }
}
