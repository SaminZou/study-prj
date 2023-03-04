package algorithms;

/**
 * 二进制求和
 *
 * @author samin
 * @date 2021-01-29
 */
public class AddBinary {

    public static void main(String[] args) {
        // 100
        System.out.println(new AddBinary().addBinary("11", "1"));
        // 10101
        System.out.println(new AddBinary().addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }
}
