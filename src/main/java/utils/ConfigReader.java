package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static Properties prop;
    private static final Logger log = LogManager.getLogger(ConfigReader.class);

    public static Properties initProperties() {
        prop = new Properties();
        try {
        	log.info("Loading configuration from config.properties");
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
            log.info("Configuration loaded successfully");
        } catch (IOException e) {
        	log.error("Failed to load config.properties file", e);
        }
        return prop;
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
