package algorithms;

/**
 * 统计一致字符串的数目
 *
 * @author samin
 * @date 2021-01-11
 */
public class CountConsistentStrings {

    public static void main(String[] args) {
        // 2
        System.out.println(
                new CountConsistentStrings().countConsistentStrings("ab", new String[]{"ad", "bd", "aaab", "baa", "badab"}));

        // 7
        System.out.println(new CountConsistentStrings().countConsistentStrings("abc", new String[]{
                "a", "b", "c", "ab", "ac", "bc", "abc"
        }));

        // 4
        System.out.println(new CountConsistentStrings().countConsistentStrings("cad", new String[]{
                "cc", "acd", "b", "ba", "bac", "bad", "ac", "d"
        }));
    }

    public int countConsistentStrings(String allowed, String[] words) {
        int res = 0;

        for (String word : words) {
            for (int i = 0; i < word.toCharArray().length; i++) {
                if (!allowed.contains(String.valueOf(word.toCharArray()[i]))) {
                    break;
                }

                if (i == word.toCharArray().length - 1) {
                    res += 1;
                }
            }
        }

        return res;
    }
}
