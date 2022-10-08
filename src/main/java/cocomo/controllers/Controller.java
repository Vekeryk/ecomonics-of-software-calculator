package cocomo.controllers;

import cocomo.data.Project;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    private Label result;
    @FXML
    private TextField size;
    @FXML
    private ComboBox<Project> projectType;

    public void show() {
        result.setText(size.getText());
        projectType.getItems().setAll(Project.values());
    }
}
