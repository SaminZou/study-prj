package basic.q6.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端
 *
 * @author samin
 * @date 2021-01-10
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Client.clientRun();
    }

    // 简单双工聊天，开启后客户端先发送信息
    public static void clientRun() throws Exception {
        Socket s = new Socket("127.0.0.1", 8081);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        while (!"stop".equals(str)) {
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
