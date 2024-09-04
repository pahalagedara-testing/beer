package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Component
    public class SocketListener {

        private static final int PORT = 8081;  // Specify the port to listen to

        public SocketListener() {
            startServer();
        }

        private void startServer() {
            new Thread(() -> {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                    System.out.println("Server is listening on port " + PORT);

                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("New client connected");

                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            System.out.println("Received message: " + inputLine);
                            // Process the message as needed
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
