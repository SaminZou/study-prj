package algorithms;

/**
 * 罗马数字转整数
 *
 * @author samin
 * @date 2021-06-23
 */
public class RomanToInt {

    public static void main(String[] args) {
        // 3
        System.out.println(new RomanToInt().romanToInt("III"));
        // 4
        System.out.println(new RomanToInt().romanToInt("IV"));
        // 9
        System.out.println(new RomanToInt().romanToInt("IX"));
        // 58
        System.out.println(new RomanToInt().romanToInt("LVIII"));
        // 1994
        System.out.println(new RomanToInt().romanToInt("MCMXCIV"));
        // 49
        System.out.println(new RomanToInt().romanToInt("XLIX"));
        // 999
        System.out.println(new RomanToInt().romanToInt("CMXCIX "));
    }

    public int romanToInt(String s) {
        if (s.length() == 1) {
            return parseWord(s.toCharArray()[0]);
        }

        int sum = 0;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (i + 1 == charArray.length) {
                sum += parseWord(charArray[i]);
                break;
            }

            if (parseTwoWord(charArray[i], charArray[i + 1]) == 0) {
                sum += parseWord(charArray[i]);
            } else {
                sum += parseTwoWord(charArray[i], charArray[i + 1]);
                i += 1;
            }
        }

        return sum;
    }

    public int parseWord(char w) {
        switch (w) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public int parseTwoWord(char w1, char w2) {
        switch (String.valueOf(w1) + w2) {
            case "IV":
                return 4;
            case "IX":
                return 9;
            case "XL":
                return 40;
            case "XC":
                return 90;
            case "CD":
                return 400;
            case "CM":
                return 900;
            default:
                return 0;
        }
    }
}
