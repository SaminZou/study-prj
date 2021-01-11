package algorithms;

/**
 * 打印从1到最大的n位数
 *
 * @author samin
 * @date 2021-01-11
 */
public class PrintNumbers {

    public static void main(String[] args) {
        int[] result = new PrintNumbers().printNumbers(2);
        System.out.println(result.length);
    }

    public int[] printNumbers(int n) {
        // 特殊情况
        if (n == 0) {
            return new int[] {};
        }

        // 计算最大数
        int max = (int) Math.pow(10, n) - 1;
        int[] result = new int[max];
        // 遍历加载数据
        for (int i = 0; i < max; i++) {
            result[i] = i + 1;
        }

        return result;
    }
}
