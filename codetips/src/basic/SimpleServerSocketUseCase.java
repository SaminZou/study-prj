package basic;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 *
 * @author samin
 * @date 2021-01-10
 */
public class SimpleServerSocketUseCase {

    public static void main(String[] args) throws Exception {
        SimpleServerSocketUseCase.simpleServerRun();
    }

    public static void simpleServerRun() throws Exception {
        // 只读服务器
        ServerSocket ss = new ServerSocket(8081);

        // establishes connection
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String str = dis.readUTF();
        System.out.println("message= " + str);
        ss.close();
    }
}
