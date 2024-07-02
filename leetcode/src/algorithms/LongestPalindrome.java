package algorithms;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 连接两字母单词得到的最长回文串
 * <p>
 * Description: 连接两字母单词得到的最长回文串
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-02
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        // 6
        System.out.println(new LongestPalindrome().longestPalindrome(new String[]{"lc", "cl", "gg"}));
        // 8
        System.out.println(new LongestPalindrome().longestPalindrome(new String[]{"ab", "ty", "yt", "lc", "cl", "ab"}));
        // 2
        System.out.println(new LongestPalindrome().longestPalindrome(new String[]{"cc", "ll", "xx"}));
    }

    /**
     * 解题思路：桶 + 贪心算法
     *
     * @param words
     * @return
     */
    public int longestPalindrome(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        // 声明桶统计字串出现数量
        Map<String, Integer> wordMap = new HashMap<>(words.length);

        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }

        int res = 0;
        boolean isMid = false;
        Iterator<String> iterator = wordMap.keySet()
                .iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            char[] wordChars = word.toCharArray();
            String rev = new String(new char[]{wordChars[1], wordChars[0]});

            // 字母相同
            if (word.equals(rev)) {
                if (wordMap.get(word) % 2 == 1) {
                    isMid = Boolean.TRUE;
                }

                // 找有几对，计算数量
                res += wordMap.get(word) / 2 * 4;
            }

            if (wordMap.containsKey(rev) && !word.equals(rev)) {
                // 如 lc、lc、cl，利用 min 方法来计算
                res += 4 * Math.min(wordMap.get(word), wordMap.get(rev));
                // 如 lc、cl 都会计算一次，需要避免重复计算
                iterator.remove();
            }
        }

        // 点睛之笔，可以计算出奇数对、单个相同字母、多个相同字母入参特殊情况
        if (isMid) {
            res += 2;
        }

        return res;
    }
}