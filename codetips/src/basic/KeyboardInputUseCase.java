package basic;

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
public class KeyboardInputUseCase {

    public static void main(String[] args) throws IOException {
        // 方法1
        method1();

        // 方法2
        method2();

        // 交互式
        interactive();
    }

    private static void method1() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();
        System.out.println("1: " + s);
    }

    private static void method2() throws IOException {
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        String s2 = brInput.readLine();
        System.out.println("2: " + s2);
    }

    private static void interactive() {
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
