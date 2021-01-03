package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.model.ListNode;

public class DeleteNode2 {

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
        ListNode l6 = new ListNode(6);
        l5.next = l6;
        new DeleteNode2().deleteNode(l3);
        System.out.println();
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
