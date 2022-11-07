package cocomo.data.functionalpoints;

import cocomo.data.Priority;
import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum DifficultyLevel implements Property {

    EI("Зовнішні вводи", 7, 10, 15),
    EO("Зовнішні виводи", 5, 7, 10),
    EQ("Зовнішні запити", 3, 4, 6),
    ILF("Внутрішні логічні файли", 4, 5, 7),
    EIF("Зовнішні інтерфейсні файли", 3, 4, 6);

    public final ToggleGroup toggleGroup = new ToggleGroup();
    public final SimpleStringProperty name;
    public final SimpleObjectProperty<Control> amount;
    public final SimpleObjectProperty<Control> low;
    public final SimpleObjectProperty<Control> average;
    public final SimpleObjectProperty<Control> high;
    public final List<SimpleObjectProperty<Control>> controls;

    DifficultyLevel(String name, int low, int average, int high) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleObjectProperty<>(new TextField());
        this.low = new SimpleObjectProperty<>(buttonFactory(low, Priority.LOW));
        this.average = new SimpleObjectProperty<>(buttonFactory(average, Priority.AVERAGE));
        this.high = new SimpleObjectProperty<>(buttonFactory(high, Priority.HIGH));
        controls = Arrays.asList(this.amount, this.low, this.average, this.high);
    }

    @Override
    public SimpleStringProperty getName() {
        return name;
    }

    @Override
    public List<SimpleObjectProperty<Control>> getControls() {
        return controls;
    }

    public int getAmount() {
        return Integer.parseInt(((TextField) amount.getValue()).getText());
    }

    @Override
    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    @Override
    public Property[] getValues() {
        return DifficultyLevel.values();
    }

    @Override
    public String[] getNames() {
        return new String[]{
                "Кількість",
                Priority.LOW.name,
                Priority.AVERAGE.name,
                Priority.HIGH.name
        };
    }
}
