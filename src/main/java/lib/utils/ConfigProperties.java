package lib.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static final String propertiesFileName = "./src/main/java/resources/config.properties";
    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream(propertiesFileName);
            prop.load(input);
        } catch (IOException var1) {
            var1.printStackTrace();
        }
    }

    public static String getURLDemoqa() {
        return prop.getProperty("urlDemoqa");
    }

    public static String getBrowser() {
        return prop.getProperty("browser");
    }

    public static boolean getHeadless() {
        return prop.getProperty("headless").equals("true");
    }
}
