package basic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 服务器端
 *
 * @author samin
 * @date 2021-01-10
 */
public class ChatServerSocketUseCase {

    private static final int SERVER_PORT = 8081;
    private static final int SOCKET_TIMEOUT = 30000; // 30 seconds
    private static final String STOP_COMMAND = "stop";
    private static final int MAX_CLIENTS = 10;
    
    private static final AtomicBoolean serverRunning = new AtomicBoolean(true);

    public static void main(String[] args) {
        try {
            serverRun();
        } catch (Exception e) {
            System.err.println("服务器运行出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 简单双工聊天，开启后服务端等待客户端
     */
    public static void serverRun() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
        
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            serverSocket.setSoTimeout(SOCKET_TIMEOUT);
            System.out.println("服务器已启动，监听端口: " + SERVER_PORT);
            System.out.println("等待客户端连接...");

            while (serverRunning.get()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("新客户端连接: " + clientSocket.getInetAddress().getHostAddress());
                    
                    executorService.submit(() -> handleClient(clientSocket));
                    
                } catch (SocketTimeoutException e) {
                    // Timeout is expected for graceful shutdown check
                    continue;
                } catch (IOException e) {
                    if (serverRunning.get()) {
                        System.err.println("接受客户端连接时出错: " + e.getMessage());
                    }
                    break;
                }
            }
        } finally {
            executorService.shutdown();
            System.out.println("服务器已关闭");
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (Socket socket = clientSocket;
             DataInputStream din = new DataInputStream(socket.getInputStream());
             DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            
            socket.setSoTimeout(SOCKET_TIMEOUT);
            
            String clientMessage;
            String serverResponse;

            while (serverRunning.get()) {
                try {
                    clientMessage = din.readUTF();
                    System.out.println("Client [" + socket.getInetAddress().getHostAddress() + "] says: " + clientMessage);

                    if (STOP_COMMAND.equalsIgnoreCase(clientMessage.trim())) {
                        System.out.println("客户端请求结束对话");
                        serverResponse = "Goodbye! Connection closed.";
                        dout.writeUTF(serverResponse);
                        dout.flush();
                        break;
                    }

                    System.out.print("Server Response: ");
                    serverResponse = br.readLine();
                    
                    if (serverResponse == null || serverResponse.trim().isEmpty()) {
                        serverResponse = "Server received: " + clientMessage;
                    }
                    
                    dout.writeUTF(serverResponse);
                    dout.flush();
                    
                } catch (SocketTimeoutException e) {
                    System.out.println("客户端 [" + socket.getInetAddress().getHostAddress() + "] 连接超时");
                    break;
                } catch (IOException e) {
                    if (serverRunning.get()) {
                        System.err.println("与客户端通信时出错: " + e.getMessage());
                    }
                    break;
                }
            }
            
        } catch (IOException e) {
            System.err.println("处理客户端连接时出错: " + e.getMessage());
        } finally {
            System.out.println("客户端连接已关闭: " + clientSocket.getInetAddress().getHostAddress());
        }
    }

    /**
     * 优雅关闭服务器
     */
    public static void shutdown() {
        serverRunning.set(false);
        System.out.println("正在关闭服务器...");
    }
}
