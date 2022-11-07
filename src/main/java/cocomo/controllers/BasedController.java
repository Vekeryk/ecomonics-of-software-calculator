package cocomo.controllers;

import cocomo.data.Property;
import cocomo.data.Result;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public abstract class BasedController {

    public final String[] resultNames = {
            "Трудомісткість(люд. × міс.)",
            "Час розробки в календарих місяцях",
            "Чисельність персоналу"
    };

    public abstract Result getResult();

    public String[] getResultNames() {
        return resultNames;
    }

    protected void fillTable(TableView<Property> tableView, Property[] properties) {
        List<TableColumn<Property, ?>> priorityColumns = new ArrayList<>();
        List<SimpleObjectProperty<Control>> controls = properties[0].getControls();
        String[] priorities = properties[0].getNames();

        tableView.getColumns().get(0).setCellValueFactory(cell -> (ObservableValue) cell.getValue().getName());
        double priorityColumnWidth = tableView.getColumns().get(1).getPrefWidth() / properties[0].getControls().size();
        controls.forEach(
                control -> {
                    TableColumn<Property, ?> column = new TableColumn<>();
                    column.setText(priorities[controls.indexOf(control)]);
                    column.setPrefWidth(priorityColumnWidth);
                    column.setSortable(false);
                    column.setResizable(false);
                    column.setEditable(false);
                    column.setStyle("-fx-alignment: CENTER;");
                    column.setCellValueFactory(
                            cell -> (ObservableValue) cell.getValue().getControls()
                                    .get(controls.indexOf(control))

                    );
                    priorityColumns.add(column);
                }
        );

        tableView.getColumns().get(1).getColumns().setAll(priorityColumns);
        tableView.getItems().setAll(properties);
        tableView.setSelectionModel(null);
    }
}
