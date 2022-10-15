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
    private Label peopleOnMonth;
    @FXML
    private Label timeAtMonth;
    @FXML
    private Label numberOfEmployee;
    @FXML
    private CocomoFXMLController cocomoController;
    @FXML
    private Cocomo2FXMLController cocomo2Controller;

    public void showResult() {
        try {
            String method = methods.getSelectionModel().getSelectedItem().getText();
            Result result;
            switch (method) {
                case "COCOMO":
                    result = cocomoController.getResult();
                    break;
                case "COCOMO 2":
                    result = cocomo2Controller.getResult();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            peopleOnMonth.setText(String.format("%.2f", result.peopleOnMonth));
            timeAtMonth.setText(String.format("%.2f", result.timeAtMonth));
            numberOfEmployee.setText(String.format("%.2f", result.numberOfEmployee));
        } catch (NumberFormatException nfe) {
            AlertController.showAlert(Alert.AlertType.WARNING, "Неправильний формат вводу",
                    "Введіть числове значення в поле кількості рядків.");
        } catch (MissingRequiredFieldException mrfe) {
            AlertController.showAlert(Alert.AlertType.WARNING, "Неправильний формат вводу",
                    "Введіть дані у всі поля.");
        }
    }

    @FXML
    private void changeMode() {
        peopleOnMonth.setText("");
        timeAtMonth.setText("");
        numberOfEmployee.setText("");
    }
}
