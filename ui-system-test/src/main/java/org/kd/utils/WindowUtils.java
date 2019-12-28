package org.kd.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class WindowUtils {

    public static void switchWindow(WebDriver driver, String url, boolean switchToGivenURL) {
        for (String winHandle : driver
                .getWindowHandles()) {
            driver
                    .switchTo()
                    .window(winHandle);
            String currentURL = driver
                    .getCurrentUrl();
            boolean isSwitchedToGivenURL = currentURL.contains(url);
            if (isSwitchedToGivenURL == switchToGivenURL) {
                return;
            }
        }
    }

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().setPosition(new Point(0, 0));
        Dimension dim = getScreenDimension();
        driver.manage().window().setSize(dim);
    }

    private static Dimension getScreenDimension() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(Double.valueOf(screenSize.getWidth()).intValue(), Double.valueOf(screenSize.getHeight()).intValue());
    }

    public static void scrollPageToVeryTop(WebDriver driver) {
        scrollPageUp(driver, getScreenDimension().height);
    }

    public static void scrollPageDown(WebDriver driver, int scrollValue) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, " + scrollValue + ")", "");
    }

    public static void scrollPageUp(WebDriver driver, int scrollValue) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, -" + scrollValue + ")", "");
    }

    public static boolean isPageScrolledToBotom(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return (Boolean) jse.executeScript("return ((window.innerHeight + window.scrollY) >= document.body.offsetHeight);", "");
    }

    public static boolean isPageScrolledToTop(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return (Boolean) jse.executeScript("return ((window.innerHeight + window.scrollY) <= 0);", "");
    }
}