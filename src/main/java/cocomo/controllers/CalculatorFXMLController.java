package cocomo.controllers;

import cocomo.data.Result;
import javafx.fxml.FXML;
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

    public void showResult() {
        String method = methods.getSelectionModel().getSelectedItem().getText();
        Result result;
        switch (method) {
            case "COCOMO": result = cocomoController.getResult(); break;
            default: throw new IllegalArgumentException();
        }
        peopleOnMonth.setText(String.format("%.2f", result.peopleOnMonth));
        timeAtMonth.setText(String.format("%.2f", result.timeAtMonth));
        numberOfEmployee.setText(String.format("%.2f", result.numberOfEmployee));
    }
}
