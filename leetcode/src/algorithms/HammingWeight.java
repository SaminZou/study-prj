package algorithms;

/**
 * 二进制中1的个数 / 位1的个数
 * <p>使用此算法实现的数据校验方式叫海明码</p>
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
            // 如 13 的二进制为 1101，每一次计算都是和 1 进行，不够的位数用 0 补齐，所以结果为 0 或 1，结合 & 的特性可以得出 1 的最终个数
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
