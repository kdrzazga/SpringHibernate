package org.kd.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    static {
        System.setProperty("webdriver.chrome.driver", PropertiesReader.readChromeDriverPath());
        System.setProperty("webdriver.gecko.driver", PropertiesReader.readFirefoxDriverPath());
    }

    public static WebDriver createDriverDefinedInConfig() {
        String browserName = PropertiesReader.readBrowser();

        switch (browserName) {
            case "Headless":
                return createHeadlessDriver();
            case "Firefox":
                return createFirefoxDriver();
            default:
                return createChromeDriver();
        }
    }

    public static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");

        return new ChromeDriver(options);
    }

    public static WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }

    public static WebDriver createHeadlessDriver() {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        return driver;
    }
}
