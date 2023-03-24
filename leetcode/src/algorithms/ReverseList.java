package algorithms;

import algorithms.base.model.ListNode;

/**
 * 反转链表
 *
 * @author samin
 * @date 2021-01-11
 */
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
        ListNode l1 = ListNode.listNodeGenerator(1, 2, 3, 4, 5);
        ListNode.listNodePrinter(l1);
        ListNode nl = new ReverseList().reverseList(l1);
        ListNode.listNodePrinter(nl);
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
