package cocomo.controllers;

import cocomo.data.CocomoProject;
import cocomo.data.Property;
import cocomo.data.Result;
import cocomo.exception.MissingRequiredFieldException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;


public class CocomoFXMLController implements BasedController, Initializable {
    @FXML
    private TextField size;
    @FXML
    private ComboBox<CocomoProject> projectType;
    @FXML
    private TableView<Property> properties;
    @FXML
    private TableColumn<Property, String> name;
    @FXML
    private TableColumn<Property, RadioButton> veryLow;
    @FXML
    private TableColumn<Property, RadioButton> low;
    @FXML
    private TableColumn<Property, RadioButton> average;
    @FXML
    private TableColumn<Property, RadioButton> high;
    @FXML
    private TableColumn<Property, RadioButton> veryHigh;
    @FXML
    private TableColumn<Property, RadioButton> critical;

    @Override
    public Result getResult() {
        try {
            double size = Double.parseDouble(this.size.getText());
            CocomoProject cocomoProject = projectType.getSelectionModel().getSelectedItem();
            Optional.ofNullable(cocomoProject).orElseThrow(MissingRequiredFieldException::new);

            double eaf = Arrays.stream(Property.values())
                    .mapToDouble(p -> (double) p.toggleGroup.getSelectedToggle().getUserData())
                    .reduce(1, (a, b) -> a * b);
            double pm = eaf * cocomoProject.a * Math.pow(size, cocomoProject.b);
            double tm = cocomoProject.c * Math.pow(pm, cocomoProject.d);
            double ss = pm / tm;
            return new Result(pm, tm, ss);
        } catch (NumberFormatException nfe) {
            AlertController.showAlert(Alert.AlertType.WARNING, "Неправильний формат вводу",
                    "Введіть числове значення в поле кількості рядків.");
        } catch (MissingRequiredFieldException mrfe) {
            AlertController.showAlert(Alert.AlertType.WARNING, "Неправильний формат вводу",
                    "Введіть дані у всі поля.");
        }
        return new Result(0, 0, 0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectType.getItems().setAll(CocomoProject.values());

        name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().name));
        veryLow.setCellValueFactory(cell -> simpleObjectProperty(cell.getValue().veryLow, cell.getValue().toggleGroup, false));
        low.setCellValueFactory(cell -> simpleObjectProperty(cell.getValue().low, cell.getValue().toggleGroup, false));
        average.setCellValueFactory(cell -> simpleObjectProperty(cell.getValue().average, cell.getValue().toggleGroup, true));
        high.setCellValueFactory(cell -> simpleObjectProperty(cell.getValue().high, cell.getValue().toggleGroup, false));
        veryHigh.setCellValueFactory(cell -> simpleObjectProperty(cell.getValue().veryHigh, cell.getValue().toggleGroup, false));
        critical.setCellValueFactory(cell -> simpleObjectProperty(cell.getValue().critical, cell.getValue().toggleGroup, false));

        properties.getItems().setAll(Property.values());
        properties.setSelectionModel(null);
    }

    private SimpleObjectProperty<RadioButton> simpleObjectProperty(double value, ToggleGroup toggleGroup, boolean selected) {
        RadioButton radioButton;
        if (value == 0) {
            radioButton = new RadioButton("n/a");
            radioButton.setDisable(true);
        } else {
            radioButton = new RadioButton(String.valueOf(value));
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setSelected(selected);
            radioButton.setUserData(value);
        }
        return new SimpleObjectProperty<>(radioButton);
    }
}
