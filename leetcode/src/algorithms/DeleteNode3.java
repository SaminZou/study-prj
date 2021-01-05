package algorithms;

import algorithms.base.model.ListNode;

public class DeleteNode3 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(5);
        l1.next = l2;
        ListNode l3 = new ListNode(1);
        l2.next = l3;
        ListNode l4 = new ListNode(9);
        l3.next = l4;
        //        ListNode l5 = new ListNode(10);
        //        l4.next = l5;
        new DeleteNode3().deleteNode(l1);
        System.out.println();
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
