package org.kd.pageobjects;

import org.apache.commons.io.FileUtils;
import org.kd.utils.WindowUtils;
import org.kd.exceptions.SiteNotOpenedException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public abstract class Page {

    protected WebDriver driver;
    protected final String url;
    protected final Logger logger = Logger.getGlobal();

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void load() {
        this.driver.get(url);
        findElements();
    }

    protected abstract void findElements();

    public void waitForPageLoaded() {
        long startTime = System.currentTimeMillis();
        final String jsScript = "return document.readyState";
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript(jsScript)
                .equals("complete");

        WebDriverWait wait = new WebDriverWait(this.driver, 8);

        try {
            wait.until(expectation);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new SiteNotOpenedException(this.url, Math.round(System.currentTimeMillis() - (startTime / 1000)));
        }
    }

    public void navigateTo() {
        this.driver.get(this.url);
    }

    public void refresh() {
        this.driver.navigate().refresh();
    }

    public void navigateBack() {
        this.driver.navigate().back();
    }

    public void takeScreenshot(String filePath) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openInNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open(arguments[0], '_blank');", url);
        WindowUtils.switchWindow(driver, url, true);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return this.url;
    }
}
