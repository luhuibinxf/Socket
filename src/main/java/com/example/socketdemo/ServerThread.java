package com.example.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/4/2 0002.
 */

class ServerThread implements Runnable {
    private Socket mSocket=null;
    private BufferedReader mBufereReader=null;
    public ServerThread(Socket socket) {
       mSocket=socket;

        // 3.1 打开输入流
        try {
            mBufereReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream(),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
        String content=null;
        // 4.1 读取传递的信息
        while ((content=mBufereReader.readLine())!=null){

            System.out.println(content);

            // 3.2 打开socket的输出流，用于将数据传递给客户端
            OutputStream out = mSocket.getOutputStream();

            // 当客户端发送一个bye，通话结束
            if ("bye".equals(content)){
                out.write(("通话结束了").getBytes("utf-8"));

                // 5. 关闭socket
                out.close();
                mBufereReader.close();
                mSocket.close();
                return;
            }

            // 4.2 写入数据传递给客户端
            out.write((content+":来自服务端的返回。\n").getBytes("utf-8"));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
