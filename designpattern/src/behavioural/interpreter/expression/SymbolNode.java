package behavioural.interpreter.expression;

/**
 * SymbolNode Abstract Class - Interpreter Pattern Role
 * <p>
 * Abstract base class for all binary operator nodes in the expression tree.
 * Represents non-terminal expressions in the grammar.
 * <p>
 * Educational Note: Symbol nodes have left and right child nodes,
 * representing the operands for binary operations like +, -, *, /, etc.
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public abstract class SymbolNode implements Node {

    /** Left operand node */
    protected Node left;
    /** Right operand node */
    protected Node right;

    /**
     * Constructor for binary operator nodes.
     * @param left The left operand expression node
     * @param right The right operand expression node
     * Educational Note: This constructor builds the expression tree structure
     * by connecting operator nodes with their operand nodes.
     */
    public SymbolNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
