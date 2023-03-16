package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一个只出现一次的字符
 *
 * <p> https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * @author samin
 * @date 2021-01-11
 */
public class FirstUniqChar {

    public static void main(String[] args) {
        // l
        System.out.println(new FirstUniqChar().firstUniqChar("leetcode"));

        // b
        System.out.println(new FirstUniqChar().firstUniqChar("abaccdeff"));

        // 空格
        System.out.println(new FirstUniqChar().firstUniqChar(""));

        // 空格
        System.out.println(new FirstUniqChar().firstUniqChar("abab"));
    }

    public char firstUniqChar(String str) {
        char[] chars = str.toCharArray();

        Map<Character, Integer> temp = new HashMap<>();
        for (char c : chars) {
            if (temp.containsKey(c)) {
                temp.put(c, c + 1);
            } else {
                temp.put(c, 1);
            }
        }

        for (char c : chars) {
            if (temp.get(c) == 1) {
                return c;
            }
        }

        return ' ';
    }
}
