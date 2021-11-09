package org.academiadecodigo.shellmurais.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        ServerConnect server = new ServerConnect();
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        while(true) {

            Socket connection = server.init();

            cachedPool.submit(new Task(connection));


        }
    }
}
