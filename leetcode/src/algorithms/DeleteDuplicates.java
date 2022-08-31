package algorithms;

import algorithms.base.model.ListNode;
import java.util.TreeSet;

/**
 * 删除排序链表中的重复元素
 *
 * @author samin
 * @date 2021-07-06
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        //        ListNode l1 = new ListNode(1);
        //        ListNode l2 = new ListNode(1);
        //        ListNode l3 = new ListNode(2);
        //        l1.next = l2;
        //        l2.next = l3;
        //        ListNode.listNodePrinter(new DeleteDuplicates().deleteDuplicates(l1));
        //
        //        ListNode ll1 = new ListNode(1);
        //        ListNode ll2 = new ListNode(1);
        //        ListNode ll3 = new ListNode(2);
        //        ListNode ll4 = new ListNode(3);
        //        ListNode ll5 = new ListNode(3);
        //        ll1.next = ll2;
        //        ll2.next = ll3;
        //        ll3.next = ll4;
        //        ll4.next = ll5;
        //        ListNode.listNodePrinter(new DeleteDuplicates().deleteDuplicates(ll1));

        ListNode ln1 = new ListNode(-3);
        ListNode ln2 = new ListNode(-1);
        ListNode ln3 = new ListNode(0);
        ListNode ln4 = new ListNode(0);
        ListNode ln5 = new ListNode(0);
        ListNode ln6 = new ListNode(3);
        ListNode ln7 = new ListNode(3);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ln5.next = ln6;
        ln6.next = ln7;
        ListNode.listNodePrinter(new DeleteDuplicates().deleteDuplicates(ln1));
    }

    /**
     * 官方提交
     *
     * @param head 待处理链表
     * @return 处理完的链表结果
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        TreeSet<Integer> temp = new TreeSet<>();
        while (head != null) {
            temp.add(head.val);
            head = head.next;
        }

        if (temp.size() > 0) {
            ListNode listNode = null;
            ListNode current = null;
            for (Integer ele : temp) {
                if (listNode == null) {
                    listNode = new ListNode(ele, null);
                    current = listNode;
                } else {
                    current.next = new ListNode(ele);
                    current = current.next;
                }
            }

            return listNode;
        }

        return null;
    }
}
