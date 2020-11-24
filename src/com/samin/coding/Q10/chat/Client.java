package com.samin.coding.Q10.simple;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Client.simpleClientRun();
        Client.clientRun();
    }

    // 发送一条 tcp 信息
    public static void simpleClientRun() throws Exception {
        Socket s = new Socket("127.0.0.1", 8081);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF("Hello Server");
        dout.flush();
        dout.close();
        s.close();
    }

    // 简单双工聊天，开启后客户端先发送信息
    public static void clientRun() throws Exception {
        Socket s = new Socket("127.0.0.1", 8081);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2 = din.readUTF();
            System.out.println("Server says: " + str2);
        }

        dout.close();
        s.close();
    }
}
