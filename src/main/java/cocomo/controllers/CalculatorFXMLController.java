package cocomo.controllers;

import cocomo.data.Result;
import cocomo.exception.MissingRequiredFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public class CalculatorFXMLController {
    @FXML
    private TabPane methods;
    @FXML
    private Label result1;
    @FXML
    private Label result2;
    @FXML
    private Label result3;
    @FXML
    private Label resultName1;
    @FXML
    private Label resultName2;
    @FXML
    private Label resultName3;
    @FXML
    private CocomoFXMLController cocomoController;
    @FXML
    private Cocomo2FXMLController cocomo2Controller;
    @FXML
    private FunctionalPointsFXMLController functionalPointsController;

    public void showResult() {
        try {
            BasedController controller = getCurrentController();
            Result result = controller.getResult();
            resultName1.setText(controller.getResultNames()[0]);
            resultName2.setText(controller.getResultNames()[1]);
            resultName3.setText(controller.getResultNames()[2]);
            result1.setText(String.format("%.2f", result.result1));
            result2.setText(String.format("%.2f", result.result2));
            result3.setText(String.format("%.2f", result.result3));
        } catch (NumberFormatException nfe) {
            AlertController.showAlert(Alert.AlertType.WARNING, "Неправильний формат вводу",
                    "Введіть числове значення в поле кількості.");
        } catch (MissingRequiredFieldException mrfe) {
            AlertController.showAlert(Alert.AlertType.WARNING, "Помилка розрахунку",
                    mrfe.getMessage());
        }
    }

    private BasedController getCurrentController() {
        int selectedTab = methods.getSelectionModel().getSelectedIndex();

        switch (selectedTab) {
            case 0:
                return cocomoController;
            case 1:
                return cocomo2Controller;
            case 2:
                return functionalPointsController;
            default:
                throw new IllegalArgumentException();
        }
    }

    @FXML
    private void changeMode() {
        resultName1.setText("");
        resultName2.setText("");
        resultName3.setText("");
        result1.setText("");
        result2.setText("Натисніть кнопку, щоб отримати результат");
        result3.setText("");
    }
}
