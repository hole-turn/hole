package com.xlh.redis.io.bio.accept;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: xielinhao
 * @title: RedisServer
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 11:12 2022/11/21
 */
public class RedisServer {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024];
        ServerSocket serverSocket=new ServerSocket(6379);

        while (true) {
            System.out.println("-----111 等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("-----222 成功连接");
        }

    }
}
