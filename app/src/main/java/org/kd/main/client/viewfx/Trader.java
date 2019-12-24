package org.kd.main.client.viewfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.viewfx.lib.PropertiesReader;
import org.kd.main.common.TraderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class Trader extends Application {

    private final static int ID_LABEL_INDEX = 3;

    private static Trader instance;
    private PresenterHandler handler;

    private Stage window;
    private Scene mainScreen;
    private Scene accountDetailsScreen;

    private TraderViewController mainScreenContoller;
    private AccountDetailsPanelController accountDetailsPanelController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        var context = new AnnotationConfigApplicationContext(TraderConfig.class);

        this.handler = context.getBean(PresenterHandler.class);
        initialize();

        TraderViewController.setHandler(this.handler);
        AccountDetailsPanelController.setHandler(this.handler);

        setupWindow();
        setupMainScreen();
        setupCustomerDetailsScreen();

        activateMainScreen();
        instance = this;
    }

    public void activateAccountDetailsScreen() {
        this.window.setScene(this.accountDetailsScreen);

        //TODO: unclean, poor method of passing an argument between scenes.
        var messageTextBox = (TextField) (this.mainScreen.lookup("#messageTextBox"));

        setAccountIdOnDetailsScreen(messageTextBox.getText());
    }

    public void activateMainScreen() {
        this.window.setScene(this.mainScreen);
    }

    private void setupCustomerDetailsScreen() throws java.io.IOException {
        var loader = new FXMLLoader(getClass().getResource("customer_details_panel.fxml"));
        Parent customerDetailsRoot = loader.load();
        this.accountDetailsPanelController = loader.getController();
        this.accountDetailsScreen = new Scene(customerDetailsRoot, 600, 494);
    }

    private void setupMainScreen() throws java.io.IOException {
        var loader = new FXMLLoader(getClass().getResource("main_form.fxml"));
        Parent mainScreenRoot = loader.load();
        this.mainScreen = new Scene(mainScreenRoot, 450, 565);

        this.mainScreenContoller = loader.getController();

        this.mainScreenContoller.loadBanks();
        this.mainScreenContoller.loadAccounts();
        this.mainScreenContoller.loadTransfers();
    }

    private void setupWindow() {
        this.window.setTitle(new PropertiesReader().readKey("app.title"));
        this.window.setIconified(false);

        this.window.setOnCloseRequest(new CloseHandler());
        this.window.show();
    }

    private void setAccountIdOnDetailsScreen(String text) {
        var idLabel = this.accountDetailsScreen.getRoot().getChildrenUnmodifiable().get(ID_LABEL_INDEX);
        ((Label) idLabel).setText(text);

        var id = extractId(text);

        if (this.accountDetailsPanelController != null) {
            this.accountDetailsPanelController.loadDebitCards(id);
            this.accountDetailsPanelController.loadCreditCards(id);
            this.accountDetailsPanelController.loadCredits(id);
        }
    }

    private Long extractId(String text) {
        text = text.replaceAll("", "");//TODO: create regex
        return Long.valueOf(text);
    }

    private void initialize() {
        this.handler.initApplication();
    }

    public void start(String[] args) {
        launch(args);
    }

    public static Trader getInstance() {
        return instance;
    }

    public Stage getWindow() {
        return window;
    }

    private class CloseHandler implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent event) {
            if (AlertHelper.showConfirmationAlert("Really exit?"))
                exit();
            else
                event.consume();
        }

        private void exit() {
            var dBSavedMsg = Trader.this.handler.saveDb() ?
                    "Database save to file" : "Database not saved";
            Logger.getGlobal().info(dBSavedMsg);
        }
    }
}
