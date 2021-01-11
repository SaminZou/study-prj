package algorithms;

import algorithms.base.model.ListNode;

/**
 * 删除链表中的节点
 *
 * @author samin
 * @date 2021-01-11
 */
public class DeleteNode {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(-3);
        ListNode n2 = new ListNode(5);
        n1.next = n2;
        ListNode n3 = new ListNode(-99);
        n2.next = n3;
        ListNode result = new DeleteNode().deleteNode(n1, -99);
        System.out.println();
        //        ListNode n1 = new ListNode(4);
        //        ListNode n2 = new ListNode(5);
        //        n1.next = n2;
        //        ListNode n3 = new ListNode(1);
        //        n2.next = n3;
        //        ListNode n4 = new ListNode(9);
        //        n3.next = n4;
        //        ListNode result = new DeleteNode().deleteNode(n1, 1);
        //        System.out.println();
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur = head;

        while (head != null) {
            // 最后一个节点的处理
            if (head.next.val == val && head.next.next == null) {
                head.next = null;
                head = null;
            } else if (head.val == val) {
                head.val = head.next.val;
                head.next = head.next.next;
                break;
            } else {
                head = head.next;
            }
        }

        return cur;
    }

    public void deleteNode2(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
