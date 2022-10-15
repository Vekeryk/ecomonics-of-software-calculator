package cocomo.data.cocomo;

import cocomo.data.Priority;
import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum Rating implements Property {
    RELY(
            "Необхідна надійність ПЗ",
            0.75,
            0.88,
            1,
            1.15,
            1.4,
            0
    ),
    DATA(
            "Розмір БД додатку",
            0,
            0.94,
            1,
            1.08,
            1.16,
            0
    ),
    CPLX(
            "Складність продукту",
            0.70,
            0.85,
            1,
            1.15,
            1.30,
            1.62
    ),
    TIME(
            "Обмеження швидкодії",
            0,
            0,
            1,
            1.11,
            1.30,
            1.66
    ),
    STOR(
            "Обмеження пам’яті",
            0,
            0,
            1,
            1.06,
            1.21,
            0
    ),
    VIRT(
            "Нестійкість віртуального оточення",
            0,
            0.87,
            1,
            1.15,
            1.30,
            0
    ),
    TURN(
            "Необхідний час відновлення",
            0,
            0.87,
            1,
            1.07,
            1.15,
            0
    ),
    ACAP(
            "Аналітичні здібності",
            1.46,
            1.19,
            1,
            0.86,
            0.71,
            0
    ),
    AEXP(
            "Досвід розробки",
            1.29,
            1.13,
            1,
            0.91,
            0.82,
            0
    ),
    PCAP(
            "Здібності до розробки ПЗ",
            1.42,
            1.17,
            1,
            0.86,
            0.70,
            0
    ),
    VEXP(
            "Досвід використання віртуальних машин",
            1.21,
            1.10,
            1,
            0.90,
            0,
            0
    ),
    LEXP(
            "Досвід розробки на мовах програмування",
            1.14,
            1.07,
            1,
            0.95,
            0,
            0
    ),
    MODP(
            "Застосування методів розробки ПЗ",
            1.24,
            1.10,
            1,
            0.91,
            0.82,
            0
    ),
    TOOL(
            "Використання інструментарію розробки",
            1.24,
            1.10,
            1,
            0.91,
            0.83,
            0
    ),
    SCED(
            "Вимоги дотримання графіка розробки",
            1.23,
            1.08,
            1,
            1.04,
            1.10,
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

    Rating(String name, double veryLow,
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
    public cocomo.data.Property[] getValues() {
        return Rating.values();
    }
}
