package org.kd.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kd.main.model.DataModelManager;

public class Trader extends Application {

    private final String APP_TITLE = "Trader";

    @Override
    public void
    start(Stage primaryStage) throws Exception {
        initialize();
        Parent root = FXMLLoader.load(getClass().getResource("main_form.fxml"));
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root, 450, 325));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            exit();
        });
    }

    private void initialize() {
        DataModelManager.initApplication();
    }

    private void exit() {
        DataModelManager.clearDb();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
