package algorithms;

/**
 * 对面的人
 * <p>
 * Description: 想象 0 到 n-1 个人围成一圈，每个人中的距离相等，试求出第 number 号对面的人是几号
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-26
 */
public class CirclePeople {

    public static void main(String[] args) {
        // 7
        System.out.println(new CirclePeople().solution(10, 2));
    }

    public int solution(int n, int number) {
        // 超出范围
        if (number < 0 || number > n - 1) {
            return -1;
        }

        return (number + (n + 1) / 2) % n;
    }
}