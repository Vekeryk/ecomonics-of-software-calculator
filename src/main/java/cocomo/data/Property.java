package cocomo.data;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;

public enum Property {
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
    ),
    ;

    public final HashMap<Priority, RadioButton> buttons = new HashMap<>();
    public final ToggleGroup toggleGroup = new ToggleGroup();
    public final String name;
    public final double veryLow;
    public final double low;
    public final double average;
    public final double high;
    public final double veryHigh;
    public final double critical;

    Property(String name, double veryLow,
             double low, double average,
             double high, double veryHigh, double critical) {
        this.name = name;
        this.veryLow = veryLow;
        this.low = low;
        this.average = average;
        this.high = high;
        this.veryHigh = veryHigh;
        this.critical = critical;

        createButtons();
    }

    public void createButtons() {
        buttons.put(Priority.VERY_LOW, buttonFactory(veryLow, Priority.VERY_LOW));
        buttons.put(Priority.LOW, buttonFactory(low, Priority.LOW));
        buttons.put(Priority.AVERAGE, buttonFactory(average, Priority.AVERAGE));
        buttons.put(Priority.HIGH, buttonFactory(high, Priority.HIGH));
        buttons.put(Priority.VERY_HIGH, buttonFactory(veryHigh, Priority.VERY_HIGH));
        buttons.put(Priority.CRITICAL, buttonFactory(critical, Priority.CRITICAL));
    }

    private RadioButton buttonFactory(double value, Priority priority) {
        RadioButton radioButton;
        if (value == 0) {
            radioButton = new RadioButton("n/a");
            radioButton.setDisable(true);
        } else {
            radioButton = new RadioButton(String.valueOf(value));
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setSelected(priority.selected);
            radioButton.setUserData(value);
        }
        return radioButton;
    }
}
