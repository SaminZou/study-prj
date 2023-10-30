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

    /**
     * 思路： 1. 左括号全部入栈 2. 遇上右括号则开始和栈顶匹配 3. 最终能够全部抵消说明符合规则，反之栈的数量大于 0 说明存在不合规的括号对
     *
     * @param s
     * @return
     */
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
