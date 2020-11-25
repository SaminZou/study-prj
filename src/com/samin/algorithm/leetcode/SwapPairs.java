package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.ListNode;

import java.util.ArrayList;
import java.util.List;

public class SwapPairs {

    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        //        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        //        l3.next = l4;

        System.out.println(new SwapPairs().swapPairs(l1));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        // 暂存入参
        ListNode tmp = head;

        // 存储所有的变量
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        head = tmp;

        // 需要处理的数据数量，单数的情况下，最后的一个数字无法进行置换
        int size = list.size() % 2 == 0 ? list.size() : list.size() - 1;

        // 根据置换规则交换相邻数字
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                head.val = list.get(i + 1);
            } else {
                head.val = list.get(i - 1);
            }

            head = head.next;
        }

        return tmp;
    }
}
