package algorithms;

/**
 * 实现 strStr()
 *
 * @author samin
 * @date 2021-06-24
 */
public class StrStr {

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        System.out.println(new StrStr().strStr("hello", "ll"));
        System.out.println(new StrStr().strStr("hello", ""));
    }
}
