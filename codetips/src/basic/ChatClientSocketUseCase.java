package basic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * 客户端
 *
 * <p>注意 ❗❗❗
 *
 * <p>Client 和 Server 跑起来以后，需要 Client 先发信息，然后 Server 给 Client 发送
 *
 * <p>直到 Client 先发送 "stop" ，Server 再随意回复一句话，即结束对话
 *
 * @author samin
 * @date 2021-01-10
 */
public class ChatClientSocketUseCase {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8081;
    private static final int SOCKET_TIMEOUT = 30000; // 30 seconds
    private static final String STOP_COMMAND = "stop";

    public static void main(String[] args) {
        try {
            clientRun();
        } catch (Exception e) {
            System.err.println("客户端运行出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 简单双工聊天，开启后客户端先发送信息
     */
    public static void clientRun() throws IOException {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             DataInputStream din = new DataInputStream(socket.getInputStream());
             DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            
            socket.setSoTimeout(SOCKET_TIMEOUT);
            System.out.println("已连接到服务器 " + SERVER_HOST + ":" + SERVER_PORT);
            System.out.println("输入消息开始聊天，输入 '" + STOP_COMMAND + "' 结束对话");

            String userInput;
            String serverResponse;

            while (true) {
                System.out.print("Client: ");
                userInput = br.readLine();

                if (userInput == null || userInput.trim().isEmpty()) {
                    System.out.println("输入不能为空，请重新输入");
                    continue;
                }

                if (STOP_COMMAND.equalsIgnoreCase(userInput.trim())) {
                    dout.writeUTF(userInput);
                    dout.flush();
                    System.out.println("正在结束对话...");
                    break;
                }

                try {
                    dout.writeUTF(userInput);
                    dout.flush();
                    
                    serverResponse = din.readUTF();
                    System.out.println("Server says: " + serverResponse);
                } catch (SocketTimeoutException e) {
                    System.err.println("等待服务器响应超时");
                    break;
                } catch (IOException e) {
                    System.err.println("与服务器通信时出错: " + e.getMessage());
                    break;
                }
            }
        } catch (ConnectException e) {
            System.err.println("无法连接到服务器 " + SERVER_HOST + ":" + SERVER_PORT);
            System.err.println("请确保服务器已启动并正在监听该端口");
        } catch (IOException e) {
            System.err.println("客户端连接错误: " + e.getMessage());
            throw e;
        }
    }
}
