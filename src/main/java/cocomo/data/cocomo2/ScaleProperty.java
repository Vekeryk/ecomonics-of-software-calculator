package cocomo.data.cocomo2;

import cocomo.data.Priority;
import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum ScaleProperty implements Property {
    PREC(
            "Наявність аналогічного досвіду",
            6.20,
            4.96,
            3.72,
            2.48,
            1.24,
            0
    ),
    FLEX(
            "Гнучкість процесу розробки",
            5.07,
            4.05,
            3.04,
            2.03,
            1.01,
            0
    ),
    RESL(
            "Архітектура і дозвіл ризиків",
            7.07,
            5.65,
            4.24,
            2.83,
            1.41,
            0
    ),
    TEAM(
            "Спрацьованість команди",
            5.48,
            4.38,
            3.29,
            2.19,
            1.10,
            0
    ),
    PMAT(
            "Зрілість процесів",
            7.80,
            6.24,
            4.68,
            3.12,
            1.56,
            0
    );

    public final ToggleGroup toggleGroup = new ToggleGroup();
    public final SimpleStringProperty name;
    public final SimpleObjectProperty<RadioButton> veryLow;
    public final SimpleObjectProperty<RadioButton> low;
    public final SimpleObjectProperty<RadioButton> average;
    public final SimpleObjectProperty<RadioButton> high;
    public final SimpleObjectProperty<RadioButton> veryHigh;
    public final SimpleObjectProperty<RadioButton> extraHigh;
    public final List<SimpleObjectProperty<RadioButton>> priorities;

    ScaleProperty(String name, double veryLow,
                  double low, double average,
                  double high, double veryHigh, double critical) {
        this.name = new SimpleStringProperty(name);
        this.veryLow = new SimpleObjectProperty<>(buttonFactory(veryLow, Priority.VERY_LOW));
        this.low = new SimpleObjectProperty<>(buttonFactory(low, Priority.LOW));
        this.average = new SimpleObjectProperty<>(buttonFactory(average, Priority.AVERAGE));
        this.high = new SimpleObjectProperty<>(buttonFactory(high, Priority.HIGH));
        this.veryHigh = new SimpleObjectProperty<>(buttonFactory(veryHigh, Priority.VERY_HIGH));
        this.extraHigh = new SimpleObjectProperty<>(buttonFactory(critical, Priority.EXTRA_HIGH));
        priorities = Arrays.asList(this.veryLow, this.low, this.average, this.high, this.veryHigh, this.extraHigh);
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
}
