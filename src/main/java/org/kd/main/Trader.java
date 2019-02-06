package org.kd.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kd.main.model.DataModelManager;
import org.kd.main.view.PropertiesReader;

public class Trader extends Application {

    public static boolean devMode = false;

    @Override
    public void
    start(Stage primaryStage) throws Exception {
        initialize();
        Parent root = FXMLLoader.load(getClass().getResource("main_form.fxml"));
        var appTitle = new PropertiesReader().readKey("app.title");
        primaryStage.setTitle(appTitle);
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root, 450, 415));
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
        if (args.length > 0)
            devMode = args[0].equals("--dev") || args[0].equals("-d");

        launch(args);
    }

    public static boolean getDevMode() {
        return devMode;
    }

}
