package algorithms;

/**
 * 二进制中1的个数 / 位1的个数
 *
 * @author samin
 * @date 2021-01-11
 */
public class HammingWeight {

    public static void main(String[] args) {
        System.out.println(new HammingWeight().hammingWeight(1));
        System.out.println(new HammingWeight().hammingWeight(13));
        System.out.println(new HammingWeight().hammingWeight(128));

        System.out.println(new HammingWeight().hammingWeight2(13));
        System.out.println(new HammingWeight().hammingWeight2(128));
    }

    public int hammingWeight(int n) {
        int result = 0;

        while (n != 0) {
            result += n & 1;
            n = n >>> 1;
        }

        return result;
    }

    public int hammingWeight2(int n) {
        int nums = 0;

        String binaryStr = Integer.toBinaryString(n);
        for (int i = 0; i < binaryStr.length(); i++) {
            if ('1' == binaryStr.charAt(i)) {
                nums += 1;
            }
        }

        return nums;
    }
}
