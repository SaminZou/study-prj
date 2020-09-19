package com.samin.coding.Q16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeyboardInput {
    public static void main(String[] args) throws IOException {
        // 方法1
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();

        // 方法2
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        String s2 = brInput.readLine();
    }
}
