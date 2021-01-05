package algorithms;

public class FindMagicIndex {

    public static void main(String[] args) {
        System.out.println(new FindMagicIndex().findMagicIndex(new int[] {0, 2, 3, 4, 5}));
        System.out.println(new FindMagicIndex().findMagicIndex(new int[] {1, 1, 1}));
        System.out.println(new FindMagicIndex().findMagicIndex(new int[] {}));
        System.out.println(new FindMagicIndex().findMagicIndex(new int[] {6, 5, 4, 1, 2, 1}));
    }

    public int findMagicIndex(int[] nums) {
        int res = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                res = i;
                break;
            }
        }

        return res;
    }
}
