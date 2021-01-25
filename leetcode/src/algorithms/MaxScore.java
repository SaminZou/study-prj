package algorithms;

/**
 * 分割字符串的最大得分
 *
 * @author samin
 * @date 2021-01-25
 */
public class MaxScore {

    public int maxScore(String s) {
        int max = 0;

        for (int i = 1; i <= s.length() - 1; i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            max = Math.max(max, stringWordCount(left, '0') + stringWordCount(right, '1'));
        }

        return max;
    }

    private int stringWordCount(String s, char source) {
        int length = 0;

        for (char ele : s.toCharArray()) {
            if (ele == source) {
                length += 1;
            }
        }

        return length;
    }

    public static void main(String[] args) {
        // 5
        System.out.println(new MaxScore().maxScore("011101"));

        // 5
        System.out.println(new MaxScore().maxScore("00111"));

        // 3
        System.out.println(new MaxScore().maxScore("1111"));
    }
}
