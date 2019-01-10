package org.kd.main.view;

import javafx.scene.control.Alert;
import javafx.stage.Window;

class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        var alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
