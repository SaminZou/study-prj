package basic.q6.simple;

import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Client.simpleClientRun();
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
}
