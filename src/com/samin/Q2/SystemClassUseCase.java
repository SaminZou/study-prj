package com.samin.Q2;

import java.util.Arrays;

public class SystemClassUseCase {
    public static void main(String[] args) {
        // 数组复制
        int[] arr1 = new int[] {1, 2, 3, 4, 5};
        int[] arr2 = new int[3];
        // 模拟从arr1复制从2开头的3个数字到arr2;
        // 入参分别是源数组，源数组开头，目的数组，目的数组开头，复制长度
        System.arraycopy(arr1, 1, arr2, 0, 3);
        System.out.println(Arrays.toString(arr2));
    }
}
