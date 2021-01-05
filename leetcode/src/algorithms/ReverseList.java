package algorithms;

import algorithms.base.model.ListNode;

public class ReverseList {

    //    public ListNode reverseList(ListNode head) {
    //        if (head == null) {
    //            return null;
    //        }
    //
    //        ListNode pre = null;
    //        while (head != null) {
    //            ListNode temp = head.next;
    //            head.next = pre;
    //
    //            pre = head;
    //            head = temp;
    //        }
    //
    //        return pre;
    //    }

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
        ListNode nl = new ReverseList().reverseList(l1);
        System.out.println(nl);
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;

            head.next = pre;
            pre = head;

            head = temp;
        }

        return pre;
    }
}
