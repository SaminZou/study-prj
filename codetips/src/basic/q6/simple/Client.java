package basic.q6.simple;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * 客户端
 *
 * @author samin
 * @date 2021-01-10
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Client.simpleClientRun();
    }

    public static void simpleClientRun() throws Exception {
        // 发送一条 tcp 信息
        Socket s = new Socket("127.0.0.1", 8081);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF("Hello Server");
        dout.flush();
        dout.close();
        s.close();
    }
}
