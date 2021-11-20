package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilTool {
    private Properties properties;

    public UtilTool(String path) {
        properties = new Properties();
        InputStream resourceAsStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
