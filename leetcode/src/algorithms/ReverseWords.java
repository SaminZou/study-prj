package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻转单词顺序
 *
 * @author samin
 * @date 2021-01-11
 */
public class ReverseWords {

    public static void main(String[] args) {
        //        System.out.println(new Solution20().reverseWords("the sky is blue."));
        //        System.out.println(new Solution20().reverseWords(" hello world!"));
        //        System.out.println(new Solution20().reverseWords("  hello   world!"));
        //        System.out.println(new Solution20().reverseWords("a good    example"));
        System.out.println(new ReverseWords().reverseWords(" "));
    }

    public String reverseWords(String s) {
        // 特殊情况
        if (null == s || s.length() == 0) {
            return s;
        }

        List<String> strList = new ArrayList<>();
        // 遍历去掉前后空格后分割的字符数组
        for (String ele : s.trim()
                           .split(" ")) {
            // 去除多余空格造成的空字符串
            if (!"".equals(ele)) {
                strList.add(ele);
            }
        }

        // 只输入了空格的情况
        if (strList.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        // 倒序输出
        for (int i = strList.size() - 1; i >= 0; i--) {
            sb.append(strList.get(i));
            sb.append(" ");
        }

        return sb.substring(0, sb.toString()
                                 .length() - 1);
    }
}
