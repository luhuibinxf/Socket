package com.example.socketdemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/4/2 0002.
 */

public class MyServer {

    public static void main(String[] args) throws IOException {
        // 1. 创建Socket服务
        ServerSocket serverSocket = new ServerSocket(4800);
//2. 不断接收连接到此服务的客户端
        while (true) {
            // 拿到连接的客户端
            Socket socket = serverSocket.accept();
            System.out.println("connect success");

            // 为每一个连接的Socket开启一个线程去处理
            new Thread(new ServerThread(socket)).start();
        }
    }
}
