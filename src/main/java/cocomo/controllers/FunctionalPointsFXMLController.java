package cocomo.controllers;

import cocomo.data.Property;
import cocomo.data.Result;
import cocomo.data.functionalpoints.DifficultyLevel;
import cocomo.data.functionalpoints.EnvironmentalFactor;
import cocomo.data.functionalpoints.ProgrammingLanguage;
import cocomo.exception.MissingRequiredFieldException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class FunctionalPointsFXMLController extends BasedController implements Initializable {

    @FXML
    private ComboBox<ProgrammingLanguage> programmingLanguage;
    @FXML
    private TableView<Property> difficultyLevels;
    @FXML
    private TableView<Property> environmentalFactors;
    @FXML
    private Accordion accordion;

    public final String[] resultNames = {
            "Корегуючий множник складності",
            "Скореговані функціональні точки",
            "Рядки LOC"
    };

    public String[] getResultNames() {
        return resultNames;
    }

    @Override
    public Result getResult() {
        ProgrammingLanguage language = programmingLanguage.getSelectionModel().getSelectedItem();
        Optional.ofNullable(language).orElseThrow(
                () -> new MissingRequiredFieldException("Оберіть мову програмування"));

        double tdi = Arrays.stream(EnvironmentalFactor.values())
                .mapToDouble(p -> (double) p.toggleGroup.getSelectedToggle().getUserData())
                .sum();
        double ufpc = Arrays.stream(DifficultyLevel.values())
                .mapToDouble(p -> p.getAmount() * (double) p.getToggleGroup().getSelectedToggle().getUserData())
                .sum();
        double vaf = 0.65 + 0.01 * tdi;
        double afpc = ufpc * vaf;

        double ml = programmingLanguage.getSelectionModel().getSelectedItem().codeLines;
        double sloc = afpc * ml;
        return new Result(vaf, afpc, sloc);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        programmingLanguage.getItems().setAll(ProgrammingLanguage.values());
        fillTable(difficultyLevels, DifficultyLevel.values());
        fillTable(environmentalFactors, EnvironmentalFactor.values());
        accordion.expandedPaneProperty().addListener((observable, oldPane, newPane) -> {
            if (oldPane != null) oldPane.setCollapsible(true);
            if (newPane != null) Platform.runLater(() -> newPane.setCollapsible(false));
        });
        accordion.setExpandedPane(accordion.getPanes().get(1));
    }
}
