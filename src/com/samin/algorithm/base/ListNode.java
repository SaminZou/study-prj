package com.samin.algorithm.base;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode listNodeGenerator(int[] arrs) {
        ListNode head = null;
        ListNode current = null;
        int index = 0;

        while (index < arrs.length) {
            if (head == null) {
                head = new ListNode(arrs[index]);
                current = head;
            } else {
                current.next = new ListNode(arrs[index]);
                current = current.next;
            }

            index = index + 1;
        }

        return head;
    }

    public static void listNodePrinter(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println("");
    }
}
