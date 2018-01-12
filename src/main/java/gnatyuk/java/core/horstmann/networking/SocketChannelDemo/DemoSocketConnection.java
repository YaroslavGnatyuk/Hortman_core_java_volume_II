package gnatyuk.java.core.horstmann.networking.SocketChannelDemo;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class DemoSocketConnection {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String PORT = "9999";

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(Integer.valueOf(PORT))){
            int counter = 0;
            while (true){
                Socket incoming = server.accept();
                logger.info("Spawning:" + counter++);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread thread = new Thread(r);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
