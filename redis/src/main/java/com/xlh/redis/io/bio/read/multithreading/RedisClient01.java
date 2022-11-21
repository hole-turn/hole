package com.xlh.redis.io.bio.read.multithreading;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: RedisClient01
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 12:57 2022/11/21
 */
public class RedisClient01 {
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();

        //socket.getOutputStream().write("RedisClient01".getBytes());

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
