package behavioural.interpreter;

import behavioural.interpreter.context.CalculatorContext;

/**
 * 解释器模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        String statement = "3 * 2 * 4 / 6 % 5";

        CalculatorContext context = new CalculatorContext();

        context.build(statement);

        int result = context.compute();

        System.out.println(statement + " = " + result);
    }
}
