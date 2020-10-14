package com.samin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 查找常用字符 */
public class CommonChars {

    public List<String> commonChars(String[] A) {
        // 计数结果
        Map<Character, Integer> countMap = new HashMap<>();

        List<Map<Character, Integer>> mapList = new ArrayList<>();
        Map<Character, Integer> tmpMap;
        // 单独计数结果
        for (String ele : A) {
            char[] eleChars = ele.toCharArray();

            // 字符串计数
            tmpMap = new HashMap<>();
            for (char c : eleChars) {
                if (tmpMap.containsKey(c)) {
                    tmpMap.put(c, tmpMap.get(c) + 1);
                } else {
                    tmpMap.put(c, 1);
                }
            }

            mapList.add(tmpMap);
        }

        // 放置对照元素
        Map<Character, Integer> indexMap = mapList.get(0);
        for (Character ele : indexMap.keySet()) {
            countMap.put(ele, indexMap.get(ele));
        }

        // 整合最终结果
        for (int i = 1; i < mapList.size(); i++) {
            for (Character ele : indexMap.keySet()) {
                if (mapList.get(i).get(ele) == null) {
                    countMap.remove(ele);
                } else {
                    if (countMap.get(ele) != null) {
                        countMap.put(ele, Math.min(countMap.get(ele), mapList.get(i).get(ele)));
                    }
                }
            }
        }

        // 生成结果
        List<String> res = new ArrayList<>();
        for (Character ele : countMap.keySet()) {
            for (int i = 0; i < countMap.get(ele); i++) {
                res.add(String.valueOf(ele));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String[] A = new String[] {"bella", "label", "roller"};
        String[] B = new String[] {"cool", "lock", "cook"};
        String[] C = new String[] {"abc", "def", "wer"};
        String[] D =
                new String[] {
                    "acabcddd",
                    "bcbdbcbd",
                    "baddbadb",
                    "cbdddcac",
                    "aacbcccd",
                    "ccccddda",
                    "cababaab",
                    "addcaccd"
                };

        //        System.out.println(new CommonChars().commonChars(A));
        //        System.out.println(new CommonChars().commonChars(B));
        //        System.out.println(new CommonChars().commonChars(C));
        System.out.println(new CommonChars().commonChars(D));
    }
}
