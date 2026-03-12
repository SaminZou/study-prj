package behavioural.interpreter.context;

import behavioural.interpreter.concrete.DivNode;
import behavioural.interpreter.concrete.ModNode;
import behavioural.interpreter.concrete.MulNode;
import behavioural.interpreter.concrete.ValueNode;
import behavioural.interpreter.expression.Node;
import java.util.Stack;

/**
 * CalculatorContext - Interpreter Pattern Context Role
 * <p>
 * The context class that builds and evaluates expression trees.
 * This class parses arithmetic expressions and constructs the Abstract Syntax Tree (AST).
 * <p>
 * Educational Note: The context is responsible for:
 * - Parsing the input string according to the grammar rules
 * - Building the expression tree using terminal and non-terminal expressions
 * - Evaluating the expression by interpreting the root node
 * <p>
 * Current Grammar:
 * - Numbers: integer values
 * - Operators: * (multiplication), / (division), % (modulo)
 * - Expression format: space-separated tokens (e.g., "3 * 2 * 4 / 6 % 5")
 * <p>
 * Created By: Samin
 * <p>
 * Optimized For Educational Clarity: 2025-03-12
 */
public class CalculatorContext {

    /** The root node of the expression tree */
    private Node expressionTree;

    /**
     * Builds the expression tree from the input statement.
     * @param statement The arithmetic expression as a space-separated string
     * Educational Note: This method uses a stack-based approach to parse
     * the expression and build the Abstract Syntax Tree (AST).
     * @throws IllegalArgumentException if the expression is invalid
     */
    public void build(String statement) {
        if (statement == null || statement.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        Node left, right;
        Stack<Node> stack = new Stack<>();

        String[] tokens = statement.split("\\s+"); // Split by whitespace

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            
            // Handle operators
            if ("*".equals(token)) {
                if (stack.size() < 1) {
                    throw new IllegalArgumentException("Invalid expression: operator '*' requires left operand");
                }
                left = stack.pop();
                
                // Get next token as right operand
                if (i + 1 >= tokens.length) {
                    throw new IllegalArgumentException("Invalid expression: operator '*' requires right operand");
                }
                int rightValue = parseNumber(tokens[++i]);
                right = new ValueNode(rightValue);
                
                // Create multiplication node and push result
                stack.push(new MulNode(left, right));
                
            } else if ("/".equals(token)) {
                if (stack.size() < 1) {
                    throw new IllegalArgumentException("Invalid expression: operator '/' requires left operand");
                }
                left = stack.pop();
                
                if (i + 1 >= tokens.length) {
                    throw new IllegalArgumentException("Invalid expression: operator '/' requires right operand");
                }
                int rightValue = parseNumber(tokens[++i]);
                right = new ValueNode(rightValue);
                
                stack.push(new DivNode(left, right));
                
            } else if ("%".equals(token)) {
                if (stack.size() < 1) {
                    throw new IllegalArgumentException("Invalid expression: operator '%' requires left operand");
                }
                left = stack.pop();
                
                if (i + 1 >= tokens.length) {
                    throw new IllegalArgumentException("Invalid expression: operator '%' requires right operand");
                }
                int rightValue = parseNumber(tokens[++i]);
                right = new ValueNode(rightValue);
                
                stack.push(new ModNode(left, right));
                
            } else {
                // Handle numbers - push value nodes onto stack
                int value = parseNumber(token);
                stack.push(new ValueNode(value));
            }
        }

        // The final result should be the only node left in the stack
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: incomplete expression or extra operands");
        }
        
        this.expressionTree = stack.pop();
    }

    /**
     * Computes the result by interpreting the expression tree.
     * @return The result of evaluating the expression
     * Educational Note: This method starts the interpretation process
     * by calling interpret() on the root node of the expression tree.
     */
    public int compute() {
        if (expressionTree == null) {
            throw new IllegalStateException("Expression tree not built. Call build() first.");
        }
        return expressionTree.interpret();
    }

    /**
     * Parses a string token into an integer.
     * @param token The token to parse
     * @return The parsed integer value
     * @throws IllegalArgumentException if the token is not a valid integer
     */
    private int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: '" + token + "'");
        }
    }

    /**
     * Gets the current expression tree for debugging purposes.
     * @return The root node of the expression tree
     */
    public Node getExpressionTree() {
        return expressionTree;
    }
}
