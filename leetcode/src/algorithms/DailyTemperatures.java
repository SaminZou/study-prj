package algorithms;

import java.util.Arrays;

/**
 * 每日温度
 *
 * @author samin
 * @date 2021-01-11
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] result = new DailyTemperatures().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});

        System.out.println(Arrays.toString(result));
    }

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            if (i == T.length - 1) {
                result[i] = 0;
            } else {
                for (int j = i; j < T.length; j++) {
                    if (T[j] > T[i]) {
                        result[i] = j - i;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
