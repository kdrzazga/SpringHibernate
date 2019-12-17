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

    @FXML
    private Label customerIdLabel;//TODO

    @FXML
    public void returnToMainScreen() {
        Trader.getInstance().activateMainScreen();
    }

    public static void setAccountId(Long accountId) {
        //CustomerDetailsPanelController.accountId = accountId;
        //customerIdLabel.setText(accountId.toString());
        ;
    }
}
