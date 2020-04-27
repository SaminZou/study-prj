package com.samin.Q1;

import java.io.*;

public class ChineseReader {
    public static void main(String[] args) throws Exception {
        String uri = "/home/prj/study-prj"; // 配置好文件目录前缀
        File file = new File(uri + "/src/com/samin/Q1/chinese.txt");

        InputStream inputStream = new FileInputStream(file);
        // 一个UTF-8占3个字节，以下代码段可以输出完整循环输出结果
        int length = 3;
        byte[] b = new byte[length];
        while (inputStream.read(b, 0, length) != -1) {
            System.out.println(new String(b, 0, length));
        }

        InputStream inputStream2 = new FileInputStream(file);
        // 未完全读取的情况，乱码
        int length2 = 2;
        byte[] b2 = new byte[length2];
        while (inputStream2.read(b2, 0, length2) != -1) {
            System.out.println(new String(b2, 0, length2));
        }

        // 解决方案，使用字符流读取
        InputStream inputStream3 = new FileInputStream(file);
        InputStreamReader io = new InputStreamReader(inputStream3);
        int ch;
        while ((ch = io.read()) != -1) {
            System.out.println((char) ch);
        }

        // 一行一行读出
        // BufferReader和InputStreamReader的区别在于是一行行读出
        InputStream inputStream4 = new FileInputStream(file);
        InputStreamReader io2 = new InputStreamReader(inputStream4);
        BufferedReader br = new BufferedReader(io2);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }
}
