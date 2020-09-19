package com.samin.leetcode;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // 等于0和1的特殊情况
        if (strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        // 找最短
        String minStr = strs[0];
        for (String ele : strs) {
            if (ele.length() < minStr.length()) {
                minStr = ele;
            }
        }

        // 找答案
        boolean isMatch = false;
        while (!isMatch) {
            for (int i = 0; i < strs.length; i++) {
                // 不是开头则减短条件字符，跳出循环继续搜索
                if (!(strs[i].indexOf(minStr) == 0)) {
                    if (minStr.length() > 0) {
                        minStr = minStr.substring(0, minStr.length() - 1);
                        isMatch = false;
                        break;
                    } else {
                        return "";
                    }
                }

                isMatch = true;
            }
        }

        return minStr;
    }

    public static void main(String[] args) {
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{
//                "flower", "flow", "flight"
//        }));
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{
//                "dog", "racecar", "car"
//        }));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{
                "ca", "a"
        }));
    }
}
