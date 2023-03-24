package algorithms;

import algorithms.base.model.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表
 *
 * @author samin
 * @date 2021-01-11
 */
public class ReorderList {

    public static void main(String[] args) {
        // 1 4 2 3
        ListNode l1 = ListNode.listNodeGenerator(1, 2, 3, 4);
        new ReorderList().reorderList(l1);
        ListNode.listNodePrinter(l1);

        // 1 5 2 4 3
        ListNode l2 = ListNode.listNodeGenerator(1, 2, 3, 4, 5);
        new ReorderList().reorderList(l2);
        ListNode.listNodePrinter(l2);

        //
        ListNode l3 = ListNode.listNodeGenerator();
        new ReorderList().reorderList(l3);
        ListNode.listNodePrinter(l3);
    }

    public void reorderList(ListNode head) {
        ListNode tmp = head;

        // 获取所有的值
        List<Integer> vals = new ArrayList<>();

        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }

        // 重新排序
        head = tmp;
        int left = 0;
        int right = vals.size() - 1;
        for (int i = 0; i < vals.size(); i++) {
            if (i % 2 == 0) {
                head.val = vals.get(left++);
            } else {
                head.val = vals.get(right--);
            }
            head = head.next;
        }
    }
}
