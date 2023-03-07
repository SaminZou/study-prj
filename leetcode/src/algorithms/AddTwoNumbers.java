package algorithms;

import algorithms.base.model.ListNode;

/**
 * 两数相加
 *
 * <p> 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
 *
 * @author samin
 * @date 2021-01-11
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNode = new AddTwoNumbers().addTwoNumbers(ListNode.listNodeGenerator(new int[]{2, 4, 3}),
                ListNode.listNodeGenerator(new int[]{5, 6, 4}));

        ListNode.listNodePrinter(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        // 有数字或进位则需要继续相加
        while (l1 != null || l2 != null || carry != 0) {
            // 从最低位开始相加
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            // 链表的专属处理方式，倒腾一下
            cursor.next = sumNode;
            cursor = sumNode;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return root.next;
    }
}
