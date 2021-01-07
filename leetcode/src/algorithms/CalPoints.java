package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 棒球比赛
 *
 * @author samin
 * @date 2021-01-07
 */
public class CalPoints {

    public int calPoints(String[] ops) {
        int sum = 0;

        // 长度小于2的输入，统一处理方式
        if (ops.length <= 2) {
            for (String ele : ops) {
                sum += Integer.parseInt(ele);
            }

            return sum;
        }

        int index = 1;
        List<String> list = new ArrayList<>();
        list.add(ops[0]);
        // 判断第二个字符是否是数字
        if (!"D".equals(ops[1]) && !"C".equals(ops[1])) {
            list.add(ops[1]);
            index += 1;
        }

        // 遍历计算结果
        for (int i = index; i < ops.length; i++) {
            if ("+".equals(ops[i])) {
                list.add(
                        String.valueOf(
                                Integer.parseInt(list.get(list.size() - 1))
                                        + Integer.parseInt(list.get(list.size() - 2))));

                continue;
            }

            if ("D".equals(ops[i])) {
                list.add(String.valueOf(Integer.parseInt(list.get(list.size() - 1)) * 2));

                continue;
            }

            if ("C".equals(ops[i])) {
                list.remove(list.size() - 1);

                continue;
            }

            list.add(ops[i]);
        }

        for (String ele : list) {
            sum += Integer.parseInt(ele);
        }

        return sum;
    }

    public static void main(String[] args) {
        // 30
        System.out.println(new CalPoints().calPoints(new String[] {"5", "2", "C", "D", "+"}));
        // 27
        System.out.println(
                new CalPoints().calPoints(new String[] {"5", "-2", "4", "C", "D", "9", "+", "+"}));
        // 1
        System.out.println(new CalPoints().calPoints(new String[] {"1"}));
        // 15
        System.out.println(new CalPoints().calPoints(new String[] {"1", "D", "D", "D"}));
    }
}
