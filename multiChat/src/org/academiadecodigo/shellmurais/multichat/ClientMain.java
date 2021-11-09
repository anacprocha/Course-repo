package org.academiadecodigo.shellmurais.multichat;

import org.academiadecodigo.shellmurais.multichat.client.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {

    public static void main(String[] args) {

        Client client = new Client();
        ExecutorService thread = Executors.newSingleThreadExecutor();



        while (client.isBound()){

            thread.submit(client);

            client.receiveInfo();



        }
    }
}
