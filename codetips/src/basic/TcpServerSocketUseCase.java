package basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TCP Server Socket Use Case - NIO-based non-blocking server
 * 
 * This class demonstrates how to use Java NIO to implement a high-performance TCP server, supporting:
 * - Non-blocking I/O operations
 * - Multi-client concurrent processing
 * - Heartbeat detection mechanism
 * - Graceful shutdown
 * - Error handling
 * 
 * @author samin
 * @date 2021-01-10
 * @version 2.0
 */
public class TcpServerSocketUseCase {
    
    private static final Logger logger = Logger.getLogger(TcpServerSocketUseCase.class.getName());
    
    // Server configuration constants
    private static final int DEFAULT_PORT = 8080;
    private static final int BUFFER_SIZE = 1024;
    private static final long HEARTBEAT_INTERVAL = 30000; // 30 seconds heartbeat interval
    private static final String HEARTBEAT_MESSAGE = "HEARTBEAT";
    
    // Server state control
    private final AtomicBoolean serverRunning = new AtomicBoolean(true);
    private Selector selector;
    private ServerSocketChannel serverChannel;
    
    /**
     * Start TCP server
     * @param port Listening port
     * @throws IOException If server startup fails
     */
    public void startServer(int port) throws IOException {
        initializeServer(port);
        logger.info("Server started, listening on port: " + port);
        
        // Main event loop
        eventLoop();
    }
    
    /**
     * 初始化服务器组件
     */
    private void initializeServer(int port) throws IOException {
        // Create selector
        selector = Selector.open();
        
        // Create server channel and configure
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        
        // Register with selector, listen for connection events
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    
    /**
     * Main event processing loop
     */
    private void eventLoop() {
        while (serverRunning.get()) {
            try {
                // Wait for ready channels, timeout after 1 second
                int readyChannels = selector.select(1000);
                
                if (readyChannels == 0) {
                    // No ready channels, check if need to stop
                    if (!serverRunning.get()) {
                        break;
                    }
                    continue;
                }
                
                // Process ready channels
                processReadyChannels();
                
            } catch (IOException e) {
                logger.log(Level.SEVERE, "事件循环处理异常", e);
                break;
            }
        }
        
        // 清理资源
        cleanup();
    }
    
    /**
     * Process ready channels
     */
    private void processReadyChannels() throws IOException {
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            
            if (!key.isValid()) {
                continue;
            }
            
            try {
                if (key.isAcceptable()) {
                    handleAccept(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                } else if (key.isWritable()) {
                    handleWrite(key);
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "处理通道事件异常", e);
                closeChannel(key);
            }
        }
    }
    
    /**
     * Handle new client connection
     */
    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverSocketChannel.accept();
        
        if (clientChannel != null) {
            clientChannel.configureBlocking(false);
            // Register read and write events
            clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            
            String clientAddress = clientChannel.getRemoteAddress().toString();
            logger.info("New client connected: " + clientAddress);
            
            // Send welcome message
            sendWelcomeMessage(clientChannel);
        }
    }
    
    /**
     * Handle read data
     */
    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        
        int bytesRead = channel.read(buffer);
        
