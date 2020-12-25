package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.ListNode;

import java.util.ArrayList;
import java.util.List;

// 二进制链表转整数
public class GetDecimalValue {

    public static void main(String[] args) {
        // 5 0 1 18880 0
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(0);
        ListNode l3 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        System.out.println(new GetDecimalValue().getDecimalValue(l1));

        ListNode l4 = new ListNode(0);
        System.out.println(new GetDecimalValue().getDecimalValue(l4));

        ListNode l5 = new ListNode(1);
        System.out.println(new GetDecimalValue().getDecimalValue(l5));

        ListNode l6 = new ListNode(1);
        ListNode l7 = new ListNode(0);
        ListNode l8 = new ListNode(0);
        ListNode l9 = new ListNode(1);
        ListNode l10 = new ListNode(0);
        ListNode l11 = new ListNode(0);
        ListNode l12 = new ListNode(1);
        ListNode l13 = new ListNode(1);
        ListNode l14 = new ListNode(1);
        ListNode l15 = new ListNode(0);
        ListNode l16 = new ListNode(0);
        ListNode l17 = new ListNode(0);
        ListNode l18 = new ListNode(0);
        ListNode l19 = new ListNode(0);
        ListNode l20 = new ListNode(0);
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        l10.next = l11;
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15;
        l15.next = l16;
        l16.next = l17;
        l17.next = l18;
        l18.next = l19;
        l19.next = l20;
        System.out.println(new GetDecimalValue().getDecimalValue(l6));

        ListNode l21 = new ListNode(0);
        ListNode l22 = new ListNode(0);
        l21.next = l22;
        System.out.println(new GetDecimalValue().getDecimalValue(l21));
    }

    public int getDecimalValue(ListNode head) {
        int res = 0;

        List<Integer> list = new ArrayList<>();
        // 加入到队列
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int index = 0;
        // 遍历求和
        for (int i = list.size() - 1; i >= 0; i--) {
            // 左移
            if (list.get(i) == 1) {
                res += 1 << index;
            }
            index += 1;
        }

        return res;
    }
}
