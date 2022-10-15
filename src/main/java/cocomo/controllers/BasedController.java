package cocomo.controllers;

import cocomo.data.Priority;
import cocomo.data.Property;
import cocomo.data.Result;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public interface BasedController {
    Result getResult();
    default void fillTable(TableView<Property> tableView, Property[] properties) {
        List<TableColumn<Property, ?>> priorityColumns = new ArrayList<>();
        List<SimpleObjectProperty<RadioButton>> radioPropertyPriorities = properties[0].getRadioPropertyPriorities();
        Priority[] priorities = properties[0].getPriorities();

        tableView.getColumns().get(0).setCellValueFactory(cell -> (ObservableValue)cell.getValue().getName());
        double priorityColumnWidth = tableView.getColumns().get(1).getPrefWidth() / properties[0].getRadioPropertyPriorities().size();
        radioPropertyPriorities.forEach(
                (o) -> {
                    TableColumn<Property, ?> column = new TableColumn<>();
                    column.setText(priorities[radioPropertyPriorities.indexOf(o)].name);
                    column.setPrefWidth(priorityColumnWidth);
                    column.setSortable(false);
                    column.setResizable(false);
                    column.setEditable(false);
                    column.setCellValueFactory(
                            cell -> (ObservableValue)cell.getValue().getRadioPropertyPriorities()
                                    .get(radioPropertyPriorities.indexOf(o))
                    );
                    priorityColumns.add(column);
                }
        );

        tableView.getColumns().get(1).getColumns().setAll(priorityColumns);
        tableView.getItems().setAll(properties);
        tableView.setSelectionModel(null);
    }
}
