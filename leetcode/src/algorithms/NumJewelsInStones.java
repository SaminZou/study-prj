package algorithms;

/**
 * 宝石与石头
 *
 * <p> https://leetcode-cn.com/problems/jewels-and-stones/
 *
 * @author samin
 * @date 2021-01-11
 */
public class NumJewelsInStones {

    public static void main(String[] args) {
        // 3
        System.out.println(new NumJewelsInStones().numJewelsInStones("aA", "aAAbbbb"));
        // 0
        System.out.println(new NumJewelsInStones().numJewelsInStones("z", "ZZ"));
    }

    public int numJewelsInStones(String jewels, String stones) {
        int result = 0;
        char[] jewelsArr = jewels.toCharArray();

        for (char stone : stones.toCharArray()) {
            for (char jewelsChar : jewelsArr) {
                if (stone == jewelsChar) {
                    result += 1;
                    break;
                }
            }
        }

        return result;
    }
}
