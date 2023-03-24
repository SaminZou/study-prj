package algorithms;

import algorithms.base.model.ListNode;
import java.util.Arrays;
import java.util.Objects;

/**
 * 从尾到头打印链表
 *
 * <p> https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 *
 * @author samin
 * @date 2021-01-11
 */
public class ReversePrint {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ReversePrint().reversePrint(ListNode.listNodeGenerator(1, 3, 2))));
    }

    public int[] reversePrint(ListNode head) {
        if (Objects.isNull(head)) {
            return new int[]{};
        }

        int[] temp = new int[10000];
        int index = 0;

        while (Objects.nonNull(head)) {
            temp[index] = head.val;
            head = head.next;
            index += 1;
        }

        int[] result = new int[index];
        int size = index;
        index -= 1;
        for (int i = 0; i < size; i++) {
            result[i] = temp[index];
            index -= 1;
        }

        return result;
    }
}
