package gnatyuk.java.core.horstmann.networking.HTTPConnectionDemo;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class HTTPConnectionDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection urlConnection = url.openConnection();

            urlConnection.connect();

            String str = "Yaroslav Gnatyuk";
            byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
            System.out.println("ecncoded value is " + new String(bytesEncoded));

// Decode data on other side, by processing encoded data
            byte[] valueDecoded = Base64.getDecoder().decode(bytesEncoded);
            System.out.println("Decoded value is " + new String(valueDecoded));

            getBooleanValue(str);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    private static boolean getBooleanValue(String word) {
        if (word.length() > 5) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean getBooleanValueNew(String word) {
        if (word.length() > 5) {
            return false;
        } else {
            return true;
        }
    }
}