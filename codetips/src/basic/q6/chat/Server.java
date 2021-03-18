package basic.q6.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 *
 * @author samin
 * @date 2021-01-10
 */
public class Server {

    public static void main(String[] args) throws Exception {
        Server.serverRun();
    }

    /** 简单双工聊天，开启后服务端等待客户端 */
    public static void serverRun() throws IOException {
        ServerSocket ss = new ServerSocket(8081);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        while (!"stop".equals(str)) {
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
