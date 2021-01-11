package algorithms;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 * @author samin
 * @date 2021-01-11
 */
public class Exchange {

    /*
    解法1
     */
    //    public int[] exchange(int[] nums) {
    //        List<Integer> oddList = new ArrayList<>();
    //        List<Integer> evenList = new ArrayList<>();
    //
    //        for (int ele : nums) {
    //            if (ele % 2 == 0) {
    //                evenList.add(ele);
    //            } else {
    //                oddList.add(ele);
    //            }
    //        }
    //
    //        oddList.addAll(evenList);
    //
    //        return oddList.stream().mapToInt(e -> e).toArray();
    //    }

    public static void main(String[] args) {
        int[] result = new Exchange().exchange(new int[] {1});
        for (int ele : result) {
            System.out.print(ele + " ");
        }
    }

    /*
    双指针解法
     */
    public int[] exchange(int[] nums) {
        int index = 0;
        int endIndex = nums.length - 1;

        while (index < endIndex) {
            if (nums[index] % 2 == 0 && nums[endIndex] % 2 != 0) {
                int temp = nums[index];
                nums[index] = nums[endIndex];
                nums[endIndex] = temp;
            }

            if (nums[index] % 2 != 0) {
                index++;
            }

            if (nums[endIndex] % 2 == 0) {
                endIndex--;
            }
        }

        return nums;
    }
}
