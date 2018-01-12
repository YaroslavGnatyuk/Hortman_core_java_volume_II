package gnatyuk.java.core.horstmann.networking.SocketChannelDemo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//// TODO: 15.11.17 Make ThreadEchoHandler Callable
public class ThreadEchoHandler implements Runnable {
    Socket incoming;

    public ThreadEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try (InputStream inputStream = incoming.getInputStream();
             OutputStream outputStream = incoming.getOutputStream()) {
            Scanner in = new Scanner(inputStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), Boolean.TRUE);
            boolean done = false;

            while (!done) {
                String value = in.nextLine();
                if (value.split(" ")[0].equals("login") && value.split(" ")[1].equals("root")) {
                    value = in.nextLine();
                    if (value.split(" ")[0].equals("password") && value.split(" ")[1].equals("root")) {
                        outputStream.write("Ok".getBytes());
                        outputStream.flush();
                        System.out.println("!!!");
                        break;
                    }else{
                        continue;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
