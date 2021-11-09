package org.academiadecodigo.shellmurais.multichat.server;

import java.util.concurrent.CopyOnWriteArrayList;

public class Room {

    private String name;

    public Room(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
