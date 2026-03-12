package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;
import behavioural.interpreter.expression.SymbolNode;

/**
 * ModNode - Modulo Operator in Interpreter Pattern
 * <p>
 * Represents the modulo operation in the expression grammar.
 * This is a non-terminal expression that computes the remainder of division.
 * <p>
 * Educational Note: Modulo operations are less common in basic arithmetic
 * but demonstrate the pattern's flexibility in supporting various operators.
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public class ModNode extends SymbolNode {

    /**
     * Constructor for modulo operator node.
     * @param left The left operand (dividend) expression node
     * @param right The right operand (divisor) expression node
     * Educational Note: Like division, modulo by zero should be handled.
     */
    public ModNode(Node left, Node right) {
        super(left, right);
    }

    /**
     * Performs modulo operation on the interpreted operands.
     * @return The remainder of left.interpret() divided by right.interpret()
     * Educational Note: This demonstrates the modulo operation behavior.
     */
    @Override
    public int interpret() {
        int divisor = right.interpret();
        if (divisor == 0) {
            throw new ArithmeticException("Modulo by zero is not allowed");
        }
        return left.interpret() % divisor;
    }
}
