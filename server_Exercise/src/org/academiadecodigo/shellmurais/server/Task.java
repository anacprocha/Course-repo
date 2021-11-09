package org.academiadecodigo.shellmurais.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Task implements Runnable {
    private Socket clientSocket;

    public Task(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();

            String request = in.readLine();


            System.out.println(request);

            String[] resourceName = request.split(" ");
            System.out.println(resourceName[1]);

            String response;
            String path;


            switch (resourceName[1]) {
                case "/batatinha.jpeg":
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/batatinha.jpeg";
                    response = "HTTP/1.0 200 Document Follows\r\n" +
                            "Content-Type: image/jpeg \r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;

                case "/batata.jpeg":
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/batata.jpeg";
                    response = "HTTP/1.0 200 Document Follows\r\n" +
                            "Content-Type: image/jpeg \r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;
                case "/meteor.jpeg":
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/meteor.jpeg";
                    response = "HTTP/1.0 200 Document Follows\r\n" +
                            "Content-Type: image/jpeg \r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;

                case "/newSky.jpeg":
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/newSky.jpeg";
                    response = "HTTP/1.0 200 Document Follows\r\n" +
                            "Content-Type: image/jpeg \r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;

                case "/legend":
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/TheLegend.html";
                    response = "HTTP/1.0 200 Document Follows\r\n" +
                            "Content-Type: text/html; charset=UTF-8 \r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;

                case "/":
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/Index.html";
                    response = "HTTP/1.0 200 Document Follows\r\n" +
                            "Content-Type: text/html; charset=UTF-8 \r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;

                default:
                    path = "/Users/codecadet/Desktop/ana/exercicios/Server/resources/FileNotFound.html";
                    response = "HTTP/1.0 404 Not Found\r\n" +
                            "Content-Type: text/html; charset=UTF-8\r\n" +
                            "Content-Length: " + getSize(path) + " \r\n" +
                            "\r\n";
                    break;
            }

            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.write(getFile(path));

            System.out.println(response);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        private byte[] getFile (String path) throws IOException {
            File file = new File(path);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return fileContent;

        }

        private Long getSize (String path){
            File file = new File(path);
            return file.length();
        }
    }
