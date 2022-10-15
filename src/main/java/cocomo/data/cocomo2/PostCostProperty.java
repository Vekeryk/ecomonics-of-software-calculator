package cocomo.data.cocomo2;

import cocomo.data.Priority;
import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum PostCostProperty implements Property {
    ACAP(
            "Можливості аналітика",
            1.42,
            1.29,
            1.00,
            0.85,
            0.71,
            0
    ),
    AEXP(
            "Досвід розробки додатків",
            1.22,
            1.10,
            1.00,
            0.88,
            0.81,
            0
    ),
    PCAP(
            "Можливості програміста",
            1.34,
            1.15,
            1.00,
            0.88,
            0.76,
            0
    ),
    PCON(
            "Тривалість роботи персоналу",
            1.29,
            1.12,
            1.00,
            0.90,
            0.81,
            0
    ),
    PEXP(
            "Досвід роботи з платформою",
            1.19,
            1.09,
            1.00,
            0.91,
            0.85,
            0
    ),
    LTEX(
            "Досвід з мовою програмування",
            1.20,
            1.09,
            1.00,
            0.91,
            0.84,
            0
    ),
    RELY(
            "Надійність програми",
            0.84,
            0.92,
            1.00,
            1.10,
            1.26,
            0
    ),
    DATA(
            "Розмір бази даних",
            0,
            0.23,
            1.00,
            1.14,
            1.28,
            0
    ),
    CPLX(
            "Складність програми",
            0.73,
            0.87,
            1.00,
            1.17,
            1.34,
            1.74
    ),
    RUSE(
            "Необхідність багаторазовості",
            0,
            0.95,
            1.00,
            1.07,
            1.15,
            1.24
    ),
    DOCU(
            "Документування життєвого циклу",
            0.81,
            0.91,
            1.00,
            1.11,
            1.23,
            0
    ),
    TIME(
            "Обмеження часу виконання",
            0,
            0,
            1.00,
            1.11,
            1.29,
            1.63
    ),
    STOR(
            "Обмеження пам'яті",
            0,
            0,
            1.00,
            1.05,
            1.17,
            1.46
    ),
    PVOL(
            "Змінність патформи",
            0,
            0.87,
            1.00,
            1.15,
            1.30,
            0
    ),
    TOOL(
            "Інструментальні засоби",
            1.17,
            1.09,
            1.00,
            0.90,
            0.78,
            0
    ),
    SITE(
            "Багатоабонентська розробка",
            1.22,
            1.09,
            1.00,
            0.93,
            0.86,
            0.80
    ),
    SCED(
            "Необхідний час",
            1.43,
            1.14,
            1.00,
            1.00,
            1.00,
            0
    );

    public final ToggleGroup toggleGroup = new ToggleGroup();
    public final SimpleStringProperty name;
    public final SimpleObjectProperty<RadioButton> veryLow;
    public final SimpleObjectProperty<RadioButton> low;
    public final SimpleObjectProperty<RadioButton> average;
    public final SimpleObjectProperty<RadioButton> high;
    public final SimpleObjectProperty<RadioButton> veryHigh;
    public final SimpleObjectProperty<RadioButton> critical;
    public final List<SimpleObjectProperty<RadioButton>> priorities;

    PostCostProperty(String name, double veryLow,
                     double low, double average,
                     double high, double veryHigh, double critical) {
        this.name = new SimpleStringProperty(name);
        this.veryLow = new SimpleObjectProperty<>(buttonFactory(veryLow, Priority.VERY_LOW));
        this.low = new SimpleObjectProperty<>(buttonFactory(low, Priority.LOW));
        this.average = new SimpleObjectProperty<>(buttonFactory(average, Priority.AVERAGE));
        this.high = new SimpleObjectProperty<>(buttonFactory(high, Priority.HIGH));
        this.veryHigh = new SimpleObjectProperty<>(buttonFactory(veryHigh, Priority.VERY_HIGH));
        this.critical = new SimpleObjectProperty<>(buttonFactory(critical, Priority.EXTRA_HIGH));
        priorities = Arrays.asList(this.veryLow, this.low, this.average, this.high, this.veryHigh, this.critical);
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
        return PostCostProperty.values();
    }
}
