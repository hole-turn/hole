package com.xlh.redis.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author: xielinhao
 * @title: RedisServerNIO
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 13:12 2022/11/21
 */
public class RedisServerNIO {
    static ArrayList<SocketChannel> socketList = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        System.out.println("---------RedisServerNIO 启动等待中......");
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 6379));
        serverSocket.configureBlocking(false);//设置为非阻塞模式

        while (true) {
            for (SocketChannel element : socketList) {
                int read = element.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    System.out.println("-----读取数据: " + new String(bytes));
                    byteBuffer.clear();
                }
            }

            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("-----成功连接: ");
                socketChannel.configureBlocking(false);//设置为非阻塞模式
                socketList.add(socketChannel);
                System.out.println("-----socketList size: " + socketList.size());
            }
        }
    }

}
