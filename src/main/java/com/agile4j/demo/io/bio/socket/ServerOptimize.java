package com.agile4j.demo.io.bio.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @author liurenpeng <liurenpeng@kuaishou.com>
 * Created on 2021-06-14
 */
public class ServerOptimize {
    public static void main(String[] args) {
        String quiet = "quiet";
        final int defaultPort = 9999;
        ServerSocket serverSocket = null;

        try {
            // 绑定监听端口
            serverSocket = new ServerSocket(defaultPort);

            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                System.out.println("客户端[" + socket.getPort() + "]已连接");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));

                String msg;
                while ((msg = reader.readLine()) != null) {
                    // 接受客户端发送的消息
                    System.out.println("客户端[" + socket.getPort() + "]：" + msg);

                    if (Objects.equals(msg, quiet)) {
                        break;
                    }

                    // 响应客户端的消息
                    writer.write("服务端：" + msg + "\n");
                    writer.flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("关闭socket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
