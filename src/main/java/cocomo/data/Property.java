package cocomo.data;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;

public interface Property {
    SimpleStringProperty getName();

    List<SimpleObjectProperty<Control>> getControls();

    ToggleGroup getToggleGroup();

    Property[] getValues();

    default String[] getNames() {
        return new String[]{
                Priority.VERY_LOW.name,
                Priority.LOW.name,
                Priority.AVERAGE.name,
                Priority.HIGH.name,
                Priority.VERY_HIGH.name,
                Priority.EXTRA_HIGH.name
        };
    }

    default RadioButton buttonFactory(double value, Priority priority) {
        RadioButton radioButton;
        if (value == 0) {
            radioButton = new RadioButton("n/a");
            radioButton.setDisable(true);
        } else {
            radioButton = new RadioButton(String.valueOf(value));
            radioButton.setToggleGroup(getToggleGroup());
            radioButton.setSelected(priority.selected);
            radioButton.setUserData(value);
        }
        return radioButton;
    }

    default RadioButton buttonFactory(double value) {
        RadioButton radioButton;
        radioButton = new RadioButton();
        radioButton.setToggleGroup(getToggleGroup());
        radioButton.setUserData(value);
        if (value == 0) {
            radioButton.setSelected(true);
        }
        return radioButton;
    }
}
