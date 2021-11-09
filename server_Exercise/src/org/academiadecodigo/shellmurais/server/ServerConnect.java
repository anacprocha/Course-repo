package org.academiadecodigo.shellmurais.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ServerConnect {
    private ServerSocket serverSocket;

    public ServerConnect() {
        try {
            serverSocket = new ServerSocket(5001);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket init() {

            Socket clientSocket;
            try {
                return clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }


}
