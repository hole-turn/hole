package com.xlh.redis.io.bio.accept;

import java.io.IOException;
import java.net.Socket;

/**
 * @author: xielinhao
 * @title: RedisClient1
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 11:13 2022/11/21
 */
public class RedisClient2 {
    public static void main(String[] args) throws IOException {
        System.out.println("------RedisClient02 start");
        Socket socket = new Socket("127.0.0.1", 6379);
    }
}
