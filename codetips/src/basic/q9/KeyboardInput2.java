package basic.q9;

import java.io.IOException;
import java.util.Scanner;

/**
 * 获取键盘输入
 *
 * @author samin
 * @date 2021-01-10
 */
public class KeyboardInput2 {

    /**
     * 交互式
     */
    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();

            if ("exit".equals(s)) {
                return;
            }

            Integer num = Integer.parseInt(s);
            System.out.println("现在完成：" + num / 240 + "圈");
        }
    }
}
