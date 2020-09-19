package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.ListNode;

import java.util.ArrayList;

public class IsPalindromeListNode {

    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(-129);
        ListNode l2 = new ListNode(-129);
        l1.next = l2;

        //        ListNode l1 = new ListNode(1);
        //        ListNode l2 = new ListNode(2);
        //        ListNode l3 = new ListNode(2);
        //        ListNode l4 = new ListNode(1);
        //        l1.next = l2;
        //        l2.next = l3;
        //        l3.next = l4;
        System.out.println(new IsPalindromeListNode().isPalindrome(l1));
    }
}
