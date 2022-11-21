package com.xlh.redis.io.bio.read.multithreading;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

/**
 * @author: xielinhao
 * @title: RedisServerBIOMultiThread
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 12:54 2022/11/21
 */
public class RedisServerBIOMultiThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);

        while (true) {
            //System.out.println("-----111 等待连接");
            Socket socket = serverSocket.accept();//阻塞1 ,等待客户端连接
            //System.out.println("-----222 成功连接");

            CompletableFuture.runAsync(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    InputStream inputStream = socket.getInputStream();
                    int length = -1;
                    byte[] bytes = new byte[1024];
                    System.out.println("-----333 等待读取");
                    while ((length = inputStream.read(bytes)) != -1)//阻塞2 ,等待客户端发送数据
                    {
                        System.out.println("-----444 成功读取" + new String(bytes, 0, length));
                        System.out.println("====================");
                        System.out.println();
                    }
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println(Thread.currentThread().getName());

        }
    }
}
