package basic.q9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 获取键盘输入
 *
 * @author samin
 * @date 2021-01-10
 */
public class KeyboardInput {

    public static void main(String[] args) throws IOException {
        // 方法1
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();
        System.out.println("1: "+s);

        // 方法2
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        String s2 = brInput.readLine();
        System.out.println("2: "+s2);
    }

    /**
     * 交互式
     */
    // public static void main(String[] args) throws IOException {
    //     while (true) {
    //         Scanner input = new Scanner(System.in);
    //         String s = input.nextLine();
    //         Integer num = Integer.parseInt(s);
    //         System.out.println("现在完成：" + num / 240 + "圈");
    //     }
    // }
}
