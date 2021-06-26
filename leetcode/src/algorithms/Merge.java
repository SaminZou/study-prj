package algorithms;

/**
 * 合并两个有序数组
 *
 * @author samin
 * @date 2021-06-26
 */
public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0 && n == 0) {
            return;
        }

        if (n >= 0) {
            System.arraycopy(nums2, 0, nums1, m, n);
        }

        quickSort(nums1, 0, m + n - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = arr[left];
            int tmp;

            while (i < j) {
                while (arr[j] >= index && i < j) {
                    j--;
                }

                while (arr[i] <= index && i < j) {
                    i++;
                }

                if (i < j) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }

            arr[left] = arr[i];
            arr[i] = index;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}
