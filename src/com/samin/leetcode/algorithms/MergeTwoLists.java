package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
