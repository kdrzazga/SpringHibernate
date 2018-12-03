package org.kd.mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationFormApplication extends Application {

    @Override
    public void
    start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("registration_form.fxml"));
        primaryStage.setTitle("Counterparty Info");
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root, 450 , 230));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
