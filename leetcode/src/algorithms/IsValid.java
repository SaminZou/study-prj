package algorithms;

import java.util.Stack;

/**
 * 有效的括号
 *
 * <p> https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author samin
 * @date 2021-01-11
 */
public class IsValid {

    public static void main(String[] args) {
        // true
        System.out.println(new IsValid().isValid("()"));
        // true
        System.out.println(new IsValid().isValid("()[]{}"));
        // true
        System.out.println(new IsValid().isValid("([{}])"));
        // false
        System.out.println(new IsValid().isValid("(]"));
        // false
        System.out.println(new IsValid().isValid("]"));
        // false
        System.out.println(new IsValid().isValid("(])"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    stack.push(c);
                }

                switch (c) {
                    case ')':
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else {
                            stack.push(c);
                        }
                        break;
                    case ']':
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            stack.push(c);
                        }
                        break;
                    case '}':
                        if (stack.peek() == '{') {
                            stack.pop();
                        } else {
                            stack.push(c);
                        }
                        break;
                    default:
                }
            }
        }

        return stack.size() == 0;
    }
}
