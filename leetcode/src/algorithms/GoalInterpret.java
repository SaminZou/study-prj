package algorithms;

/**
 * 设计 Goal 解析器
 *
 * @author samin
 * @date 2021-01-11
 */
public class GoalInterpret {

    public static void main(String[] args) {
        // "Goal"
        System.out.println(new GoalInterpret().interpret("G()(al)"));
        // "Gooooal"
        System.out.println(new GoalInterpret().interpret("G()()()()(al)"));
        // "alGalooG"
        System.out.println(new GoalInterpret().interpret("(al)G(al)()()G"));
    }

    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();

        StringBuilder temp = new StringBuilder();
        for (Character ele : command.toCharArray()) {
            if (ele.equals('G')) {
                sb.append(ele);
            } else {
                temp.append(ele);
            }

            if ("()".equals(temp.toString())) {
                sb.append("o");
                temp = new StringBuilder();
            }

            if ("(al)".equals(temp.toString())) {
                sb.append("al");
                temp = new StringBuilder();
            }
        }

        return sb.toString();
    }
}
