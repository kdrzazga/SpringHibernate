package org.kd.main.client.viewfx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.util.Optional;

class AlertHelper {

    public static void showErrorAlert(String message) {
        var window = Trader.getInstance().getWindow();
        showAlert(Alert.AlertType.ERROR, window, "Error", message);
    }

    public static void showInfoAlert(String message) {
        var window = Trader.getInstance().getWindow();
        showAlert(Alert.AlertType.INFORMATION, window, "Info", message);
    }

    public static boolean showConfirmationAlert(String message) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(Trader.getInstance().getWindow());
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        var alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
