package cocomo.controllers;

import cocomo.data.CocomoProject;
import cocomo.data.Priority;
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
        veryLow.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().buttons.get(Priority.VERY_LOW)));
        low.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().buttons.get(Priority.LOW)));
        average.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().buttons.get(Priority.AVERAGE)));
        high.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().buttons.get(Priority.HIGH)));
        veryHigh.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().buttons.get(Priority.VERY_HIGH)));
        critical.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().buttons.get(Priority.CRITICAL)));

        properties.getItems().setAll(Property.values());
        properties.setSelectionModel(null);
    }
}
