package cocomo.data.cocomo2;

import cocomo.data.Priority;
import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum EarlyCostProperty implements Property {
    PERS(
            "Можливості аналітика",
            2.12,
            1.62,
            1.16,
            1.00,
            0.83,
            0.63,
            0.50
    ),
    PREX(
            "Досвід розробки додатків",
            1.59,
            1.33,
            1.22,
            1.00,
            0.87,
            0.74,
            0.62
    ),
    RCPX(
            "Можливості програміста",
            0.49,
            0.60,
            0.83,
            1.00,
            1.33,
            1.91,
            2.72
    ),
    RUSE(
            "Тривалість роботи персоналу",
            0,
            0,
            0.95,
            1.00,
            1.07,
            1.15,
            1.24
    ),
    PDIF(
            "Досвід роботи з платформою",
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
    public final SimpleObjectProperty<RadioButton> criticalLow;
    public final SimpleObjectProperty<RadioButton> veryLow;
    public final SimpleObjectProperty<RadioButton> low;
    public final SimpleObjectProperty<RadioButton> average;
    public final SimpleObjectProperty<RadioButton> high;
    public final SimpleObjectProperty<RadioButton> veryHigh;
    public final SimpleObjectProperty<RadioButton> extraHigh;
    public final List<SimpleObjectProperty<RadioButton>> priorities;

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
    public List<SimpleObjectProperty<RadioButton>> getRadioPropertyPriorities() {
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
    public Priority[] getPriorities() {
        return new Priority[] {
                Priority.EXTRA_LOW,
                Priority.VERY_LOW,
                Priority.LOW,
                Priority.AVERAGE,
                Priority.HIGH,
                Priority.VERY_HIGH,
                Priority.EXTRA_HIGH
        };
    }
}
