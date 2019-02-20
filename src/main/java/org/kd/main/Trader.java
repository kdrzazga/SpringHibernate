package org.kd.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kd.main.model.data.DataModelManager;
import org.kd.main.view.PropertiesReader;
import org.kd.main.view.ViewerController;

public class Trader extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();

        var loader = new FXMLLoader(getClass().getResource("main_form.fxml"));
        Parent root = loader.load();
        
        setupPrimaryStage(primaryStage, root);

        ((ViewerController)loader.getController()).loadParties();
        ((ViewerController)loader.getController()).loadFunds();
        ((ViewerController)loader.getController()).loadTrades();
    }

    private void setupPrimaryStage(Stage primaryStage, Parent root) {
        primaryStage.setTitle(new PropertiesReader().readKey("app.title"));
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root, 450, 415));
        primaryStage.setOnCloseRequest(event -> exit());

        primaryStage.show();
    }

    private void initialize() {
        DataModelManager.initApplication();
    }

    private void exit() {
        DataModelManager.saveDb();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
