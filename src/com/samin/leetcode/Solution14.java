package com.samin.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution14 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 特殊情况处理
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        // 获取所有整数
        List<Integer> list = new ArrayList<>();

        while (l1 != null) {
            list.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            list.add(l2.val);
            l2 = l2.next;
        }

        // 排序
        list.sort(Comparator.comparingInt(e -> e));

        // 生成链表
        ListNode cur = new ListNode(list.get(0));
        ListNode result = cur;
        int i = 1;
        while (i < list.size()) {
            ListNode ele = new ListNode(list.get(i));

            cur.next = ele;
            cur = ele;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        l11.next = l12;
        ListNode l13 = new ListNode(4);
        l12.next = l13;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        l21.next = l22;
        ListNode l23 = new ListNode(4);
        l22.next = l23;

        ListNode result = new Solution14().mergeTwoLists(l11, l21);

        System.out.println(result);
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
