package algorithms;

import algorithms.base.model.ListNode;

public class Test {

    public static void main(String[] args) {
        // reverseList();
    }

    public static void reverseList() {
        ListNode l1 = ListNode.listNodeGenerator(1, 2, 3, 4, 5);

        ListNode pre = null;
        ListNode cur = l1;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        while (pre != null) {
            System.out.println(pre.val);
            pre = pre.next;
        }
    }
}