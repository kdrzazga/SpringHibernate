package org.kd.pageobjects;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Optional;

public class QuietSelect extends Select {
    public QuietSelect(WebElement element) {
        super(element);
    }

    public void selectByValue(String value) {
        try {
            super.selectByValue(value);
        } catch (WebDriverException e) {
            String caption = Optional
                    .ofNullable(this.getFirstSelectedOption())
                    .map(WebElement::getText)
                    .orElse("");

            if (!caption.equals(value))
                throw new WebDriverException("Could not select an item " + value);
        }
    }

}
