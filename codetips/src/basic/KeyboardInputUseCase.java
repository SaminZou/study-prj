package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Keyboard Input Utility Class - Provides multiple keyboard input methods
 * 
 * Features:
 * - Supports multiple input methods (Scanner, BufferedReader)
 * - Provides common data type inputs (string, integer, double, boolean)
 * - Complete input validation and error handling
 * - Supports interactive input and batch input
 * - Automatic resource management
 * 
 * @author samin
 * @date 2021-01-10
 * @version 2.0
 */
public class KeyboardInputUseCase {
    
    private static final Scanner SCANNER = new Scanner(System.in);
    
    /**
     * Main method - Demonstrates various input use cases
     */
    public static void main(String[] args) {
        System.out.println("=== Keyboard Input Use Case Demo ===\n");
        
        // Basic input demo
        demoBasicInput();
        
        // Data type input demo
        demoDataTypeInput();
        
        // Interactive input demo
        demoInteractiveInput();
        
        // Input validation demo
        demoInputValidation();
        
        SCANNER.close();
    }
    
    /**
     * Basic input method demo
     */
    private static void demoBasicInput() {
        System.out.println("1. Basic Input Demo:");
        
        // Use Scanner to input string
        String scannerInput = readStringWithScanner("Please enter a string (Scanner method): ");
        System.out.println("Scanner input result: " + scannerInput);
        
        // Use BufferedReader to input string
        try {
            String bufferedReaderInput = readStringWithBufferedReader("Please enter a string (BufferedReader method): ");
            System.out.println("BufferedReader input result: " + bufferedReaderInput);
        } catch (IOException e) {
            System.err.println("BufferedReader input error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Data type input demo
     */
    private static void demoDataTypeInput() {
        System.out.println("2. Data Type Input Demo:");
        
        // Integer input
        int intValue = readInt("Please enter an integer: ", 0, 100);
        System.out.println("Integer input result: " + intValue);
        
        // Double input
        double doubleValue = readDouble("Please enter a double: ", -1000.0, 1000.0);
        System.out.println("Double input result: " + doubleValue);
        
        // Boolean input
        boolean boolValue = readBoolean("Please enter boolean (true/false/yes/no/1/0): ");
        System.out.println("Boolean input result: " + boolValue);
        
        System.out.println();
    }
    
    /**
     * Interactive input demo
     */
    private static void demoInteractiveInput() {
        System.out.println("3. Interactive Input Demo (enter 'quit' to exit):");
        
        interactiveCalculator();
        System.out.println();
    }
    
    /**
     * Input validation demo
     */
    private static void demoInputValidation() {
        System.out.println("4. Input Validation Demo:");
        
        // Demo input validation
        int validatedInt = readIntWithRetry("Please enter an integer between 1-100: ", 1, 100, 3);
        System.out.println("Validated integer: " + validatedInt);
        
        System.out.println();
    }
    
    // ========== Basic Input Methods ==========
    
    /**
     * Read string using Scanner
     */
    public static String readStringWithScanner(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }
    
    /**
     * Read string using BufferedReader
     */
    public static String readStringWithBufferedReader(String prompt) throws IOException {
        System.out.print(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
    
    // ========== Data Type Input Methods ==========
    
    /**
     * Read integer with range validation
     */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = SCANNER.nextInt();
                SCANNER.nextLine(); // Clear newline character
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Input out of range [" + min + "-" + max + "], please re-enter");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input format error, please enter an integer");
                SCANNER.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Read double with range validation
     */
    public static double readDouble(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = SCANNER.nextDouble();
                SCANNER.nextLine(); // Clear newline character
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Input out of range [" + min + "-" + max + "], please re-enter");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input format error, please enter a number");
                SCANNER.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Read boolean (supports multiple formats)
     */
    public static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim().toLowerCase();
            
            if (input.equals("true") || input.equals("yes") || input.equals("1") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("0") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Input format error, please enter true/false/yes/no/1/0");
            }
        }
    }
    
    // ========== Advanced Features ==========
    
    /**
     * Interactive calculator
     */
    public static void interactiveCalculator() {
        System.out.println("Interactive Calculator - Supports +, -, *, / operations");
        
        while (true) {
            System.out.print("Enter expression (format: number operator number) or 'quit' to exit: ");
            String input = SCANNER.nextLine().trim();
            
            if ("quit".equalsIgnoreCase(input)) {
                System.out.println("Calculator exited");
                break;
            }
            
            try {
                String[] parts = input.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Format error, please use: number operator number");
                    continue;
                }
                
                double num1 = Double.parseDouble(parts[0]);
                String operator = parts[1];
                double num2 = Double.parseDouble(parts[2]);
                
                double result = calculate(num1, operator, num2);
                System.out.println("Calculation result: " + result);
                
            } catch (NumberFormatException e) {
                System.out.println("Number format error, please check input");
            } catch (ArithmeticException e) {
                System.out.println("Calculation error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Operator error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Integer input with retry mechanism
     */
    public static int readIntWithRetry(String prompt, int min, int max, int maxRetries) {
        int retries = 0;
        while (retries < maxRetries) {
            try {
                System.out.print(prompt);
                int value = SCANNER.nextInt();
                SCANNER.nextLine(); // Clear newline character
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Input out of range, remaining retries: " + (maxRetries - retries - 1));
                }
            } catch (InputMismatchException e) {
                System.out.println("Input format error, remaining retries: " + (maxRetries - retries - 1));
                SCANNER.nextLine(); // Clear invalid input
            }
            retries++;
        }
        
        System.out.println("Retries exhausted, using default value: " + min);
        return min;
    }
    
    /**
     * Simple arithmetic operations
     */
    private static double calculate(double num1, String operator, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
    
    /**
     * Clean up resources
     */
    public static void close() {
        if (SCANNER != null) {
            SCANNER.close();
        }
    }
}
