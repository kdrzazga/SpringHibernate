package org.kd.main.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private final ClassLoader objClassLoader;

    public PropertiesReader() {
        objClassLoader = getClass().getClassLoader();
    }

    public String readKey(String key) {
        return readKey("application.properties", key);
    }

    public String readKey(String propertiesFilename, String key) {
        var commonProperties = new Properties();
        if (propertiesFilename != null && !propertiesFilename.trim().isEmpty()
                && key != null && !key.trim().isEmpty()) {
            try (
                    var objFileInputStream = new FileInputStream(Objects.requireNonNull(objClassLoader.getResource(propertiesFilename)).getFile())
            ) {
                commonProperties.load(objFileInputStream);
                return String.valueOf(commonProperties.get(key));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }
}