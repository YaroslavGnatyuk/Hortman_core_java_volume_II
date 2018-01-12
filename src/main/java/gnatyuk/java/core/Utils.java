package gnatyuk.java.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    public static String getPropertyValue(String propertyFile, String propertyKey) {
        Properties props = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(propertyFile))) {
            props.load(inputStream);
            return props.getProperty(propertyKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
