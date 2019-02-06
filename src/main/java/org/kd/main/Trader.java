package org.kd.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kd.main.model.DataModelManager;
import org.kd.main.view.PropertiesReader;
import org.kd.main.view.ViewerController;

public class Trader extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();

        var loader = new FXMLLoader(getClass().getResource("main_form.fxml"));
        Parent root = loader.load();
        
        var appTitle = new PropertiesReader().readKey("app.title");
        setupPrimaryStage(primaryStage, root, appTitle);

        var controller = (ViewerController)loader.getController();
        controller.loadParties();
        controller.loadFunds();
    }

    private void setupPrimaryStage(Stage primaryStage, Parent root, String appTitle) {
        primaryStage.setTitle(appTitle);
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root, 450, 415));
        primaryStage.setOnCloseRequest(event -> {
            exit();
        });

        primaryStage.show();
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
