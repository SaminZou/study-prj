package algorithms;

public class FindTheDistanceValue {

    public static void main(String[] args) {
        //        int[] arr1 = new int[]{4, 5, 8};
        //        int[] arr2 = new int[]{10, 9, 1, 8};
        //        int d = 2;

        //        int[] arr1 = new int[]{1, 4, 2, 3};
        //        int[] arr2 = new int[]{-4, -3, 6, 10, 20, 30};
        //        int d = 3;

        int[] arr1 = new int[] {2, 1, 100, 3};
        int[] arr2 = new int[] {-5, -2, 10, -3, 7};
        int d = 6;
        System.out.println(new FindTheDistanceValue().findTheDistanceValue(arr1, arr2, d));
    }

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int nums = arr1.length;

        for (int ele1 : arr1) {
            for (int ele2 : arr2) {
                if (Math.abs(ele1 - ele2) <= d) {
                    nums -= 1;
                    break;
                }
            }
        }

        return nums;
    }
}
