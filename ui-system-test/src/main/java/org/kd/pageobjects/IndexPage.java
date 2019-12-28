package org.kd.pageobjects;

import org.kd.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class IndexPage extends Page {
    public IndexPage(String url) {
        super(WebDriverFactory.createChromeDriver(), url);
    }

    @Override
    protected void findElements() {
        //TODO
    }
}
