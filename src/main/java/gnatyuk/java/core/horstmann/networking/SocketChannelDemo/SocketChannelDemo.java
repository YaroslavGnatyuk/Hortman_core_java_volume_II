package gnatyuk.java.core.horstmann.networking.SocketChannelDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        try(SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("178.219.93.93", 2223))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
