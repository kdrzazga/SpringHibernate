package org.kd.main.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kd.main.client.presenter.PresenterHandler;

public class AccountDetailsPanelController {

    private static PresenterHandler handler;

    private static Long accountId = 2001L;

    public static void setHandler(PresenterHandler handler) {
        AccountDetailsPanelController.handler = handler;
    }

    public static void setAccountId(Long accountId) {
        AccountDetailsPanelController.accountId = accountId;
    }

    @FXML
    public void initialize(){
        customerIdLabel.setText(accountId.toString());
    }

    @FXML
    private Label customerIdLabel;//TODO

    @FXML
    public void returnToMainScreen() {
        Trader.getInstance().activateMainScreen();
    }


}
