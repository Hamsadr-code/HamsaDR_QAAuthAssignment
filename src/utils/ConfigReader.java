package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    public static void loadProperties() {
        try {
            FileInputStream file = new FileInputStream("data/config.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties file",e);
        }
    }

    public static String get(String key) {
        if (properties.isEmpty()) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}