package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle {

    public static void main(String[] args) {
        //        ListNode l1 = new ListNode(1);
        //        System.out.println(new DetectCycle().detectCycle(l1));

        //        ListNode l1 = new ListNode(1);
        //        ListNode l2 = new ListNode(2);
        //        l1.next = l2;
        //        l2.next = l1;
        //        System.out.println(new DetectCycle().detectCycle(l1));

        //        ListNode l1 = new ListNode(3);
        //        ListNode l2 = new ListNode(2);
        //        ListNode l3 = new ListNode(0);
        //        ListNode l4 = new ListNode(-4);
        //        l1.next = l2;
        //        l2.next = l3;
        //        l3.next = l4;
        //        l4.next = l2;
        //        System.out.println(new DetectCycle().detectCycle(l1));

        System.out.println(new DetectCycle().detectCycle(null));
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> tmp = new HashSet<>();

        while (head != null) {
            if (!tmp.add(head)) {
                return head;
            }

            head = head.next;
        }

        return null;
    }
}
