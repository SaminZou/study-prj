package algorithms.base.util;

/**
 * 快速排序
 *
 * @author samin
 * @date 2021-01-03
 */
public class QuickSort {

    // copy to commit answer
    public static void quickSort(int[] arr, int left, int right) {
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

//    private void quickSort(int[] arr, int left, int right) {
//        if (left < right) {
//            int i = left;
//            int j = right;
//            int index = arr[left];
//            int tmp;
//
//            while (i < j) {
//                while (arr[j] >= index && i < j) {
//                    j--;
//                }
//
//                while (arr[i] <= index && i < j) {
//                    i++;
//                }
//
//                if (i < j) {
//                    tmp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = tmp;
//                }
//            }
//
//            arr[left] = arr[i];
//            arr[i] = index;
//            quickSort(arr, left, i - 1);
//            quickSort(arr, i + 1, right);
//        }
//    }
}
