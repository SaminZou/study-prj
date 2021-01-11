package algorithms;

/**
 * IP 地址无效化
 *
 * @author samin
 * @date 2021-01-11
 */
public class DefangIPaddr {

    public static void main(String[] args) {
        System.out.println(new DefangIPaddr().defangIPaddr("1.1.1.1"));
        System.out.println(new DefangIPaddr().defangIPaddr("255.100.50.0"));
    }

    public String defangIPaddr(String address) {
        char[] addressChars = address.toCharArray();
        char[] newChars = new char[address.length() + 6];
        int index = 0;
        for (char ele : addressChars) {
            if (ele == '.') {
                newChars[index++] = '[';
                newChars[index++] = '.';
                newChars[index++] = ']';
            } else {
                newChars[index++] = ele;
            }
        }
        return new String(newChars);
    }
}
