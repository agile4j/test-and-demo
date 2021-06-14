package com.agile4j.demo.io.bio.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author liurenpeng <liurenpeng@kuaishou.com>
 * Created on 2021-06-14
 */
public class Client {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1";
        int serverPort = 9999;
        Socket socket = null;

        try {
            socket = new Socket(serverHost, serverPort);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input = consoleReader.readLine();
            System.out.println("input:" + input);

            writer.write(input + "\n");
            writer.flush();

            String resp = reader.readLine();
            System.out.println("resp:" + resp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
