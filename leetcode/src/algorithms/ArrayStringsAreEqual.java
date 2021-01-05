package algorithms;

// 检查两个字符串数组是否相等
public class ArrayStringsAreEqual {

    public static void main(String[] args) {
        // true false true
        System.out.println(
                new ArrayStringsAreEqual()
                        .arrayStringsAreEqual(new String[] {"ab", "c"}, new String[] {"a", "bc"}));
        System.out.println(
                new ArrayStringsAreEqual()
                        .arrayStringsAreEqual(new String[] {"a", "cb"}, new String[] {"ab", "c"}));
        System.out.println(
                new ArrayStringsAreEqual()
                        .arrayStringsAreEqual(
                                new String[] {"abc", "d", "defg"}, new String[] {"abcddefg"}));
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for (String ele : word1) {
            s1.append(ele);
        }

        for (String ele : word2) {
            s2.append(ele);
        }

        return s1.toString().equals(s2.toString());
    }
}
