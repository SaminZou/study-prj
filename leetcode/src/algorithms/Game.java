package algorithms;

/**
 * 猜数字
 *
 * @author samin
 * @date 2021-01-11
 */
public class Game {

    public static void main(String[] args) {
        System.out.println(new Game().game(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        System.out.println(new Game().game(new int[]{2, 2, 3}, new int[]{3, 2, 1}));
    }

    public int game(int[] guess, int[] answer) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            if (guess[i] == answer[i]) {
                result = result + 1;
            }
        }
        return result;
    }
}
