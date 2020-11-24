package com.samin.coding.Q10.simple;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        Server.simpleServerRun();
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
}
