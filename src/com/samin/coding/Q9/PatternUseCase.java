package com.samin.coding.Q9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUseCase {
    /** 使用java的正则表达式，需要了解Pattern(模式类)和Matcher(匹配器类) */
    public static void main(String[] args) {
        String str = "822085977@!qq.(com)";
        String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 输入需要被匹配的字符串
        Matcher matcher = pattern.matcher(str);
        // 输出所有结果
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
