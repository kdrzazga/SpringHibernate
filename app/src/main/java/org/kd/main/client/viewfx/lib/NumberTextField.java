package org.kd.main.client.viewfx.lib;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    public NumberTextField() {
        super();
        createOnChangeListener();
    }

    public NumberTextField(String text) {
        super(text);
        createOnChangeListener();
    }

    public Long getLong() {
        return getDouble().longValue();
    }

    public Double getDouble() {
        return Double.valueOf(this.getText());
    }

    private void createOnChangeListener() {

        NumberTextField pointer = this;

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*\\.?[0-9]*")) {
                String digitsDotsOnly = newValue.replaceAll("[^\\d.]", "");
                pointer.setText(DigitConverter.removeExtraDots(digitsDotsOnly));
            }
        });
    }

}
