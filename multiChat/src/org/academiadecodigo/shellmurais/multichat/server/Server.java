package org.academiadecodigo.shellmurais.multichat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port = 9000;
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<ServerReceiver> clientList;
    private CopyOnWriteArrayList<Room> rooms;

    public Server() {
        //this.port = getPort();
        clientList = new CopyOnWriteArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rooms = new CopyOnWriteArrayList<>();
        Room general = new Room("general");
        rooms.add(general);
    }

    public int getPort() {
        BufferedReader portGetter = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Port: ");

        String port = "";
        try {
            port = portGetter.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(port);
    }

    public void listen() throws IOException {
        Socket client = serverSocket.accept();

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        ServerReceiver clientThread = new ServerReceiver(client, this, rooms.get(0));
        //rooms.add(clientThread);

        clientList.add(clientThread);

        cachedPool.submit(clientThread);
    }

    public boolean isBound() {
        return serverSocket.isBound();
    }

    public void broadCast(ServerReceiver origin, String message) {
        PrintWriter out = null;
        for (ServerReceiver client : clientList) {

            if (origin.equals(client)) {
                continue;
            }
            try {
                out = new PrintWriter(client.getClientSocket().getOutputStream(), true);
                out.println(rooms.get(0).getName() + "/ " + message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToGroup(Room room, ServerReceiver origin, String message) {
        PrintWriter out = null;
        for (ServerReceiver client : clientList) {

            if (client.getRoom().equals(room)) {
                if (!origin.equals(client)) {
                    try {
                        out = new PrintWriter(client.getClientSocket().getOutputStream(), true);
                        out.println(room.getName() + "/ " + message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void whisper(ServerReceiver sender, String name, String message) {
        PrintWriter out = null;
        for (ServerReceiver client : clientList) {
            if (!client.getName().equals(name)) {
                continue;
            }
            try {
                out = new PrintWriter(client.getClientSocket().getOutputStream(), true);
                out.println(sender.getRoom().getName() + "/ " + sender.getName() + " whispered to you:" + message);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out = new PrintWriter(sender.getClientSocket().getOutputStream(), true);
            out.println("user not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void remove(ServerReceiver client) {
        clientList.remove(client);
    }

    public void createRoom(Room room) {
        rooms.add(room);
    }

    public Room getRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public String getGroups(){
        String groups="";
        for (Room room: rooms){
            groups += room.getName() + "\n";
        }
        return groups;
    }

    public String getMembers(Room room){
        String members ="";

        for (ServerReceiver client : clientList){
            if(client.getRoom().equals(room)){
                members += client.getName() + "\n";
            }
        }
        return members;
    }

    /*public void receiveInfo() {
        for (int i = 0; i < clientList.size(); i++) {
            Iterator<Socket> iterator = clientList.iterator();
            if (iterator.hasNext()) {
                try {
                    Socket clientSocket = clientList.iterator().next();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    clientMessage = clientSocket.toString() + " " + in.readLine();
                    System.out.println(clientMessage);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }*/


}
