package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.ListNode;

public class GetKthFromEnd {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        ListNode result = new GetKthFromEnd().getKthFromEnd(l1, 2);
        System.out.println(result);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) { // 特殊情况
            return null;
        }

        ListNode temp = head;
        int index = 0;
        while (temp != null) { // 计算长度
            index += 1;
            temp = temp.next;
        }

        while (head != null) { // 遍历比较位置
            if (index == k) {
                return head;
            }

            index -= 1;
            head = head.next;
        }

        return null;
    }
}
