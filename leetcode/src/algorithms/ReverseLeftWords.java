package algorithms;

/**
 * 左旋转字符串
 *
 * @author samin
 * @date 2021-01-11
 */
public class ReverseLeftWords {

    public static void main(String[] args) {
        System.out.println(new ReverseLeftWords().reverseLeftWords("", 0));
    }

    public String reverseLeftWords(String s, int n) {
        // 特殊情况
        if (s.length() == 0 || n == 0) {
            return s;
        }

        // 字符数组暂存
        char[] chars = new char[n];
        // 加入规定长度的字符
        for (int i = 0; i < n; i++) {
            chars[i] = s.charAt(i);
        }

        // 返回拼接结果
        return s.substring(n) + new String(chars);
    }
}
