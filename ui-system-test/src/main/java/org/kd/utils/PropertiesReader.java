package org.kd.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.logging.Logger;

public class PropertiesReader {

    private static final PropertiesConfiguration config = new PropertiesConfiguration();
    private static final String propertiesFilePath = "src/main/resources/application.properties";
    private static final Logger logger = Logger.getGlobal();

    static {
        try {
            config.load(propertiesFilePath);
        } catch (ConfigurationException e) {
            logger.warn("Could not parse " + propertiesFilePath);
        }
    }

    public static String readAppUrl() {
        return readString("app-under-test.url");
    }

    public static String readBrowser() {
        return readString("browser");
    }

    public static String readChromeDriverPath() {
        return readString("driver.chrome.path");
    }

    public static String readFirefoxDriverPath() {
        return readString("driver.firefox.path");
    }

    public static int readTimeout() {
        return readInteger("timeout.default");
    }

    public static String readString(String keyName) {
        return config.getString(keyName);
    }

    public static int readInteger(String keyName) {
        return config.getInt(keyName);
    }

}
