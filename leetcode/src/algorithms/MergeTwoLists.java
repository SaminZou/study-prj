package algorithms;

import algorithms.base.model.ListNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 合并两个排序的链表
 *
 * @author samin
 * @date 2021-01-11
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();

        // 加入到列表中
        while (l1 != null) {
            list.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            list.add(l2.val);
            l2 = l2.next;
        }

        // 排序
        list.sort(Comparator.naturalOrder());

        // 生成链表
        ListNode head = null;
        ListNode current = null;
        for (Integer ele : list) {
            if (head == null) {
                head = new ListNode(ele, null);
                current = head;
            } else {
                current.next = new ListNode(ele);
                current = current.next;
            }
        }

        return head;
    }
}
