package algorithms;

import java.util.Stack;

/**
 * 分割平衡字符串
 *
 * @author samin
 * @date 2021-01-11
 */
public class BalancedStringSplit {

    public static void main(String[] args) {
        // 4 3 1
        System.out.println(new BalancedStringSplit().balancedStringSplit("RLRRLLRLRL"));
        System.out.println(new BalancedStringSplit().balancedStringSplit("RLLLLRRRLR"));
        System.out.println(new BalancedStringSplit().balancedStringSplit("LLLLRRRR"));
    }

    public int balancedStringSplit(String s) {
        int res = 0;

        Stack<Character> temp = new Stack<>();
        for (Character ele : s.toCharArray()) {
            // 空的情况直接插入元素
            if (temp.size() == 0) {
                temp.push(ele);
            } else {
                // 不配对，入栈
                if (ele.equals(temp.peek())) {
                    temp.push(ele);
                    // 配对出栈，并判断栈是否为空，为空证明有相同数量的平衡字符串
                } else {
                    temp.pop();
                    if (temp.size() == 0) {
                        res += 1;
                    }
                }
            }
        }

        return res;
    }
}
