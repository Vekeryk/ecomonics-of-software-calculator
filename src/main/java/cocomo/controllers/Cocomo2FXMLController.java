package cocomo.controllers;

import cocomo.data.Property;
import cocomo.data.Result;
import cocomo.data.cocomo2.EarlyCostProperty;
import cocomo.data.cocomo2.PostCostProperty;
import cocomo.data.cocomo2.Project;
import cocomo.data.cocomo2.ScaleProperty;
import cocomo.exception.MissingRequiredFieldException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;


public class Cocomo2FXMLController implements BasedController, Initializable {
    @FXML
    private TextField size;
    @FXML
    private ComboBox<Project> calculationType;
    @FXML
    private TableView<Property> scaleDrivers;
    @FXML
    private TableView<Property> costDrivers;

    @Override
    public Result getResult() {
        double size = Double.parseDouble(this.size.getText());
        Project type = calculationType.getSelectionModel().getSelectedItem();
        Optional.ofNullable(type).orElseThrow(MissingRequiredFieldException::new);
        ObservableList<Property> costDriversItems = costDrivers.getItems();

        double sf = Arrays.stream(ScaleProperty.values())
                .mapToDouble(p -> (double) p.toggleGroup.getSelectedToggle().getUserData())
                .reduce(0, Double::sum);
        double eaf = costDriversItems.stream()
                .mapToDouble(p -> (double) p.getToggleGroup().getSelectedToggle().getUserData())
                .reduce(1, (a, b) -> a * b);
        double e = type.b + 0.01 * sf;
        double pm = eaf * type.a * Math.pow(size, e);
        double sced = (double) costDriversItems.get(costDriversItems.size() - 1).getToggleGroup().getSelectedToggle().getUserData();
        double tm = sced * type.c * Math.pow(pm/sced, type.d + 0.2 * (e - type.b));
        double ss = pm / tm;
        return new Result(pm, tm, ss);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calculationType.getItems().setAll(Project.values());
        costDrivers.setPlaceholder(new Label("Оберіть тип розрахунку!"));
        fillTable(scaleDrivers, ScaleProperty.values());
        fillTable(costDrivers, EarlyCostProperty.values());
        costDrivers.getItems().clear();
    }

    public void changeType() {
        Project selectedItem = calculationType.getSelectionModel().getSelectedItem();
        if (selectedItem == Project.EARLY_DESIGN) {
            fillTable(costDrivers, EarlyCostProperty.values());
        } else {
            fillTable(costDrivers, PostCostProperty.values());
        }
    }
}
