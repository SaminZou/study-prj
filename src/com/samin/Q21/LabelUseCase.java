package com.samin.Q21;

public class LabelUseCase {
    public static void main(String[] args) {
        // 标签名可以随意定义，用于和 break 搭配，跳出多重循环
        stop:
        {
            for (int i = 0; i < 5; i++) {
                for (int j = 5; j < 10; j++) {
                    if (j == 6) {
                        break stop;
                    }
                    System.out.println("j:" + j);
                }
                System.out.println("i:" + i);
            }
        }
    }
}
