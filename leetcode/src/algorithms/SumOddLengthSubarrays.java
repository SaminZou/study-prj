package algorithms;

public class SumOddLengthSubarrays {

    int res = 0;

    public static void main(String[] args) {
        // 58
        System.out.println(
                new SumOddLengthSubarrays().sumOddLengthSubarrays(new int[] {1, 4, 2, 5, 3}));
        // 3
        System.out.println(new SumOddLengthSubarrays().sumOddLengthSubarrays(new int[] {1, 2}));
        // 66
        System.out.println(
                new SumOddLengthSubarrays().sumOddLengthSubarrays(new int[] {10, 11, 12}));
        // 12
        System.out.println(new SumOddLengthSubarrays().sumOddLengthSubarrays(new int[] {1, 2, 3}));
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int index = 1;

        while (index <= arr.length) {
            for (int i = 0; arr.length - i >= index; i++) {
                for (int j = 0; j < index; j++) {
                    res = res + arr[i + j];
                }
            }

            index += 2;
        }

        return res;
    }
}
