package org.kd.main.client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.presenter.TraderPresenter;
import org.kd.main.client.view.lib.PropertiesReader;

public class Trader extends Application {

    private PresenterHandler handler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.handler = new TraderPresenter();

        initialize();

        var loader = new FXMLLoader(getClass().getResource("main_form.fxml"));
        Parent root = loader.load();
        
        setupPrimaryStage(primaryStage, root);

        TraderViewController.setHandler(this.handler);

        ((TraderViewController)loader.getController()).loadParties();
        ((TraderViewController)loader.getController()).loadFunds();
        ((TraderViewController)loader.getController()).loadTrades();
    }

    private void setupPrimaryStage(Stage primaryStage, Parent root) {
        primaryStage.setTitle(new PropertiesReader().readKey("app.title"));
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root, 450, 415));
        primaryStage.setOnCloseRequest(event -> exit());

        primaryStage.show();
    }

    private void initialize() {
        this.handler.initApplication();
    }

    private void exit() {
        this.handler.saveDb();
    }

    public static void start(String[] args) {
        launch(args);
    }

}
