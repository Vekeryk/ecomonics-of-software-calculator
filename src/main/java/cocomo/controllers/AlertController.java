package cocomo.controllers;

import javafx.scene.control.Alert;

public class AlertController {
    public static void showAlert(Alert.AlertType alertType, String title, String header) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
