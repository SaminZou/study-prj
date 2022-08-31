package algorithms;

import algorithms.base.model.ListNode;
import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 *
 * <p>给定一个链表，判断链表中是否有环。
 *
 * @author samin
 * @date 2021-01-11
 */
public class HasCycle {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        System.out.println(new HasCycle().hasCycle(l1));

        //        ListNode l1 = new ListNode(1);
        //        ListNode l2 = new ListNode(2);
        //        l1.next = l2;
        //        l2.next = l1;
        //        System.out.println(new HasCycle().hasCycle(l1));

        //        ListNode l1 = new ListNode(3);
        //        ListNode l2 = new ListNode(2);
        //        ListNode l3 = new ListNode(0);
        //        ListNode l4 = new ListNode(-4);
        //        l1.next = l2;
        //        l2.next = l3;
        //        l3.next = l4;
        //        l4.next = l2;
        //        System.out.println(new HasCycle().hasCycle(l1));

        //        System.out.println(new HasCycle().hasCycle(null));
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }

        // 存放遍历过的元素
        Set<Integer> cycle = new HashSet<>();

        // 从头开始遍历
        while (head.next != null) {
            // 是否出现回环
            if (cycle.contains(System.identityHashCode(head.next))) {
                return true;
            }

            cycle.add(System.identityHashCode(head));
            head = head.next;
        }

        return false;
    }

    // 优化
    public boolean hasCycle(ListNode head) {

        Set<ListNode> cycle = new HashSet<>();

        // 从头开始遍历
        while (head != null) {
            if (!cycle.add(head)) {
                return true;
            }

            head = head.next;
        }

        return false;
    }
}
