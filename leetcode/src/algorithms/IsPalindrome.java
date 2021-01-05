package algorithms;

public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome2(32123));
    }

    /*
    全转
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        int paramX = x;
        int temp = 0;
        while (x > 0) {
            temp = temp * 10 + (x % 10);
            x = x / 10;
        }

        return temp == paramX;
    }

    /*
    优化，半转即可
     */
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        int temp = 0;
        while (x > temp) {
            temp = temp * 10 + (x % 10);
            x = x / 10;
        }

        // 偶数和奇数（去掉中间数字判断）
        return temp == x || temp / 10 == x;
    }

    /*
    自己的解法
     */
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }

        String[] intStrs = String.valueOf(x).split("");

        int leftIndex;
        int rightIndex;
        if (intStrs.length % 2 == 1) { // 奇数位
            rightIndex = (intStrs.length - 1) / 2 + 1;
            leftIndex = rightIndex - 2;
        } else { // 偶数位
            rightIndex = (intStrs.length) / 2;
            leftIndex = rightIndex - 1;
        }

        int growNum = 0;
        for (int i = leftIndex; i >= 0; i--) {
            if (!intStrs[i].equals(intStrs[rightIndex + growNum])) {
                return false;
            }
            growNum += 1;
        }

        return true;
    }
}
