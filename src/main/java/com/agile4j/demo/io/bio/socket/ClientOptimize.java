package com.agile4j.demo.io.bio.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * @author liurenpeng <liurenpeng@kuaishou.com>
 * Created on 2021-06-14
 */
public class ClientOptimize {
    public static void main(String[] args) {
        String quiet = "quiet";
        String serverHost = "127.0.0.1";
        int serverPort = 9999;
        Socket socket = null;
        BufferedWriter writer = null;

        try {
            socket = new Socket(serverHost, serverPort);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));

            String input = null;
            while (true) {
                input = consoleReader.readLine();
                System.out.println("input:" + input);

                writer.write(input + "\n");
                writer.flush();

                if (Objects.equals(input, quiet)) {
                    break;
                }

                String resp = reader.readLine();
                System.out.println("resp:" + resp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("关闭socket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
