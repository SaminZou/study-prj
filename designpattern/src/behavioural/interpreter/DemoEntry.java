package behavioural.interpreter;

import behavioural.interpreter.context.CalculatorContext;

/**
 * Interpreter Pattern Demo - Arithmetic Expression Calculator
 * <p>
 * This demo shows the Interpreter Pattern in action using an arithmetic expression calculator.
 * <p>
 * Interpreter Pattern Roles Demonstrated:
 * 1. Abstract Expression (Node) - Defines the interpret() method
 * 2. Terminal Expression (ValueNode) - Represents numeric values
 * 3. Non-Terminal Expressions (MulNode, DivNode, ModNode) - Represent operations
 * 4. Context (CalculatorContext) - Builds and evaluates expression trees
 * <p>
 * Key Educational Points:
 * - Grammar rules are represented as classes in an object hierarchy
 * - Expressions are built as Abstract Syntax Trees (AST)
 * - Interpretation happens through recursive traversal of the AST
 * - Easy to extend with new grammar rules
 * <p>
 * @author samin
 * @date 2021-01-05
 * @optimized 2025-03-12 for educational clarity
 */
public class DemoEntry {

    public static void main(String[] args) {
        System.out.println("=== Interpreter Pattern Demo: Arithmetic Calculator ===\n");

        // Example 1: Original expression from the code
        System.out.println("Example 1: Basic arithmetic expression");
        evaluateExpression("3 * 2 * 4 / 6 % 5");

        // Example 2: More complex expression
        System.out.println("\nExample 2: Complex expression with multiple operations");
        evaluateExpression("10 * 5 / 2 % 3");

        // Example 3: Single operation
        System.out.println("\nExample 3: Simple multiplication");
        evaluateExpression("7 * 8");

        // Example 4: Demonstrate error handling
        System.out.println("\nExample 4: Error handling demonstration");
        try {
            evaluateExpression("5 *"); // Incomplete expression
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        // Example 5: Division by zero protection
        System.out.println("\nExample 5: Division by zero protection");
        try {
            evaluateExpression("10 / 0");
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        System.out.println("\n=== Demo Complete ===");
        System.out.println("\nEducational Summary:");
        System.out.println("- The Interpreter Pattern represents grammar rules as classes");
        System.out.println("- Expressions are built as Abstract Syntax Trees (AST)");
        System.out.println("- Interpretation happens through recursive traversal");
        System.out.println("- Easy to extend with new operations and grammar rules");
        System.out.println("- Provides clear separation between grammar definition and interpretation");
    }

    /**
     * Helper method to evaluate an expression and display the result.
     * @param expression The arithmetic expression to evaluate
     */
    private static void evaluateExpression(String expression) {
        try {
            CalculatorContext context = new CalculatorContext();
            context.build(expression);
            int result = context.compute();
            System.out.println(expression + " = " + result);
        } catch (Exception e) {
            System.out.println("Failed to evaluate '" + expression + "': " + e.getMessage());
        }
    }
}