        if (bytesRead > 0) {
            // Process received data
            processReceivedData(channel, buffer, bytesRead);
        } else if (bytesRead == -1) {
            // Client closed connection
            String clientAddress = channel.getRemoteAddress().toString();
            logger.info("Client closed connection: " + clientAddress);
            closeChannel(key);
        }
    }
    
    /**
     * Handle write data (heartbeat detection)
     */
    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        
        // Check if need to send heartbeat
        if (System.currentTimeMillis() % HEARTBEAT_INTERVAL < 1000) {
            sendHeartbeat(channel);
        }
    }
    
    /**
     * Process received data
     */
    private void processReceivedData(SocketChannel channel, ByteBuffer buffer, int bytesRead) throws IOException {
        buffer.flip();
        byte[] data = new byte[bytesRead];
        buffer.get(data);
        
        String message = new String(data, StandardCharsets.UTF_8).trim();
        String clientAddress = channel.getRemoteAddress().toString();
        
        logger.info("Received message from " + clientAddress + ": " + message);
        
        // Handle different types of messages
        handleMessage(channel, message);
        
        buffer.clear();
    }
    
    /**
     * Handle different types of messages
     */
    private void handleMessage(SocketChannel channel, String message) throws IOException {
        if (message.equalsIgnoreCase("quit") || message.equalsIgnoreCase("exit")) {
            sendResponse(channel, "Goodbye! Connection closed.");
            channel.close();
        } else if (message.equalsIgnoreCase("time")) {
            sendResponse(channel, "当前时间: " + new java.util.Date());
        } else if (message.equalsIgnoreCase("echo")) {
            sendResponse(channel, "ECHO: " + message);
        } else if (message.startsWith("echo ")) {
            String echoText = message.substring(5);
            sendResponse(channel, "ECHO: " + echoText);
        } else {
            sendResponse(channel, "Server received: " + message);
        }
    }
    
    /**
     * Send welcome message
     */
    private void sendWelcomeMessage(SocketChannel channel) throws IOException {
        String welcome = "Welcome to TCP Server!\n" +
                        "Available commands:\n" +
                        "- time: Get server time\n" +
                        "- echo [text]: Echo text\n" +
                        "- quit/exit: Disconnect\n" +
                        "- other: Normal message processing";
        sendResponse(channel, welcome);
    }
    
    /**
     * Send response message
     */
    private void sendResponse(SocketChannel channel, String response) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap((response + "\n").getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
    }
    
    /**
     * Send heartbeat message
     */
    private void sendHeartbeat(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap((HEARTBEAT_MESSAGE + "\n").getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
        logger.fine("Sent heartbeat to client: " + channel.getRemoteAddress());
    }
    
    /**
     * Close channel and cleanup resources
     */
    private void closeChannel(SelectionKey key) {
        try {
            if (key != null) {
                key.cancel();
                if (key.channel() != null) {
                    key.channel().close();
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error closing channel", e);
        }
    }
    
    /**
     * Cleanup server resources
     */
    private void cleanup() {
        try {
            if (selector != null) {
                selector.close();
            }
            if (serverChannel != null) {
                serverChannel.close();
            }
            logger.info("Server resources cleaned up");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error during cleanup", e);
        }
    }
    
    /**
     * Gracefully stop server
     */
    public void stopServer() {
        serverRunning.set(false);
        logger.info("Stopping server...");
        
        // Wake up selector to exit event loop immediately
        if (selector != null) {
            selector.wakeup();
        }
    }
    
    // ========== 常见用例演示 ==========
    
    /**
     * Use case 1: Simple server startup
     */
    public static void demoSimpleServer() {
        try {
            TcpServerSocketUseCase server = new TcpServerSocketUseCase();
            server.startServer(DEFAULT_PORT);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server startup failed", e);
        }
    }
    
    /**
     * Use case 2: Server with graceful shutdown
     */
    public static void demoServerWithShutdownHook() {
        TcpServerSocketUseCase server = new TcpServerSocketUseCase();
        
        // 添加关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Received shutdown signal, gracefully shutting down server...");
            server.stopServer();
        }));
        
        try {
            server.startServer(DEFAULT_PORT);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server startup failed", e);
        }
    }
    
    /**
     * Use case 3: Custom port server
     */
    public static void demoCustomPortServer(int port) {
        try {
            TcpServerSocketUseCase server = new TcpServerSocketUseCase();
            logger.info("Starting custom port server: " + port);
            server.startServer(port);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Custom port server startup failed", e);
        }
    }
    
    /**
     * Main method - demonstrate different use cases
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            // Use command line argument to specify port
            try {
                int port = Integer.parseInt(args[0]);
                demoCustomPortServer(port);
            } catch (NumberFormatException e) {
                logger.severe("Invalid port number: " + args[0]);
                System.out.println("Usage: java TcpServerSocketUseCase [port]");
            }
        } else {
            // 默认演示用例
            System.out.println("=== TCP Server Use Case Demo ===");
            System.out.println("1. Simple server startup");
            System.out.println("2. Server with graceful shutdown");
            System.out.println("3. Custom port server");
            System.out.print("Select use case (1-3): ");
            
            try {
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        demoSimpleServer();
                        break;
                    case 2:
                        demoServerWithShutdownHook();
                        break;
                    case 3:
                        System.out.print("Enter port number: ");
                        int port = scanner.nextInt();
                        demoCustomPortServer(port);
                        break;
                    default:
                        System.out.println("Invalid selection, using default case 1");
                        demoSimpleServer();
                }
            } catch (Exception e) {
                logger.severe("Input error: " + e.getMessage());
                demoSimpleServer();
            }
        }
    }
}