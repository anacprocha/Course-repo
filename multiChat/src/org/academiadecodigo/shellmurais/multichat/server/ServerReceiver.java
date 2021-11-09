package org.academiadecodigo.shellmurais.multichat.server;

import org.academiadecodigo.shellmurais.multichat.client.Client;
import org.academiadecodigo.shellmurais.multichat.server.Room;
import org.academiadecodigo.shellmurais.multichat.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerReceiver implements Runnable {

    private Socket clientSocket;
    private String clientMessage;
    private String name;
    private Server server;
    private BufferedReader in;
    private PrintWriter out;
    private Room room;


    public ServerReceiver(Socket clientSocket, Server server, Room room) {
        this.clientSocket = clientSocket;
        this.clientMessage = "";
        this.server = server;
        this.room = room;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                clientMessage = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] message = clientMessage.split(":");
            String finalMessage = clientMessage.substring(clientMessage.indexOf(":") + 1);

            if (clientMessage == null) {

                System.out.println("Client connection is closed.");

                server.remove(this);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            switch (message[0]) {
                case "/create":

                    name = message[1];
                    System.out.println(finalMessage);
                    server.broadCast(this, finalMessage);
                    break;

                case "/quit":
                    server.broadCast(this, finalMessage);
                    break;

                case "/whisper":

                    System.out.println(clientMessage);
                    System.out.println(message[1]);
                    server.whisper(this, message[1], finalMessage.substring(finalMessage.indexOf(":") + 1));
                    break;

                case "/name":

                    name = message[2];
                    System.out.println(clientMessage);
                    server.broadCast(this, finalMessage);
                    break;

                case "/crtGroup":
                    System.out.println(clientMessage);
                    server.broadCast(this, this.name + " created a new group " + message[1]);
                    server.createRoom(new Room(message[1]));
                    break;

                case "/chgGroup":
                    System.out.println(clientMessage);
                    Room newRoom = server.getRoom(message[1]);
                    if (newRoom != null) {
                        room = newRoom;
                        break;
                    } else {
                        out.println("Group not found");
                        break;
                    }

                case "/gen":
                    System.out.println(clientMessage);
                    server.broadCast(this, name+ ": " + finalMessage);
                    break;

                case "/groups":
                    System.out.println(server.getGroups());
                    out.println(server.getGroups());
                    break;

                case "/whois":
                    System.out.println(server.getMembers(room));
                    out.println(server.getMembers(room));
                    break;

                default:
                    if (room.getName().equals("general")) {
                        System.out.println(clientMessage);
                        server.broadCast(this, clientMessage);
                        break;
                    }
                    System.out.println(clientMessage);
                    server.sendToGroup(room, this, clientMessage);
                    break;
            }
        }
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

}
