package com.samin.Q4;

public class SplitUseCase {
    public static void main(String[] args) {
        // 以"."为分隔符
        String str1 = "192.168.110.1";
        for (String ele : str1.split("\\.")) {
            System.out.println(ele);
        }
        System.out.println("-----------------------------");

        // 以"|"为分隔符
        String str2 = "aa|bb|cc|dd";
        for (String ele : str2.split("\\|")) {
            System.out.println(ele);
        }
        System.out.println("-----------------------------");

        // 需要多个分隔符，用"|"作为连接符
        String str3 = "1@2@3&4&5&6";
        for (String ele : str3.split("@|&")) {
            System.out.println(ele);
        }
    }
}
