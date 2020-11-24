package com.samin.coding.Q10.simple;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        Server.simpleServerRun();
        Server.serverRun();
    }

    // 只读服务器
    public static void simpleServerRun() throws Exception {
        ServerSocket ss = new ServerSocket(8081);

        // establishes connection
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String str = dis.readUTF();
        System.out.println("message= " + str);
        ss.close();
    }

    // 简单双工聊天，开启后服务端等待客户端
    public static void serverRun() throws Exception {
        ServerSocket ss = new ServerSocket(8081);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = din.readUTF();
            System.out.println("client says: " + str);
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }
}
