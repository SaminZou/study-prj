package behavioural.interpreter.concrete;

import behavioural.interpreter.expression.Node;

/**
 * ValueNode - Terminal Expression in Interpreter Pattern
 * <p>
 * Represents a numeric value in the expression grammar.
 * This is a terminal expression (leaf node) in the expression tree.
 * <p>
 * Educational Note: Terminal expressions are the basic building blocks
 * of the grammar. They don't have child nodes and return their stored value.
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public class ValueNode implements Node {

    /** The numeric value stored by this terminal expression */
    private final int value;

    /**
     * Constructor for terminal value nodes.
     * @param value The numeric value to store
     * Educational Note: Value nodes are the leaves of the expression tree
     * and represent the actual data in the grammar.
     */
    public ValueNode(int value) {
        this.value = value;
    }

    /**
     * Returns the stored numeric value.
     * @return The value of this terminal expression
     * Educational Note: Terminal expressions simply return their value
     * without any further interpretation.
     */
    @Override
    public int interpret() {
        return this.value;
    }
}
