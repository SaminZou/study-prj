package algorithms;

/**
 * 第 k 个缺失的正整数
 *
 * @author samin
 * @date 2021-01-27
 */
public class FindKthPositive {

    public int findKthPositive(int[] arr, int k) {
        // 从 1 开头排查
        int index = 1;
        // 记录缺失数字数量
        int positive = 0;
        // 当前指针
        int cur = 0;

        while (positive < k) {
            // 非必要条件
            if (cur < arr.length && arr[cur] == index) {
                cur += 1;
            } else {
                positive += 1;

                // 直到找到缺失整数
                if (positive == k) {
                    return index;
                }
            }

            index += 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        // 9
        System.out.println(new FindKthPositive().findKthPositive(new int[] {2, 3, 4, 7, 11}, 5));

        // 6
        System.out.println(new FindKthPositive().findKthPositive(new int[] {1, 2, 3, 4}, 2));
    }
}
