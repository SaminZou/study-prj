package algorithms;

/**
 * Excel表列名称
 *
 * @author samin
 * @date 2021-06-29
 */
public class ConvertToTitle {

    public static void main(String[] args) {
        // A
        System.out.println(new ConvertToTitle().convertToTitle(1));
        // AB
        System.out.println(new ConvertToTitle().convertToTitle(28));
        // ZY
        System.out.println(new ConvertToTitle().convertToTitle(701));
        // FXSHRXW
        System.out.println(new ConvertToTitle().convertToTitle(2147483647));
        // AZ
        System.out.println(new ConvertToTitle().convertToTitle(52));
    }

    /**
     * 进制
     */
    private static final int BASE_26 = 26;

    /**
     * 根据 ASCII 码值转移
     *
     * @param ch 需要解析的数字
     * @return 对应的字母
     */
    private char parseChar(int ch) {
        return (char) (ch + 64);
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            int cur = columnNumber % BASE_26 == 0 ? 26 : columnNumber % BASE_26;
            sb.append(parseChar(cur));
            columnNumber = (columnNumber - cur) / BASE_26;
        }

        return sb.reverse()
                 .toString();
    }
}
