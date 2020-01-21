package org.kd.pageobjects;

import org.kd.utils.WebDriverFactory;

public class IndexPage extends Page {
    public IndexPage(String url) {
        super(WebDriverFactory.createChromeDriver(), url);
    }

    @Override
    protected void findElements() {
        //TODO
    }
}
