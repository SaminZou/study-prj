package algorithms;

import algorithms.base.model.ListNode;

/**
 * 相交链表
 *
 * @author samin
 * @date 2021-01-11
 */
public class GetIntersectionNode {

    //    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //        if (headA == null || headB == null) {
    //            return null;
    //        }
    //
    //        int aLength = 0;
    //        int bLength = 0;
    //        for (ListNode temp = headA; temp != null; temp = temp.next, aLength++) {
    //        }
    //        for (ListNode temp = headB; temp != null; temp = temp.next, bLength++) {
    //        }
    //
    //        ListNode tempC = aLength > bLength ? headB : headA;
    //        while (tempC != null) {
    //            ListNode tempD = aLength > bLength ? headA : headB;
    //            while (tempD != null) {
    //                if (tempC==tempD) {
    //                    return tempC;
    //                }
    //                tempD = tempD.next;
    //            }
    //            tempC = tempC.next;
    //        }
    //
    //        return null;
    //    }

    public static void main(String[] args) {
        ListNode listA1 = new ListNode(2);
        ListNode listA2 = new ListNode(6);
        listA1.next = listA2;
        ListNode listA3 = new ListNode(4);
        listA2.next = listA3;

        ListNode listB1 = new ListNode(1);
        ListNode listB2 = new ListNode(5);
        listB1.next = listB2;

        ListNode result = new GetIntersectionNode().getIntersectionNode(listA1, listB1);
        System.out.println(result);

        //        ListNode listA1 = new ListNode(0);
        //        ListNode listA2 = new ListNode(9);
        //        listA1.next = listA2;
        //        ListNode listA3 = new ListNode(1);
        //        listA2.next = listA3;
        //        ListNode listA4 = new ListNode(2);
        //        listA3.next = listA4;
        //        ListNode listA5 = new ListNode(4);
        //        listA4.next = listA5;
        //
        //        ListNode listB1 = new ListNode(3);
        //        listB1.next = listA4;
        //
        //        ListNode result = new Solution19().getIntersectionNode(listA1, listB1);
        //        System.out.println(result);

        //        ListNode listA1 = new ListNode(4);
        //        ListNode listA2 = new ListNode(1);
        //        listA1.next = listA2;
        //        ListNode listA3 = new ListNode(8);
        //        listA2.next = listA3;
        //        ListNode listA4 = new ListNode(4);
        //        listA3.next = listA4;
        //        ListNode listA5 = new ListNode(5);
        //        listA4.next = listA5;
        //
        //        ListNode listB1 = new ListNode(5);
        //        ListNode listB2 = new ListNode(0);
        //        listB1.next = listB2;
        //        ListNode listB3 = new ListNode(1);
        //        listB2.next = listB3;
        //        listB3.next = listA3;
        //
        //        ListNode result = new Solution19().getIntersectionNode(listA1, listB1);
        //        System.out.println(result);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 计算长度
        int aLength = 0;
        int bLength = 0;
        for (ListNode temp = headA; temp != null; temp = temp.next, aLength++) {}
        for (ListNode temp = headB; temp != null; temp = temp.next, bLength++) {}

        // 调整较长链表的长度
        ListNode longOne = aLength > bLength ? headA : headB;
        int diff = aLength - bLength > 0 ? aLength - bLength : -(aLength - bLength);
        while (diff > 0) {
            longOne = longOne.next;
            diff--;
        }

        ListNode temp = aLength > bLength ? headB : headA; // 较短的链表
        while (longOne != temp) {
            longOne = longOne.next;
            temp = temp.next;
        }

        return longOne; // 任意返回longOne或者temp为答案
    }
}
