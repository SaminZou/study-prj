package behavioural.interpreter.context;

import behavioural.interpreter.concrete.DivNode;
import behavioural.interpreter.concrete.ModNode;
import behavioural.interpreter.concrete.MulNode;
import behavioural.interpreter.expression.Node;
import behavioural.interpreter.concrete.ValueNode;
import java.util.Stack;

public class CalculatorContext {

    private Node node;

    public void build(String statement) {
        Node left, right;
        Stack<Node> stack = new Stack<>();

        String[] statementArr = statement.split(" ");

        for (int i = 0; i < statementArr.length; i++) {
            // 计算解析
            if ("*".equalsIgnoreCase(statementArr[i])) {
                // 拿出数值
                left = stack.pop();
                int val = Integer.parseInt(statementArr[++i]);
                // 声明数值节点
                right = new ValueNode(val);
                // 计算后放入结果
                stack.push(new MulNode(left, right));
            } else if ("/".equalsIgnoreCase(statementArr[i])) {
                left = stack.pop();
                int val = Integer.parseInt(statementArr[++i]);
                right = new ValueNode(val);
                stack.push(new DivNode(left, right));
            } else if ("%".equalsIgnoreCase(statementArr[i])) {
                left = stack.pop();
                int val = Integer.parseInt(statementArr[++i]);
                right = new ValueNode(val);
                stack.push(new ModNode(left, right));
            } else {
                // 第一步一定是放入数值
                stack.push(new ValueNode(Integer.parseInt(statementArr[i])));
            }
        }

        // 结果
        this.node = stack.pop();
    }

    public int compute() {
        return node.interpret();
    }
}
