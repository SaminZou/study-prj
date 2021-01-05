package algorithms;

public class IsUnique {

    public static void main(String[] args) {
        // false
        System.out.println(new IsUnique().isUnique("leetcode"));

        // true
        System.out.println(new IsUnique().isUnique("abc"));

        // true
        System.out.println(new IsUnique().isUnique("a"));
    }

    public boolean isUnique(String astr) {
        if (astr.length() == 1) {
            return true;
        }

        char[] chars = astr.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
