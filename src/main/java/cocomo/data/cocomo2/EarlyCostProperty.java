package cocomo.data.cocomo2;

import cocomo.data.Priority;
import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum EarlyCostProperty implements Property {
    PERS(
            "Кваліфікація персоналу",
            2.12,
            1.62,
            1.16,
            1.00,
            0.83,
            0.63,
            0.50
    ),
    PREX(
            "Досвід персоналу",
            1.59,
            1.33,
            1.22,
            1.00,
            0.87,
            0.74,
            0.62
    ),
    RCPX(
            "Складність і надійність продукту",
            0.49,
            0.60,
            0.83,
            1.00,
            1.33,
            1.91,
            2.72
    ),
    RUSE(
            "Розробка для повторного використання",
            0,
            0,
            0.95,
            1.00,
            1.07,
            1.15,
            1.24
    ),
    PDIF(
            "Складність платформи розробки",
            0,
            0,
            0.87,
            1.00,
            1.29,
            1.81,
            2.61
    ),
    FCIL(
            "Обладнання",
            1.43,
            1.30,
            1.10,
            1.00,
            0.87,
            0.73,
            0.62
    ),
    SCED(
            "Необхідний час",
            0,
            1.43,
            1.14,
            1.00,
            1.00,
            0,
            0
    );

    public final ToggleGroup toggleGroup = new ToggleGroup();
    public final SimpleStringProperty name;
    public final SimpleObjectProperty<Control> criticalLow;
    public final SimpleObjectProperty<Control> veryLow;
    public final SimpleObjectProperty<Control> low;
    public final SimpleObjectProperty<Control> average;
    public final SimpleObjectProperty<Control> high;
    public final SimpleObjectProperty<Control> veryHigh;
    public final SimpleObjectProperty<Control> extraHigh;
    public final List<SimpleObjectProperty<Control>> priorities;

    EarlyCostProperty(String name, double criticalLow, double veryLow,
                      double low, double average,
                      double high, double veryHigh, double extraHigh) {
        this.name = new SimpleStringProperty(name);
        this.criticalLow = new SimpleObjectProperty<>(buttonFactory(criticalLow, Priority.EXTRA_LOW));
        this.veryLow = new SimpleObjectProperty<>(buttonFactory(veryLow, Priority.VERY_LOW));
        this.low = new SimpleObjectProperty<>(buttonFactory(low, Priority.LOW));
        this.average = new SimpleObjectProperty<>(buttonFactory(average, Priority.AVERAGE));
        this.high = new SimpleObjectProperty<>(buttonFactory(high, Priority.HIGH));
        this.veryHigh = new SimpleObjectProperty<>(buttonFactory(veryHigh, Priority.VERY_HIGH));
        this.extraHigh = new SimpleObjectProperty<>(buttonFactory(extraHigh, Priority.EXTRA_HIGH));
        priorities = Arrays.asList(this.criticalLow, this.veryLow, this.low, this.average, this.high, this.veryHigh, this.extraHigh);
    }

    @Override
    public SimpleStringProperty getName() {
        return name;
    }

    @Override
    public List<SimpleObjectProperty<Control>> getControls() {
        return priorities;
    }

    @Override
    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    @Override
    public Property[] getValues() {
        return ScaleProperty.values();
    }

    @Override
    public String[] getNames() {
        return new String[]{
                Priority.EXTRA_LOW.name,
                Priority.VERY_LOW.name,
                Priority.LOW.name,
                Priority.AVERAGE.name,
                Priority.HIGH.name,
                Priority.VERY_HIGH.name,
                Priority.EXTRA_HIGH.name
        };
    }
}
