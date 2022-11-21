package com.xlh.redis.io.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: RedisClient02
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 13:15 2022/11/21
 */
public class RedisClient02 {
    public static void main(String[] args) throws IOException
    {
        System.out.println("------RedisClient01 start");
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
