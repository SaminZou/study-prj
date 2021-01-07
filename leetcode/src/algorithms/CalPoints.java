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

        List<String> list = new ArrayList<>();
        list.add(ops[0]);
        list.add(ops[1]);
        for (int i = 2; i < ops.length; i++) {
            if ("+".equals(ops[i])) {
                list.add(
                        String.valueOf(
                                Integer.parseInt(list.get(list.size() - 1))
                                        + Integer.parseInt(list.get(list.size() - 1))));

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
}
