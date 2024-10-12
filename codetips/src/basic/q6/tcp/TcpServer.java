package basic.q6.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TcpServer {

    public void startServer(int port) throws IOException {
        // 创建一个 Selector
        Selector selector = Selector.open();
        // 打开一个 ServerSocketChannel，并绑定到特定端口
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket()
                .bind(new InetSocketAddress(port));
        // 设置为非阻塞模式
        serverChannel.configureBlocking(false);
        // 注册到选择器上，并指定接收 accept 事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("启动服务器，监听端口: " + port);

        while (!Thread.currentThread()
                .isInterrupted()) {
            int select = selector.select();
            if (select == 0) {
                // 没有就绪通道
                continue;
            }

            // 获取所有已就绪的注册通道
            Iterator<SelectionKey> iterator = selector.selectedKeys()
                    .iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    if (key.isAcceptable()) {
                        // 如果是 accept 事件，则处理新的连接请求
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        // 注册到选择器上，并指定接收读事件
                        sc.register(selector, SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        // 如果是 read 事件，则读取数据
                        readData(key);
                    }

                    if (key.isValid() && key.isWritable()) {
                        // 如果是 write 事件，则发送心跳消息
                        sendHeartbeat(key);
                    }
                } catch (IOException e) {
                    if (key != null) {
                        key.cancel();
                    }
                }
            }
        }
    }

    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        // 假设最大接收1KB的数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int numRead;

        while ((numRead = channel.read(buffer)) > 0) {
            // 准备读取数据
            buffer.flip();
            byte[] data = new byte[numRead];
            // 从buffer中获取数据
            buffer.get(data);
            String message = new String(data, StandardCharsets.UTF_8);
            System.out.println("接收到的消息: " + message);
            // 清空buffer以备下次读取
            buffer.clear();
        }

        // 客户端关闭了连接
        if (numRead == -1) {
            System.out.println("客户端关闭了连接.");
            channel.close();
            key.cancel();
        }
    }

    private void sendHeartbeat(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        // 心跳消息
        ByteBuffer buffer = ByteBuffer.wrap("1".getBytes(StandardCharsets.UTF_8));
        // 向客户端写入数据
        channel.write(buffer);
        System.out.println("发送心跳消息.");
    }
}