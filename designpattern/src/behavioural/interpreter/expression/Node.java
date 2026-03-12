package behavioural.interpreter.expression;

/**
 * Node Interface - Interpreter Pattern Role
 * <p>
 * Defines the interpret() method that all expression nodes must implement.
 * This is the core of the Interpreter Pattern - representing grammar rules as objects.
 * <p>
 * Educational Note: Each class in the expression hierarchy implements this interface
 * to define how to interpret/execute that particular grammar element.
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public interface Node {

    /**
     * Interpret/execute this node.
     * @return The result of interpreting this expression node
     * Educational Note: This method is recursively called on child nodes
     * to evaluate the entire expression tree.
     */
    int interpret();
}
