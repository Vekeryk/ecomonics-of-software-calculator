package cocomo.data;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;

public interface Property {
    SimpleStringProperty getName();
    List<SimpleObjectProperty<RadioButton>> getRadioPropertyPriorities();
    ToggleGroup getToggleGroup();
    Property[] getValues();
    default Priority[] getPriorities() {
        return new Priority[] {
                Priority.VERY_LOW,
                Priority.LOW,
                Priority.AVERAGE,
                Priority.HIGH,
                Priority.VERY_HIGH,
                Priority.EXTRA_HIGH
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
}
