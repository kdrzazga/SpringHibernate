package org.kd.main.client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.view.lib.PropertiesReader;
import org.kd.main.common.TraderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Trader extends Application {

    private final static int ID_LABEL_INDEX = 3;

    private static Trader instance;
    private PresenterHandler handler;

    private Stage window;
    private Scene mainScreen;
    private Scene accountDetailsScreen;

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
        var messageTextBox = (TextField) (this.mainScreen.lookup("#messageTextBox"));

        setAccountIdOnDetailsScreen(messageTextBox.getText());
    }

    public void activateMainScreen() {
            this.window.setScene(this.mainScreen);
    }

    private void setupCustomerDetailsScreen() throws java.io.IOException {
        var loader = new FXMLLoader(getClass().getResource("customer_details_panel.fxml"));
        Parent customerDetailsRoot = loader.load();
        this.accountDetailsScreen = new Scene(customerDetailsRoot, 600, 494);
    }

    private void setupMainScreen() throws java.io.IOException {
        var loader = new FXMLLoader(getClass().getResource("main_form.fxml"));
        Parent mainScreenRoot = loader.load();
        this.mainScreen = new Scene(mainScreenRoot, 450, 565);

        var controller = ((TraderViewController) loader.getController());

        controller.loadBanks();
        controller.loadAccounts();
        controller.loadTransfers();
    }

    private void setupWindow() {
        this.window.setTitle(new PropertiesReader().readKey("app.title"));
        this.window.setIconified(false);

        this.window.setOnCloseRequest(event -> exit());
        this.window.show();
    }

    private void setAccountIdOnDetailsScreen(String text) {
        var idLabel = this.accountDetailsScreen.getRoot().getChildrenUnmodifiable().get(ID_LABEL_INDEX);
        ((Label) idLabel).setText(text);
    }

    private void initialize() {
        this.handler.initApplication();
    }

    private void exit() {
        this.handler.saveDb();
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
}
